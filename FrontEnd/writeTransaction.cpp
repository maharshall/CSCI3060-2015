/*
 * Functions that Write to the daily transaction file
 */

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

ofstream outfile;
stringstream conv;

void writeUserTransaction(string code, string user, string type, int _credit) {
    string credit = "000000";

    if(code.compare("02") == 0){
        //do a deletion from users.txt
    } else {
        if(_credit != 0){ 
            conv << _credit;
            string c  = conv.str();
            int i = (6-c.length());
            credit.replace(i, c.length(), c);
        }
    }
        outfile.open("../daily.txt", ios::out | ios::app);
        outfile << code << "_" << user << string(15-user.length(), '-') << "_"
                << type << "_" << credit << endl;
        outfile.close();
}

void writeRefundTransaction(string buyer, string seller, int _credit) {
    string credit = "000000";
    if(_credit != 0){
        conv << _credit;
        string c  = conv.str();
        int i = (6-c.length());
        credit.replace(i, c.length(), c);
    }
    outfile.open("../daily.txt", ios::out | ios::app);
    outfile <<  "05_" << buyer << string(15-buyer.length(), '-') << "_"
            << seller <<"_"<< string(15-seller.length(), '-') << credit << endl;
    outfile.close();
}

void writeSalesTransaction(string code, string event, string seller, int _tickets, int _price) {
    string tickets = "000", price = "000000";
    if(_tickets != 0){
        conv << _tickets;
        string t  = conv.str();
        int i = (3-t.length());
        tickets.replace(i, t.length(), t);
    }
    if(_price != 0){
        conv.str(string());
        conv.clear();
        conv << _price;
        string p  = conv.str();
        int i = (6-p.length());
        price.replace(i, p.length(), p);
    }
    outfile.open("../daily.txt", ios::out | ios::app);
    outfile << code <<  "_" << event << string(25-event.length(), '-') << "_"
            << seller << string(15-seller.length(), '-') <<"_"<< tickets << "_"
            << price << endl;
    outfile.close();
}
