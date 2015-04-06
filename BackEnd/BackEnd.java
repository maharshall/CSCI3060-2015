/*
 * The main file for the back end.
 * Essentially all the work is done here
 * except for the buying operation.
 */
import java.io.*;
import java.util.*;

public class BackEnd {

    /*
     * Arraylists for storing users and events
     */
    static ArrayList<User> users;
    static ArrayList<Ticket> ticks;

    //The Buy class
    static Buy buy;

    public BackEnd(){

    }

    public void initBuy(){
        buy = new Buy();
    }

    public static void main(String[] args) {
        users = new ArrayList<User>();
        users = buildUsers(users);
        ticks = new ArrayList<Ticket>();
        ticks = buildEvents(ticks);

        buy = new Buy();

        //Most of the work happens here!
        parseTransactions();

        writeUsers(users);
        writeEvents(ticks);
        writeHistory();
    }

    /*
     * Read users.txt line-by-line and add instances of the User class to the arraylist
     */
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

    /*
     * Read tickets.txt line-by-line and add instances of the Ticket class to the arraylist
     */
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

    /*
     * Clear users.txt and write the updated list of users.
     */
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

    /*
     * Clear tickets.txt and write the updated list of tickets.
     */
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

    /*
     * Read daily.txt line-by-line and perform operations
     * based on the op-code at the beginning of each line
     */
    public static void parseTransactions(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../daily.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split("_");
                int i = Integer.parseInt(tokens[0]);
                switch(i) {
                    case 0:
                        //00 - logout, we don't need to do anything
                        break;
                    case 1:
                        //01 - Create a new user.
                        //We can just add to the arraylist directly.
                        users.add(new User(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                        break;

                    case 2:
                        //02 - Delete a user.
                        //We can just find and remove them from the arraylist.
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[1].equals(users.get(j).username)) {
                                users.remove(j);
                                break;
                            }
                        }
                        break;

                    case 3:
                        //03 - Sell/create an event.
                        //We can just addit to the arraylist directly.
                        ticks.add(new Ticket(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                        break;

                    case 4:
                        //04 - Buy tickets.
                        //Find the seller and event, then pass them to the buy class.
                        User s = new User();
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[2].equals(users.get(j).username)) {
                                s = users.get(j);
                                break;
                            }
                        }

                        Ticket t = new Ticket();
                        for(int j = 0; j < ticks.size(); ++j) {
                            if(tokens[1].equals(ticks.get(j).event) && s.username.equals(ticks.get(j).seller)) {
                                t = ticks.get(j);
                                break;
                            }
                        }
                        buy.apply(s, t, Integer.parseInt(tokens[3]));
                        break;

                    case 5:
                        //05 - Refund credits.
                        //We can just find the users in the arraylist and alter their credit values.
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

                    case 6:
                        //06 - Add credit.
                        //We can just find the user in the arraylist and give them credit.
                        for(int j = 0; j < users.size(); ++j) {
                            if(tokens[1].equals(users.get(j).username)) {
                                users.get(j).credit += Integer.parseInt(tokens[3]);
                                break;
                            }
                        }
                        break;

                    default:
                        //Default case.
                        //If the front end works properly then this statement should never be reached.
                        System.out.println("Invalid token! This shouldn't happen.");
                        break;

                }
            }
        } catch(Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /*
     * Append the contents of daily.txt to history.txt
     * Clear daily.txt for the next front end session.
     */
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

    /*
     * Writes any errors to the log file
     */
    public static void writeLog(){
        //TODO: yes
    }
}
