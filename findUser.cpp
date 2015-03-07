/*
 * Used to find a username in the userdata vector
 * Returns the index if the username in the vector
 */

 #include <stdio.h>

 using namespace std;

 int findUser(vector<string> users, string username) {
    for(int i = 0; i < users.size(); i+=3) {
        if(username == users[i]) {
            return i;
        }
    }
    cout << "Error: user not found." << endl;
    return -1;
 }
