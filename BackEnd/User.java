import java.io.*;
import java.util.*;

/*
 * The User Class.
 * Users will be stored as an arraylist
 * of instances of this class
 */
class User {
    String username;
    String type;
    int credit;

    public User(String username, String type, int credit){
        this.username = username;
        this.type = type;
        this.credit = credit;
    }

    public User(){}

    public void printUser(){
        System.out.println(username+"_"+type+"_"+credit);
    }

    public void updateCredit(int c){credit+=c;}

}
