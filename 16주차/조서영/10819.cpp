#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int main()
{
    int N;
    cin >> N;

    vector<int> A;

    for(int i = 0; i < N; i++){
        int a;
        cin >> a;
        A.push_back(a);
    }

    sort(A.begin(), A.end());

    int result = 0;

    while(next_permutation(A.begin(), A.end())){
        int hap = 0;
        for(int i = 0; i < A.size() - 1; i++){
            hap += abs(A[i] - A[i+1]);
        }
        result = max(result, hap);
    }
    cout << result;
}