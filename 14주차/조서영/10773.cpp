#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

int main()
{
    int k;
    cin >> k;
    stack<int> stack;
    int hap = 0;
    for(int i = 0; i < k; i++){
        int a;
        cin >> a;
        if(a == 0){
            stack.pop();
            continue;
        }
        stack.push(a);
    }
    int s = stack.size();
    for(int i = 0; i < s; i++){
        hap += stack.top();
        stack.pop();
    }
    cout << hap;
}