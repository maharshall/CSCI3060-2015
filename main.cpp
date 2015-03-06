/*
 * The main file which will contain the loop for inputs
 * This will be the main hub for testing functions
 */

#include "main.h"
 vector <string> userdata;
 vector <string> tickdata;

int main() {
	//
  pull(userdata,0);
  pull(tickdata,1);

	//login here
	//set these values based on user type
	bool buyb = false; bool sellb = false; bool adminb = true;
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
				if(adminb) {
					create(userdata);
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
                break;
			case 2:
				if(adminb) {
					llamaDelete();
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
                break;
			case 3:
				if(buyb) {
					buy(tickdata, userdata);
				} else {
                    cout << "Error: you do not have permission to do that." << endl;
                }
                break;
			case 4:
				if(sellb) {
					sell();	
				}break;
			case 5:
				if(adminb) {
					refund(userdata);
				} else {
					cout << "Error: you do not have permission to do that." << endl;
				}
                break;
			case 6:
				addcredit(adminb);
                break;
			default:
				cout << "Invalid input." << endl;
                break;
			break;
		}
	}
}
