#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main()
{
    int n, s;
    cin >> n >> s;
    vector<int> num;
    int low = 0;
    int high = 0;
    for(int i = 0; i < n; i++){
        int a;
        cin >> a;
        num.push_back(a);
    }
    int len = n + 1;
    int sum = num[0];
    while(low <= high && high <= n-1){
        if(sum < s){
            sum += num[++high];
        }
        else{
            len = min(len, high - low + 1);
            sum -= num[low++];
        }
    }
    if(len == n + 1){
        cout << "0";
    }
    else{
        cout << len;
    }
    
}