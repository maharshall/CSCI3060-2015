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
        
        addcredit = new AddCredit();
        buy = new Buy();
        refund = new Refund();
        sell = new Sell();
        
        //Does everything
        parseTransactions();
        
        writeUsers(users);
        writeEvents(ticks);
        writeHistory();
    }

    //For building the user arraylist and applying transactions
    public static ArrayList<User> buildUsers(ArrayList<User> users){
        return users;
    }

    //For building the ticket arraylist and applying transactions
    public static ArrayList<Ticket> buildEvents(ArrayList<Ticket> ticks){
        return ticks;
    }

    //For writing the updated users to users.txt
    public static void writeUsers(ArrayList<User> users){
        
    }

    //For writing the updated events to tickets.txt
    public static void writeEvents(ArrayList<Ticket> ticks){
        
    }
    
    //For reading in transactions and processing them
    public static void parseTransactions(){

    }

    //For appending daily.txt to history.txt and then clearing daily.txt
    public static void writeHistory(){
        
    }
}
