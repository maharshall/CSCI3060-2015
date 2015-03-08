/*
 * Prints the end of transaction pattern to the daily.txt file
 */


#include <stdio.h>
#include <iostream>

using namespace std;


void logout (){
    writeUserTransaction("00", "END OF SESSION", "00", 0);    
}
