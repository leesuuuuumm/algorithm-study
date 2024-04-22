#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int c,r;
char map[21][21];
vector<string> v;

void row_word(){ //가로로 만들어지는 단어 (단어 길이가 1이면 안됌)
    
    for(int i = 0;i<c;i++){
        string str = "";
        bool find_word = false;
        
        for(int j = 0;j<r;j++){
            if(map[i][j] == '#' && str.size() >= 2){
                find_word = true;
                v.push_back(str);
                str = "";
                continue;

            }
            else if(map[i][j] == '#' && str.size() < 2){
                str = "";
                continue;
            }
            else{
                str += map[i][j];
            }
        }
        if(find_word == false && str.size() >= 2){
            v.push_back(str);
        }
    }
    
}


void column_word(){ //세로로 만들어지는 단어
    for(int i = 0;i<r;i++){
        bool find_word = false;
        string str = "";
        
        for(int j = 0;j<c;j++){
            if(map[j][i] == '#' && str.size() >= 2){
                find_word = true;
                v.push_back(str);
                str = "";
                continue;
            }
            else if(map[j][i] == '#' && str.size() < 2){
                str = "";
                continue;
            }
            else{
                str += map[j][i];
            }
        }
        if(find_word == false && str.size() >= 2){
            v.push_back(str);
        }
    }
}

int main(void){
    cin >> c >> r;
    for(int i = 0;i<c;i++){
        for(int j = 0;j<r;j++){
            cin >> map[i][j];
        }
    }
    
    row_word();
    column_word();
    sort(v.begin(),v.end());
    cout << v[0];
}