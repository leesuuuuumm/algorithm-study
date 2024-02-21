#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#define MAX 1000000000

using namespace std;

int main()
{
    long long N, M;
    cin >> N >> M;

    vector<int> tree;

    for(int i = 0; i < N; i++){
        int a;
        cin >> a;
        tree.push_back(a);
    }

    sort(tree.begin(), tree.end(), greater<>());

    int pf = 0;
    int pl = tree[0];

    int result = 0;
    long long hap;

    while(pf <= pl){
        hap = 0;
        int pk = (pf + pl)/2;
        for(int i = 0; i < N; i++){
            if(tree[i] >= pk)
                hap += (tree[i] - pk);
        }
       // cout << pk << '\n';
        if(hap >= M){
            result = pk;
            pf = pk + 1;
        }
        else if(hap < M){
            pl = pk - 1;
        }
    }
    cout << result;
}