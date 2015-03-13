/*
 * Creates a new user with purchasing and/or selling privileges
 * Writes to the daily.txt file in the format UUUUUUUUUUUUUUU_TT_CCCCCCCCC
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void create(vector<string> userdata) {
	string username = "", type = "", input = "", line = "";
	ofstream outfile;
	ifstream infile;

	//take in username, reject if >15 characters or already exists
	userin: 
	cout << "Enter the desired username: ";
    cin >>username;
	if(username.length() > 15) {
		cout << "Error: username cannot exceed 15 characters." << endl;
		goto userin;
	}

	//check to see if user already exists
    if(findUser(userdata, username) > -1){
        cout << "Error: username already in use." << endl;
        goto userin;
    }	

	//take in type, reject if not exact match to valid type
	typein: cout << "Enter the account type (AA, FS, BS, SS): ";
	cin >> type;
	if(!(type == "AA" || type == "FS" || type == "BS" || type == "SS")) {
		cout << "Error: invalid acount type." << endl;
		goto typein;
	}

	//confirm with user that they want to complete the transaction
	cout << "Are you sure you want to create this user? (Y/N):";
	cin >> input;

	if(input == "y" || input == "Y") {
	    writeUserTransaction("01", username, type, 0);
    } else {
		cout << "User was not created." << endl;
	}
}
