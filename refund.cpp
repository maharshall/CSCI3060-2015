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
	int bIn,sIn;
	int sellC, useC;

	//take in buyer username
	buyerin: cout << "Enter the buyer's username: ";
	cin>>buyer;
	bIn = findUser(users, buyer);
    if(bIn < 0){
        cout << "Error: buyer not found." << endl;
        goto buyerin;
    }	

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	cin>>seller;
	sIn = findUser(users, seller);
    if(sIn < 0){
        cout << "Error: seller not found." << endl;
        goto sellerin;
    }

	//take in quantity of credits
	quantin: cout << "Enter the amount of credit to refund: ";
    cin >> credit;	

    // catch credit errors

    if (credit >1000 || credit <0){
    	cout<<"Error: session maximum exceeded."<<endl;
    	goto quantin;
    }

    useC = atoi(users[bIn+2].c_str());
    if(credit+useC>999999){
        cout<<"Error: buyer cannot have more than 999999 credit."<<endl;
        goto quantin;
    }

    sellC = atoi(users[sIn+2].c_str());
    if(sellC-credit<0){
        cout<<"Error: Cannot have give more than owned."<<endl;
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
