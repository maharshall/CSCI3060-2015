/*
 * Adds credit to a user account
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void addcredit(bool admin, vector<string> users) {
	string user = "", input = "", str = "";
	int credit = 0;
	ofstream outfile;
	ifstream infile;

	if(admin) {
		//take in username
		userin: cout << "Enter the username of the account you wish to add credit to: ";
		getline(cin, user);
	    if(findUser(users, user) < 0) {
            goto userin;
        }
    }	

	//take in credit amount
	creditin: cout << "Enter the amount of credit to add: ";
	getline(cin, str);
	stringstream stream(str);
   	stream >> credit;

	cout << "Are you sure you want to add this amount? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
	//write to daily.txt
	outfile.open("daily.txt", ios::out | ios::app);
		outfile << "06_user_type_credit" << endl;
		cout << "Credits were added successfully." << endl;
	outfile.close();
	} else {
		cout << "Credits were not added." << endl;
	}
}
