/*
 * The first thing done in the system
 * No transactions can be performed before a login
 * Returns index of username for use in main()
 */

#include <stdio.h>
#include <iostream>
#include <vector>
#include "findUser.cpp"
#include "writeTransaction.cpp"

using namespace std;
int loginattempt=0;

int login(vector<string> users) {
	string line = "", username = "";
  
    userin:

    if (loginattempt>3){
        cout<<"Number of Login Attempts exceeded."<<endl;
        return -2;
    }

	cout << "Enter your username: ";
	getline(cin, username);
    
    int index = findUser(users, username);
    if(index < 0){
        cout << "Error: username not found." << endl; 
        cout<<"Login attemps remaining: "<<3 - loginattempt<<endl;
        loginattempt++;
        goto userin;
    }
    
    return index;
}
