#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

int main()
{
    string bar;
    cin >> bar;
    int cnt = 0;
    int barcnt = 0;
    stack<char> cut;
    for(int i = 0; i < bar.size(); i++){
        if(bar[i] == '('){
            if(bar[i+1] != ')'){
                barcnt++;
            }
        }
        if(bar[i] == ')'){
            if(bar[i-1] != '('){
                cut.pop();
                barcnt--;
                cnt++;
            }
            else if(cut.top() == '('){
                cut.pop();
                if(barcnt != 0){
                    cnt += barcnt;
                }
            }
        }
        else{
            cut.push(bar[i]);
        }
    }
    cout << cnt;
}