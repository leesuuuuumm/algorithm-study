#include <iostream>
#include <set>

using namespace std;

set<int> S;
set<int> S1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    long long M;
    cin >> M;
    for(long long i = 0; i < M; i++){
        string str;
        int x;
        cin >> str;
        if(str[0] == 'a'){
            if(str[1] == 'd'){
                cin >> x;
                if(!S.count(x))
                    S.insert(x);
            }
            else{
                S.swap(S1);
                S1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            }
        }
        else if(str[0] == 'r'){
            cin >> x;
            if(S.count(x))
                S.erase(x);
        }
        else if(str[0] == 'c'){
            cin >> x;
            if(S.count(x))
                cout << 1 << '\n';
            else
                cout << 0 << '\n';
        }
        else if(str[0] == 't'){
            cin >> x;
            if(S.count(x))
                S.erase(x);
            else
                S.insert(x);
        }
        else if(str[0] == 'e'){
            S.clear();
        }
    }
}