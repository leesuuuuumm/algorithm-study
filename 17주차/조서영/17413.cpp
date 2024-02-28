#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

int main()
{
    string s;
    getline(cin, s);
    // cin >> s; // cin은 공백을 받지 못한다
    stack<char> results;
    for(int i = 0; i < s.size(); i++){
        if(s[i] == '<'){
            if(results.empty() == 0){
                while(results.empty() != 1){
                    cout << results.top();
                    results.pop();
                }
            }
            cout << s[i];
            while(s[i] != '>'){
                i++;
                cout << s[i];
            }
            continue;
        }

        if(i == (s.size() - 1)){
            results.push(s[i]);
            while(results.empty() != 1){
                cout << results.top();
                results.pop();
            }
        }

        if(s[i] != ' '){
            results.push(s[i]);
        }
        else if(s[i] == ' '){
            while(results.empty() != 1){
                cout << results.top();
                results.pop();
            }
            cout << " ";
        }
    }
}