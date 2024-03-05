#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> stack;

void push(int a){
    stack.push_back(a);
}

int pop(){
    int a;
    if(stack.empty() == true){
        a = -1;
    }
    else{
        a = stack.back();
        stack.pop_back();
    }
    
    return a;
}

int size(){
    return stack.size();
}

int empty(){
    int a = 0;
    if(stack.empty() == true){
        a = 1;
    }
    else{
        a = 0;
    }
    return a;
}

int top(){
    int a;
    if(stack.empty() == true){
        a = -1;
    }
    else{
        a = stack.back();
    }
    return a;
}

int main()
{
    int n;
    cin >> n;
    for(int i = 0; i < n; i++){
        string a;
        int b;
        cin >> a;
        if(a == "push"){
            cin >> b;
            push(b);
        }
        else if(a == "pop"){
            cout << pop() << '\n';
        }
        else if(a == "size"){
            cout << size() << '\n';
        }
        else if(a == "empty"){
            cout << empty() << '\n';
        }
        else if(a == "top"){
            cout << top() << '\n';
        }
    }
}