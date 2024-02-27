#include <iostream>
#include <algorithm>
#include <string> // 원칙적으로는 쓰는 게 맞다 쓰는 습관을 들이자
#include <vector>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int K, N;
    cin >> K >> N;
    vector<int> lan;
    for(int i = 0; i < K; i++){
        int a;
        cin >> a;
        lan.push_back(a);
    }
    sort(lan.begin(), lan.end());
    long long pf = 1; // 롱롱이 왜맞지? 즉 int가 왜 아니지?
    long long pl = lan[K-1];
    long long max = 0;
        while(pf <= pl){
            long long cnt = 0;
            long long pk = (pf+pl) / 2;
            for(int i = 0; i < K; i++){
                cnt += (lan[i] / pk);
            }
            if(cnt < N){
                pl = pk - 1;
            }
            else{
                if(max < pk)
                    max = pk;
                pf = pk + 1;
            }
        }
    cout << max;
}