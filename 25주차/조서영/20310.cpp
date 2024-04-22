#include <bits/stdc++.h>
 
using namespace std;
 
int zero;
int one;
int checkErase[501];
 
int main() {
    string str;
    string ans = "";
    cin >> str;
    
    for(int i = 0; i < str.size(); i++) {
        if(str[i] == '0') zero++;
        else one++;
    }
    
    int zeroCnt = 0;
    for(int i = str.size() - 1; i >= 0; i--) {
        if(zeroCnt == zero/2) break;
        if(str[i] == '0') {
            checkErase[i] = true;
            zeroCnt++;
        }
    }
    
    int oneCnt = 0;
    for(int i = 0; i < str.size(); i++) {
        if(oneCnt == one/2) break;
        if(str[i] == '1') {
            checkErase[i] = true;
            oneCnt++;
        }
    }
    
    for(int i = 0; i < str.size(); i++) {
        if(checkErase[i]) continue;
        ans += str[i];
    }
    
    cout << ans;
}