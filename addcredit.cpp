/*
 * Adds credit to a user account
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void addcredit(vector<string> users, string self) {
	string user = "", input = "", type = "";
	int credit = 0;
    int useC = 0;
    
    // find the user to get its index checks if it is an admin
    // If they are admin,  allow them to specify who to add credit to
    int index = findUser(users, self);
    type = "AA";
    if(type.compare(users[index+1]) == 0){
		//take in username
		userin: cout << "Enter the username of the account you wish to add credit to: ";
		cin>>user;
        index = findUser(users, user);
	    if(index < 0) {
            cout << "Error: user not found." << endl;
            goto userin;
        }
        type = users[index+1];
    }
    else{
        user = self;
    }

	//take in credit amount
	creditin: cout << "Enter the amount of credit to add: ";
    cin >> credit;	

    //Credit checks to make sure credit is a proper value
    if(credit > 1000 || credit <0){
        cout << "Error: you cannot add this much credit." << endl;
        goto creditin;
    } 

    useC = atoi(users[index+2].c_str());
    if(credit+useC>999999){
        cout<<"Error: you cannot have more than 999999 credit "<<endl;
        goto creditin;
    }

    // Confirmation and output to the Transaction file
	cout << "Are you sure you want to add this amount? (Y/N):";
	cin>>input;

	if(input == "y" || input == "Y") {
        writeUserTransaction("06", user, type, credit);
	} else {
		cout << "Credits were not added." << endl;
	}
}
