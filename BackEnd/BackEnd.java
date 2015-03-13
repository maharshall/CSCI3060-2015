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
        ticks = new ArrayList<Ticket>();

        addcredit = new AddCredit();
        buy = new Buy();
        refund = new Refund();
        sell = new Sell();
        
        users.add(new User("maharshall", "AA", 30));
        users.get(0).printUser();
    }

    //For building the user arraylist and applying transactions
    public ArrayList<User> buildUsers(ArrayList<User> users){
        return users;
    }

    //For building the ticket arraylist and applying transactions
    public ArrayList<Ticket> buildEvents(ArrayList<Ticket> ticks){
        return ticks;
    }

    //For writing the updated users to users.txt
    public void writeUsers(ArrayList<User> users){
        
    }

    //For writing the updated events to tickets.txt
    public void writeEvents(ArrayList<Ticket> ticks){
        
    }
    
    //For reading in transactions and processing them
    public void parseTransactions(){

    }

    //For appending daily.txt to history.txt and then clearing daily.txt
    public void writeHistory(){
        
    }
}
