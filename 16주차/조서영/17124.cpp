#include <iostream>
#include <algorithm>
#include <vector>
#include <numeric> // 첨봄
#define MAX_S 1000000

using namespace std;

int A[MAX_S];
int B[MAX_S];

int main()
{
    int T;
    cin >> T;
    for(int i = 0; i < T; i++){
        int n, m;
        cin >> n >> m;

        for(int j = 0; j < n; j++)
            cin >> A[j];

        for(int j = 0; j < m; j++)
            cin >> B[j];

        sort(B, B + m);

        long long hap = 0;
        int num = 0;
        
        for(int j = 0; j < n; j++){
            if(A[j] >= B[m - 1])
                num = B[m - 1];
            else if(A[j] <= B[0])
                num = B[0];
            else{
                int pf = 0;
                int pl = m - 1;
                int min_C = B[m - 1];
                while(pf <= pl){
                    int pk = (pf+pl)/2;
                    //cout << A[j] << " " << B[pk] << '\n';
                    if(min_C > abs(A[j] - B[pk])){
                        num = B[pk];
                        min_C = abs(A[j] - B[pk]);
                    }
                    else if(min_C == abs(A[j] - B[pk])){ // 항상 작은 게 보장x... 반례를 찾아보자
                        if(A[j] < num)
                            num = B[pk];
                    }
                    if(A[j] >= B[pk])
                        pf = pk + 1;
                    else
                        pl = pk - 1;
                }
            }
            hap += num;
            /*vector<int> mid;
            for(int k = 0; k < m; k++){
                mid.push_back(abs(A[j] - B[k]));
            }
            C.push_back(B[min_element(mid.begin(), mid.end()) - mid.begin()]);*/
        }
        /*for(int j = 0; j < n; j++){
            cout << C[j] << " ";
        }
        cout << '\n';*/
        cout << hap << '\n';
    }
}