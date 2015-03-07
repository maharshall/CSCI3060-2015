/*
 * Adds tickets to be sold
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void sell(string seller) {
	string event = "", input = "", str = "";
	int price = 0, quantity = 0;
	ofstream outfile;
	ifstream infile;

	//take in event name
	eventin: cout << "Enter the event name: ";
	getline(cin, event);
	if(event.length() > 25) {
		cout << "Error: event name cannot exceed 25 characters." << endl;
		goto eventin;
	}

	//take in ticket price
	pricein: cout << "Enter the ticket price: ";
	getline(cin, str);
	stringstream stream(str);
   	stream >> price;
	if(price > 999) {
		cout << "Error: ticket price cannot exceed 999." << endl;
		goto pricein;
	}

	//take in quantity of tickets
	quantin: cout << "Enter the amount of tickets: ";
	getline(cin, str);
   	stream >> quantity;
	if(quantity > 100) {
		cout << "Error: ticket quantity cannot exceed 100." << endl;
		goto quantin;
	}

	cout << "Are you sure you want to create this event? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
        writeSalesTransaction("03", event, seller, quantity, price);
		cout << "Event was created successfully." << endl;
	} else {
		cout << "Event was not created." << endl;
	}
}
