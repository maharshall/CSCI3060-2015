/*
 * Deletes an existing user.
 * We couldn't call it delete.cpp because delete is a c++ keyword
 */

#include <stdio.h>
#include <iostream>
#include <stdlib.h>

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
    int i = findUser(users, username);
    if(i < 0){
        cout << "Error: user not found." << endl;
        goto userin;
    }
    type = users[i+1];
    char buffer[256];
    strcpy(buffer, users[i+2].c_str());
    int credit = atoi(buffer);

	cout << "Are you sure you want to delete this user? (Y/N):";
	getline(cin, input);

	if(input == "y" || input == "Y") {
        writeUserTransaction("02", username, type, credit);
		cout << "User was deleted successfully." << endl;	
	} else {
		cout << "User was not deleted." << endl;
	}
}
