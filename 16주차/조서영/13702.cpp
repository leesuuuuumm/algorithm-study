#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main()
{
    int n, k;
    cin >> n >> k;
    vector<long long> ml;
    for(int i = 0; i < n; i++){
        long long a;
        cin >> a;
        ml.push_back(a);
    }
    int pf = 1;
    long long pl = ml[0]; 
    long long pk;
    long long result;
    
    while(pf <= pl){ // 이분 탐색에 관한 생각 오류... 까먹은 거
        long long cnt = 0;
        pk = (pf+pl) / 2;
        for(int i = 0; i < n; i++){
            cnt += ml[i]/pk; // 전혀 생각하지 못한 코드
        }
        if(cnt < k){ // 크다
            pl = pk - 1;
        }
        else{ // 작다
            result = pk; // cnt가 k보다 크니까 k명한테는 나누어 주었음 그 후 더 많은 용량을 나누어 줄 수 있는지 확인... 
            pf = pk + 1;  
        }
    }
    cout << result;
}