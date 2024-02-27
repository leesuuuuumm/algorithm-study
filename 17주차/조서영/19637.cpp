#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

int main()
{
    ios::sync_with_stdio(false); // 이 코드가 없으면
    cin.tie(NULL); // 시간 초과가 난다...
    int N, M;
    cin >> N >> M;
    vector<string> style;
    vector<int> power;
    for(int i = 0; i < N; i++){
        string s;
        int a;
        cin >> s >> a;
        style.push_back(s);
        power.push_back(a);
    }
    for(int i = 0; i < M; i++){
        int i_power;
        cin >> i_power;
        if(i_power <= power[0]){
            cout << style[0] << '\n';
        }
        else{
            int pf = 1;
            int pl = N - 1;
            string result;
            while(pf <= pl){
                int pk = (pf + pl) / 2;
                if(power[pk] < i_power){
                    pf = pk + 1;
                }
                else{
                    result = style[pk];
                    pl = pk - 1;
                }
            }
            cout << result << '\n';
        }
    }
}