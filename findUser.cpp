/*
 * Used to find a username in the userdata vector
 * Returns the index of the username in the vector
 */

 #include <stdio.h>

 using namespace std;

 int findUser(vector<string> users, string username) {
    int pad = 15 - username.length();
    username.append(pad, '-');
    for(int i = 0; i < users.size(); i+=3) {
        if(username.compare(users[i]) == 0) {
            return i;
        }
    }
    return -1;
 }
