/*
 * Creates a new user with purchasing and/or selling privileges
 * Writes to the daily.txt file in the format UUUUUUUUUUUUUUU_TT_CCCCCCCCC
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void create() {
	string username = "", type = "", input = "", line = "";
	ofstream outfile;
	ifstream infile;

	//take in username, reject if >15 characters or already exists
	userin: cout << "Enter the desired username: ";
	getline(cin, username);
	if(username.length() > 15) {
		cout << "Error: username cannot exceed 15 characters." << endl;
		goto userin;
	}

	//check to see if user already exists
	infile.open("users.txt", ios::in);
	while(getline(infile, line)) {
		if(line.find(username) == string::npos) {
			cout << "Error: username already in use." << endl;
			goto userin;
		}
	}
	infile.close();

	//take in type, reject if not exact match to valid type
	typein: cout << "Enter the account type (AA, FS, BS, SS): ";
	getline(cin, type);
	if(!(type == "AA" || type == "FS" || type == "BS" || type == "SS")) {
		cout << "Error: invalid acount type." << endl;
		goto typein;
	}

	cout << "Are you sure you want to create this user? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
	//write to daily.txt
	outfile.open("daily.txt", ios::out | ios::app);
		outfile << "01_" << username << string(15-username.length(), '-') << "_" << type << "_" << string(9, '0') << endl;
		cout << "User was created successfully." << endl;
	outfile.close();
	} else {
		cout << "User was not created." << endl;
	}
}