/*
 * Transfers credit from a seller to a buyer
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void refund() {
	string seller = "", buyer = "", input = "", str = "";
	int credit = 0;
	ofstream outfile;
	ifstream infile;

	//take in buyer username
	buyerin: cout << "Enter the buyer's username: ";
	getline(cin, buyer);
	//find buyer

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	getline(cin, seller);
	//find seller

	//take in quantity of tickets
	quantin: cout << "Enter the amount of credit to refund: ";
	getline(cin, str);
	stringstream stream(str);
   	stream >> credit;

	cout << "Are you sure you want to refund this amount? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
	//write to daily.txt
	outfile.open("daily.txt", ios::out | ios::app);
		outfile << "05_buyer_seller_credit" << endl;
		cout << "Credits were refunded successfully." << endl;
	outfile.close();
	} else {
		cout << "Credit was not refunded." << endl;
	}
}