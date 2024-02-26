#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

int main()
{
    int t;
    cin >> t;
    for(int i = 0; i < t; i++){
        stack<char> vps;
        string a;
        cin >> a;
        for(int j = 0; j < a.size(); j++){
            if(vps.empty()){
                vps.push(a[j]);
                continue;
            }
            if(a[j] == ')' && vps.top() == '('){
                vps.pop();
            }
            else{
                vps.push(a[j]);
            }
        }
        if(vps.empty()){
            cout << "YES" << endl;
        }
        else{
            cout << "NO" << endl;
        }
    };
}