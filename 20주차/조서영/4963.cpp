#include <iostream>
#include <queue>
#include <string.h>
 
using namespace std;
int map[51][51];
bool visit[51][51];
int w, h;
int dx[] = {0,0,-1,1,1,1,-1,-1}; // 대각선까지 고려해아함
int dy[] = {1,-1,0,0,1,-1,-1,1};
int cnt = 0;
void bfs(int a, int b){
    queue<pair<int, int>> q;
    q.push(make_pair(a, b));
    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 8;i++){ // 대각선 처리 8번 반복
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(nx >= 0 && nx < w && ny >=0 && ny < h){
                if(!visit[ny][nx] && map[ny][nx] == 1){
                    q.push(make_pair(nx, ny));
                    visit[ny][nx] = true;
                }
            }
        }
    }
}
int main(){
    while(1){
        cin >> w >> h;
        if(w==0 && h==0){
            break;
        }
        cnt = 0;
        memset(visit, false, sizeof(visit));
        queue<pair<int, int>> temp;
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w;j++){
                cin >> map[i][j];
                if(map[i][j]==1){
                    temp.push(make_pair(j,i)); // land만 저장
                } 
            }
        }
        while(!temp.empty()){// land를 다 밟을 때까지
            int tempx = temp.front().first;
            int tempy = temp.front().second;
            if(!visit[tempy][tempx]){ // 밟지 않은 땅일 경우
                visit[tempy][tempx] = true;
                bfs(tempx, tempy); // bfs호출
                cnt++; //bfs호출 횟수가 정답
            }
            temp.pop();
        }
        cout << cnt << '\n';
    }
    return 0;
}