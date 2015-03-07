/*
 * The first thing done in the system
 * No transactions can be performed before a login
 * Returns index of username for use in main()
 */

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <vector>
#include "findUser.cpp"
#include "writeTransaction.cpp"

using namespace std;

int login(vector<string> users) {
	string line = "", username = "";
    
    userin:
	cout << "Enter your username: ";
	getline(cin, username);
    
    int index = findUser(users, username);
    if(index < 0){
        cout << "Error: username not found." << endl; 
        goto userin;
    }
    
    return index;
}
