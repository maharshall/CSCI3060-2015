/*
 * Transfers credit from a seller to a buyer
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void refund(vector<string> users) {
	string seller = "", buyer = "", input = "", str = "";
	int credit = 0;
	ofstream outfile;
	ifstream infile;

	//take in buyer username
	buyerin: cout << "Enter the buyer's username: ";
	getline(cin, buyer);
	for(int i = 0; i < users.size(); i+=3) {
        if(buyer == users[i]){
            break;
        }
        if(i+3 >= users.size()){
            cout << "Error: buyer not found." << endl;
            goto buyerin;
        }
    }

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	getline(cin, seller);
	for(int i = 0; i < users.size(); i+=3) {
        if(seller == users[i]){
            break;
        }
        if(i+3 >= users.size()){
            cout << "Error: seller not found." << endl;
            goto sellerin;
        }
    }

	//take in quantity of credits
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
