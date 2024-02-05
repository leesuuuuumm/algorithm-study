#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n, m;
int arr[9] = {0, };
bool vis[9] = {0, };

void dfs(int num, int cnt){
    if(cnt == m){
        for(int i = 0; i < m; i++)
            cout << arr[i] << " ";
        cout << '\n';
        return;
    }
    for(int i = num; i <= n; i++){
        if(!vis[i]){
            vis[i] = true;
            arr[cnt] = i;
            dfs(i+1, cnt+1);
            vis[i] = false;
        }
    }
}

int main(){
    cin >> n >> m;
    dfs(1, 0);
}