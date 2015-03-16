#include <stdio.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <string.h>

using namespace std;

int pull(vector<string> &x, int i) {
	ifstream infile;
	string line = "", temp = "";
	char * t;


	// runs a loop to store usertext or tickets.txt into a vector.
	if (i==0){infile.open("../users.txt", ios::in);}
	else {infile.open("../tickets.txt", ios::in);}


	// while there are still more lines
	// store the information into a vector.
	// closes the inputfilereader afterwards
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
	}
	infile.close();
	
}
