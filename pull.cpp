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

	infile.open("users.txt", ios::in);
	while(getline(infile, line)) {

		t = new char [line.size()+1];
  		strcpy (t, line.c_str());

		temp = strtok(t,"_");
		x.push_back(temp);
		temp = strtok(NULL,"_");
		x.push_back(temp);
		temp = strtok(NULL,"_");
		x.push_back(temp);
		row++;
	}
	infile.close();

	
}
