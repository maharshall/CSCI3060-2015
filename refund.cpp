/*
 * Transfers credit from a seller to a buyer
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void refund(vector<string> users) {
	string seller = "", buyer = "", input = "";
	int credit = 0;
	ofstream outfile;
	ifstream infile;

	//take in buyer username
	buyerin: cout << "Enter the buyer's username: ";
	cin>>buyer;
    if(findUser(users, buyer) < 0){
        cout << "Error: buyer not found." << endl;
        goto buyerin;
    }	

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	cin>>seller;
    if(findUser(users, seller) < 0){
        cout << "Error: seller not found." << endl;
        goto sellerin;
    }

	//take in quantity of credits
	quantin: cout << "Enter the amount of credit to refund: ";
    cin >> credit;	

    if (credit >1000){
    	cout<<"Error: session maximum exceeded."<<endl;
    	goto quantin;
    }

	cout << "Are you sure you want to refund this amount? (Y/N):";
	cin>>input;

	if(input == "y" || input == "Y") {
	    writeRefundTransaction(buyer, seller, credit);
	    cout << "Credits were refunded successfully." << endl;;
	} else {
		cout << "Credit was not refunded." << endl;
	}
}
