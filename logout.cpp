/*
 * Prints the end of transaction pattern to the daily.txt file
 */


#include <stdio.h>
#include <iostream>
#include <fstream>

using namespace std;


void logout (){
    writeUserTransaction("00", "000000000000000", "00", 0);    
}
