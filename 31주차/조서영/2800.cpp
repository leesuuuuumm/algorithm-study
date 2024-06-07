#include <iostream>
#include <algorithm>
#include <string>
#include <cstring>
#include <cmath>
#include <vector>
#include <queue>
#include <stack>
#include <set>

using namespace std;

// 2800 - 괄호 제거
string str;
vector<int> isSelected, isVisited(10, false); // 전체 문자열에서 쓰인 인덱스, info에서 쓰인 인덱스
vector<pair<int, int>> info; // 열린 괄호, 닫힌 괄호 쌍의 인덱스 저장
set<string> result;

void input(){
    cin >> str;
    isSelected.assign(str.length(), false);

    stack<int> stk;
    for(int i = 0; i < str.length(); i++){
        if(str[i] == '('){
            stk.push(i);
        }
        else if(str[i] == ')'){
            int idx = stk.top();
            stk.pop();
            info.emplace_back(make_pair(idx, i));
        }
    }
}

void DFS(int idx, int cnt){
    if(cnt >= 1){ // 선택(제거)된 괄호의 쌍이 1개 이상이라면
        string s = "";
        for(int i = 0; i < str.length(); i++){
            if(isSelected[i])   continue;
            s += str[i];
        }
        if(!result.count(s)){
            result.insert(s);
        }
    }

    for(int i = idx; i < info.size(); i++){
        if(isVisited[i])    continue;
        isVisited[i] = true;
        isSelected[info[i].first] = true;
        isSelected[info[i].second] = true;

        DFS(i + 1, cnt + 1);

        isVisited[i] = false;
        isSelected[info[i].first] = false;
        isSelected[info[i].second] = false;
    }
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    DFS(0, 0);

    for(auto iter = result.begin(); iter != result.end(); iter++){
        cout << *iter << '\n';
    }
    
    return 0;
}