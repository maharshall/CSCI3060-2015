/*
 * Felicitates the purchasing of tickets
 */

#include <stdio.h>
#include <iostream>

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
        if(event.compare(tickets[i]) == 0){
            str = tickets[i+3];
            break ; 
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
    if(findUser(users, seller) < 0){
        cout << "Error: seller not found." << endl;
        goto sellerin;
    }    

	//take in quantity of tickets
	quantin: cout << "Enter the amount of tickets you wish to buy: ";
    cin >> quantity;	
    //quantity cannot exceed maximum
	if(quantity > 4) {
		cout << "Error: ticket quantity cannot exceed 4." << endl;
		goto quantin;
	}

    //check to see requested amound does not exceed availability
    for(int i = 0; i < tickets.size(); i+=3 ){
        if(event.compare(tickets[i]) == 0){
            str = tickets[i+2];
            int available = 0;
            stream >> available;
            if(quantity > available){
                cout << "Error: ticket quantity exceeds availability." << endl;
                goto quantin;
            }
        }
    }

	cout << "Are you sure you want to buy these tickets? (Y/N):";
	getline(cin, input);
    getline(cin, input);

	if(input == "y" || input == "Y") {
	   writeSalesTransaction("04", event, seller, quantity, price); 
	} else {
		cout << "Tickets were not purchased." << endl;
	}
}
