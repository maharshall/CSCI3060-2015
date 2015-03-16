import java.io.*;
import java.util.*;

class User {
    String username;
    String type;
    int credit;

    public User(String username, String type, int credit){
        this.username = username;
        this.type = type;
        this.credit = credit;
    }

    public void printUser(){
        System.out.println(username+"_"+type+"_"+credit);
    }

    public void updateCredit(int c){credit+=c;}

}
