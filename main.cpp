/*
 * The main file which will contain the loop for inputs
 * This will be the main hub for testing functions
 */

#include "main.h"

int main() {

	//login here
	cout << "Welcome to TSS. Please login." << endl;
	if(login() != 1) {
		cout << "Error: username not found." << endl;
		return 0;
	}


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
				//logout();
				cout << "Thank you for using TSS. Goodbye." << endl;
				return 0;
			case 1:
				create();
			case 2:
				//delete();
			case 3:
				//buy();
			case 4:
				//sell();
			case 5:
				//refund();
			case 6:
				//addcredit();
			default:
				cout << "Invalid input." << endl;
			break;
		}
	}
}