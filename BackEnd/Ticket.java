import java.io.*;
import java.util.*;

/*
 * Ticket Class
 * Events will be stored in an arraylist
 * as instances of this class.
 */
class Ticket {
    String event;
    String seller;
    int tickets;
    int price;

    public Ticket(String event, String seller, int tickets, int price){
        this.event = event;
        this.seller = seller;
        this.tickets = tickets;
        this.price = price;
    }

    public Ticket(){}

    public void printEvent(){
        System.out.println(event+"_"+seller+"_"+tickets+"_"+price);
    }

    public void updateTick(int i){
        tickets+=i;
    }

    public int getPrice(){
        return price;
    }
}
