/*
 * Felicitates the purchasing of tickets
 */

#include <stdio.h>
#include <iostream>

using namespace std;

void buy(vector<string> tickets, vector<string> users, int index) {
	string seller = "", event = "", input = "", str = "";
	int quantity = 0, price = 0, eventnum = 0,eprice = 0;
	ofstream outfile;
	ifstream infile;
    string temp;

	//take in event name
	eventin: cout << "Enter the event name: ";
	cin.clear();
    getline(cin, event);
    
    //pad the event name so it compares correctly
    int pad = 25 - event.length();
    temp = event;
    temp.append(pad, '-');

    // find the the event and get its index
	for(int i = 0; i < tickets.size(); i+=4) {
        if(temp.compare(tickets[i]) == 0){
            str = tickets[i+3];
            eventnum = i;
            break ; 
        } 
        if(i+4 >= tickets.size()){
            cout << "Error: event not found." << endl;
            goto eventin;
        }
    }

    stringstream stream(str);
    stream >> price;

	//take in seller username
	sellerin: cout << "Enter the seller's username: ";
	getline(cin, seller);
    if(findUser(users, seller) < 0){
        cout << "Error: seller not found." << endl;
        goto sellerin;
    }    

	//take in quantity of tickets
	quantin: cout << "Enter the amount of tickets you wish to buy: ";
    cin >> quantity;	

    //quantity cannot exceed maximum
	if(quantity > 4) {
		cout << "Error: ticket quantity cannot exceed 4." << endl;
		goto quantin;
	}

    //check if buyer has enuough money
    eprice = atoi(users[index+2].c_str());
    quantity = quantity * atoi(tickets[eventnum+3].c_str());
    if (eprice<quantity){
        cout<<"Error: Insufficient funds"<<endl;
        return;
    }

    //check to see requested amound does not exceed availability
    for(int i = 0; i < tickets.size(); i+=3 ){
        if(event.compare(tickets[i]) == 0){
            str = tickets[i+2];
            int available = 0;
            stream >> available;
            if(quantity > available){
                cout << "Error: ticket quantity exceeds availability." << endl;
                goto quantin;
            }
        }
    }


    //Confirm they want to complete this transaction.
	cout << "Are you sure you want to buy these tickets? (Y/N):";
    cin>>input;

	if(input == "y" || input == "Y") {
	   writeSalesTransaction("04", event, seller, quantity, price); 
	} else {
		cout << "Tickets were not purchased." << endl;
	}
}
