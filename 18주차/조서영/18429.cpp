#include <iostream>
#define MAX 9
using namespace std;

int n, k;
int kit[MAX];
bool chk[MAX];
int cnt=0;

void Input(){
    cin>>n>>k;
    for(int i=0; i<n; i++){
        cin>>kit[i];
    }
}

void DFS(int day, int cur){
    if(day==n-1){
        if(cur<500){
            return;
        }
        else{
            cnt++;
            return;
        }
    }
    for(int i=0; i<n; i++){
        if(!chk[i]&&cur-k+kit[i]>=500){
            chk[i]=true;
            DFS(day+1, cur-k+kit[i]);
            chk[i]=false;
        }
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    Input();
    DFS(0, 500);
    cout<<cnt<<endl;
    return 0;
}