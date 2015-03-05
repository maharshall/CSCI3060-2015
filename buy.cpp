/*
 * Felicitates the purchasing of tickets
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void buy(vector<string> tickets, vector<string> users) {
	string seller = "", event = "", input = "", str = "";
	int quantity = 0, price = 0;
	ofstream outfile;
	ifstream infile;

	//take in event name
	eventin: cout << "Enter the event name: ";
	getline(cin, event);
	for(int i = 0; i < tickets.size(); i+=4) {
        if(event == tickets[i]){
            str = tickets[i+3];
            break ; //this is what we want!
        } 
        if(i+4 >= tickets.size()){
            cout << "Error: event not found." << endl;
            goto eventin;
        }
    }
    stringstream stream(str);
    stream >> price;

    //check if buyer has enough money

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	getline(cin, seller);
    for(int i = 0; i < users.size(); i+=3) {
        if(seller == users[i]){
            break;
        }
        if(i+3 >= users.size(){
            cout << "Error: seller not found." << endl;
            goto sellerin;
        }
    }

	//take in quantity of tickets
	quantin: cout << "Enter the amount of tickets you wish to buy: ";
	getline(cin, str);
   	stream >> quantity;
    //quantity cannot exceed maximum
	if(quantity > 4) {
		cout << "Error: ticket quantity cannot exceed 4." << endl;
		goto quantin;
	}

    //check to see requested amound does not exceed availability
    for(int i = 0; i < tickets.size(); i+=3 ){
        if(event == tickets[i]){
            if(quantity > tickdata[i+2]){
                cout << "Error: ticket quantity exceeds availability." << endl;
                goto quantin;
            }
        }
    }

	cout << "Are you sure you want to buy these tickets? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
	//write to daily.txt
	outfile.open("daily.txt", ios::out | ios::app);
		outfile << "04_" << event << string(25-event.length(), '-' << "_" << seller << string(15-seller.length(), '-') << quantity << "_" << price  << endl;
		cout << "Tickets were purchased successfully." << endl;
	outfile.close();
	} else {
		cout << "Tickets were not purchased." << endl;
	}
}
