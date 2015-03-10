/*
 * Prints the end of transaction pattern to the daily.txt file
 */


#include <stdio.h>
#include <iostream>

using namespace std;


void logout (){
	// writes to the daily transaction file 
	// that a user has logged; out end of session
    writeUserTransaction("00", "END OF SESSION", "00", 0);    
}
