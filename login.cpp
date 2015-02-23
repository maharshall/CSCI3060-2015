/*
 * The first thing done in the system
 * No transactions can be performed before a login
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

int login() {
	ifstream infile;
	string line = "", username = "";;

	cout << "Enter your username: ";
	getline(cin, username);

	infile.open("users.txt", ios::in);
	while(getline(infile, line)) {
		if(line.find(username) != string::npos) {
			cout << "Login successfull." << endl;
			return 1;
		}
	}
	infile.close();
	return -1;
}