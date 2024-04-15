#include <iostream>
#include <cmath>
using namespace std;
int n;
int one = 0;
int m_one = 0;
int ze = 0;
int map[2200][2200];
void papper(int x, int y, int size){
    bool same = true;
    for (int i = y; i < y+size;i++){ //각 분면 탐색
        for (int j = x; j < x + size;j++){
            if(map[i][j] != map[y][x]){
                same = false; // 원소 하나라도 다르면 false
                break;
            }
        }
        if(!same){
            break;
        }
    }
    if(!same){
        papper(x, y, size / 3); // 1번
        papper(x+size/3, y, size / 3); // 2번
        papper(x+size/3+size/3, y, size / 3); // 3번
        papper(x, y+size/3, size / 3); // 4번
        papper(x, y+size/3+size/3, size / 3); // 7번
        papper(x+size/3, y+size/3, size / 3); // 5번
        papper(x+size/3+size/3, y+size/3+size/3, size / 3); // 9번
        papper(x+size/3, y+size/3+size/3, size / 3); // 8번
        papper(x+size/3+size/3, y+size/3, size / 3); // 6번
    }else{
        if(map[y][x] == 1){
            one++; // 1인 경우
        }else if(map[y][x] == -1){
            m_one++; // -1인 경우
        }else{
            ze++; // 0인 경우
        }
    }
}
 
int main(){
    ios::sync_with_stdio(false);
    cin >> n;
    for (int i = 0; i < n;i++){
        for (int j = 0; j < n;j++){
            cin >> map[i][j];
        }
    }
    papper(0, 0, n);
    cout << m_one << '\n';
    cout << ze << '\n';
    cout << one << '\n';
    return 0;
}