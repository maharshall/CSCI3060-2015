/*
 * Felicitates the purchasing of tickets
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void buy() {
	string seller = "", event = "", input = "", str = "";
	int quantity = 0;
	ofstream outfile;
	ifstream infile;

	//take in event name
	eventin: cout << "Enter the event name: ";
	getline(cin, event);
	//find event

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	getline(cin, seller);
	//find seller

	//take in quantity of tickets
	quantin: cout << "Enter the amount of tickets you wish to buy: ";
	getline(cin, str);
	stringstream stream(str);
   	stream >> quantity;
	if(quantity > 4) {
		cout << "Error: ticket quantity cannot exceed 4." << endl;
		goto quantin;
	}

	cout << "Are you sure you want to buy these tickets? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
	//write to daily.txt
	outfile.open("daily.txt", ios::out | ios::app);
		outfile << /*04_event_seller_tickets_price*/ << endl;
		cout << "Tickets were purchased successfully." << endl;
	outfile.close();
	} else {
		cout << "Tickets were not purchased." << endl;
	}
}