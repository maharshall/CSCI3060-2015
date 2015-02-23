/*
 * The main file which will contain the loop for inputs
 * This will be the main hub for testing functions
 */

#include "main.h"
 vector <string> userdata;

int main() {
	//
	pull(userdata,0);

	//login here
	//set these values based on user type
	bool buy = false; bool sell = false; bool admin = true;
	cout << "Welcome to Llama TSS. Please login." << endl;
	if(login() != 1) {
		cout << "Error: username not found." << endl;
		return 0;
	}

	//check username for type and set bools

	string input = "";
	int in = -1;
	while(in != 0) {
		cout << "0. Logout" << endl;
		cout << "1. Create" << endl;
		cout << "2. Delete" << endl;
		cout << "3. Buy" << endl;
		cout << "4. Sell" << endl;
		cout << "5. Refund" << endl;
		cout << "6. Add Credit" << endl << endl;
		cout << "Enter a numerical option: ";

		getline(cin, input);
   	stringstream stream(input);
   	stream >> in;

		switch(in) {
			case 0:
				logout();
				cout << "Thank you for using Llama TSS. Goodbye." << endl;
				return 0;
			case 1:
				if(admin == true) {
					create();
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
			case 2:
				if(admin == true) {
					llamaDelete();
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
			case 3:
				if(buy) {
					buy();
				}
			case 4:
				if(sell) {
					sell();	
				}
			case 5:
				if(admin == true) {
					refund();
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
			case 6:
				addcredit(admin);
			default:
				cout << "Invalid input." << endl;
			break;
		}
	}
}