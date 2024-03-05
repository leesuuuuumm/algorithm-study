#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> queue;

void push(int a){
    queue.push_back(a);
}

int pop(){
    int a;
    if(queue.empty() == true){
        a = -1;
    }
    else{
        a = queue.front();
        queue.erase(queue.begin());
    }
    
    return a;
}

int size(){
    return queue.size();
}

int empty(){
    int a = 0;
    if(queue.empty() == true){
        a = 1;
    }
    else{
        a = 0;
    }
    return a;
}

int front(){
    int a;
    if(queue.empty() == true){
        a = -1;
    }
    else{
        a = queue.front();
    }
    return a;
}

int back(){
    int a;
    if(queue.empty() == true){
        a = -1;
    }
    else{
        a = queue.back();
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
        else if(a == "front"){
            cout << front() << '\n';
        }
        else if(a == "back"){
            cout << back() << '\n';
        }
    }
}