/*
 * Adds credit to a user account
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void addcredit(vector<string> users, string self) {
	string user = "", input = "", str = "", type = "";
	int credit = 0;
    
    int index = findUser(users, self);
    type = users[index+1];
    if(type.compare(users[index+1]) == 0){
		//take in username
		userin: cout << "Enter the username of the account you wish to add credit to: ";
		getline(cin, user);
        int i = findUser(users, user);
	    if(i < 0) {
            cout << "Error: user not found." << endl;
            goto userin;
        }
        type = users[i+1];
    }

	//take in credit amount
	creditin: cout << "Enter the amount of credit to add: ";
	getline(cin, str);
	stringstream stream(str);
   	stream >> credit;
    if(credit > 1000){
        cout << "Error: you cannot add this much credit." << endl;
        goto creditin;
    }

	cout << "Are you sure you want to add this amount? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
        writeUserTransaction("06", user, type, credit);
	} else {
		cout << "Credits were not added." << endl;
	}
}
