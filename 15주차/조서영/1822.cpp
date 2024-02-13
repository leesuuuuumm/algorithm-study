#include <iostream>
#include <set>
#include <vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int nA, nB;
    cin >> nA >> nB;

    set<int> A;
    set<int> B;
    int cnt = 0;
    vector<int> result;

    for(int i = 0; i < nA; i++){
        int a = 0;
        cin >> a;
        A.insert(a);
    }

    for(int i = 0; i < nB; i++){
        int a = 0;
        cin >> a;
        B.insert(a);
    }

    set<int>::iterator it = A.begin();

    for(it; it != A.end(); it++){
        if(!B.count(*it)){
            cnt++;
            result.push_back(*it);
        }
    }

    if(cnt == 0)
        cout << '0';
    else{
        cout << cnt << '\n';
        for(int i = 0; i < cnt; i++){
            cout << result[i] << ' ';
        }
    }
}