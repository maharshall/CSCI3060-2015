//The main file for the back end

import java.io.*;
import java.util.*;

public class BackEnd {

    static ArrayList<User> users;
    static ArrayList<Ticket> ticks;

    static AddCredit addcredit;
    static Buy buy;
    static Refund refund;
    static Sell sell;
    
    public static void main(String[] args) {
        users = new ArrayList<User>();
        users = buildUsers(users);
        ticks = new ArrayList<Ticket>();
        ticks = buildEvents(ticks);
        
        buy = new Buy();
        
        //Does everything
        parseTransactions();
        
        writeUsers(users);
        writeEvents(ticks);
        writeHistory();
    }

    //For building the user arraylist and applying transactions
    public static ArrayList<User> buildUsers(ArrayList<User> users){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../users.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split("_");
                users.add(new User(tokens[0], tokens[1], Integer.parseInt(tokens[2])));
            }
        } catch(Exception e){
            System.out.println("Something went wrong: " + e);
        }
        return users;
    }

    //For building the ticket arraylist and applying transactions
    public static ArrayList<Ticket> buildEvents(ArrayList<Ticket> ticks){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../tickets.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split("_");
                ticks.add(new Ticket(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
            }
        } catch(Exception e){
            System.out.println("Something went wrong: " + e);
        }
        return ticks;
    }

    //For writing the updated users to users.txt
    public static void writeUsers(ArrayList<User> users){
        try {
            PrintWriter pw = new PrintWriter("../users.txt");
            pw.write("");
            for(int i = 0; i < users.size(); ++i) {
                String temp = (users.get(i).username + "_" + users.get(i).type
                        + "_" + Integer.toString(users.get(i).credit));
                pw.write(temp + "\n");
            }
            pw.close();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e);
        }
    }

    //For writing the updated events to tickets.txt
    public static void writeEvents(ArrayList<Ticket> ticks){
        try {
            PrintWriter pw = new PrintWriter("../tickets.txt");
            pw.write("");
            for(int i = 0; i < ticks.size(); ++i) {
                String temp = (ticks.get(i).event + "_" + ticks.get(i).seller + "_"
                        + Integer.toString(ticks.get(i).tickets)) + "_" + Integer.toString(ticks.get(i).price);
                pw.write(temp + "\n");
            }
            pw.close();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e);
        }
    }
    
    //For reading in transactions and processing them
    public static void parseTransactions(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../daily.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split("_");
                int i = Integer.parseInt(tokens[0]);
                switch(i) {
                    case 0:
                       break; //do nothing
                    case 1:     //Create new user
                        users.add(new User(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                        break;

                    case 2:     //Delete User
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[1].equals(users.get(j).username)) {
                                users.remove(j);
                                break;
                            }
                        }
                        break;

                    case 3:     //Create event
                        ticks.add(new Ticket(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]))); 
                        break;
                    
                    case 4:     //Buy tickets
                        //find seller
                        User s = new User();
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[2].equals(users.get(j).username)) {
                                s = users.get(j);
                            }
                        }

                        //find event
                        Ticket t = new Ticket();
                        for(int j = 0; j < ticks.size(); ++j) {
                            if(tokens[1].equals(ticks.get(j).event) && s.username.equals(ticks.get(j).seller)) {
                                t = ticks.get(j);
                            }
                        }
                        buy.apply(s, t, Integer.parseInt(tokens[3]));
                        break;
                    
                    case 5:     //Refund Credits
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[1].equals(users.get(j).username)) {
                                //Add credit to buyer account
                                users.get(j).credit += Integer.parseInt(tokens[3]);
                            }
                            if(tokens[2].equals(users.get(j).username)) {
                                //Remove credit from seller account
                                users.get(j).credit -= Integer.parseInt(tokens[3]);
                            }
                        }
                        break;
                    
                    case 6:     //Add Credit
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[1].equals(users.get(j).username)) {
                                users.get(j).credit += Integer.parseInt(tokens[3]);
                                break;
                            }
                        }
                        break;
                    
                    default:
                        System.out.println("Invalid token! This shouldn't happen.");
                        break;

                }
            }
        } catch(Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    //For appending daily.txt to history.txt and then clearing daily.txt
    public static void writeHistory(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../daily.txt"));
            PrintWriter pw = new PrintWriter("../history.txt");
            String line;
            while((line = br.readLine()) != null) {
                pw.append(line + "\n");
            }
            pw.close();
            PrintWriter p2 = new PrintWriter("../daily.txt");
            p2.write("");
            p2.close();
        } catch(Exception e) {
            System.out.println("Something went wrong: " + e);
        }     
    }
}
