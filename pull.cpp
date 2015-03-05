#include <stdio.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <string.h>

using namespace std;

const int col = 3;
int row=0;

int pull(vector<string> x, int i) {
	ifstream infile;
	string line = "", temp = "";
	char * t;


	// runs a loop to store usertext into a vector.
	if (i==0){infile.open("users.txt", ios::in);}
	else {infile.open("tickets.txt", ios::in);}


	while(getline(infile, line)) {

		t = new char [line.size()+1];
  		strcpy (t, line.c_str());

		temp = strtok(t,"_");
		x.push_back(temp);
		temp = strtok(NULL,"_");
		x.push_back(temp);
		temp = strtok(NULL,"_");
		x.push_back(temp);
		if(i==1){
			temp = strtok(NULL,"_");
			x.push_back(temp);
		}

		row++;
	}


	cout<<x[0]<<endl;
	cout<<x[1]<<endl;
	cout<<x[2]<<endl;
	cout<<x[3]<<endl;

	infile.close();
	
}
