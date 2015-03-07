/*
 * Deletes an existing user.
 * We couldn't call it delete.cpp because delete is a c++ keyword
 */

#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;

void llamaDelete(vector<string> users, string self) {
	string username = "", type = "", input = "", line = "";
	ofstream outfile;
	ifstream infile;

    userin:
	cout << "Enter the name of the user you wish to delete: ";
	getline(cin, username);

	//check to see if user exists and is not self
    if(username == self){
        cout << "Error: cannot delte self." << endl;
        goto userin;
    } 
    if(findUser(users, username) < 0){
        cout << "Error: user not found." << endl;
        goto userin;
    }

	cout << "Are you sure you want to delete this user? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
        writeUserTransaction("02", username, "", 0);
		cout << "User was deleted successfully. RIP." << endl;	
	} else {
		cout << "User was not deleted." << endl;
	}
}
