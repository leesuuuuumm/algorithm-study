#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int main()
{
    int N, K;
    cin >> N >> K;

    vector<int> level;

    for(int i = 0; i < N; i++){
        int x;
        cin >> x;
        level.push_back(x);
    }

    sort(level.begin(), level.end());

    int pf = *min_element(level.begin(), level.end());
    int pl = *max_element(level.begin(), level.end()) + K;

    long long hap = 0;
    int result = 0;

    while(pf <= pl){
        int pk = (pf + pl)/2;
        hap = 0;
        for(int i = 0; i < N; i++){
            if(level[i] <= pk){
                hap += (pk - level[i]);
            }
        }
        if(hap > K){
            pl = pk - 1;
        }
        else{
            result = pk;
            pf = pk + 1;
        }
        //cout << hap << " " << pk << '\n';
    }
    cout << result;
}