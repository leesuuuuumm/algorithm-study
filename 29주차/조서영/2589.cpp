#include <iostream>
#include <queue>
#include <tuple>
using namespace std; 
int map[50][50]; 
int check[50][50]; 
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 }; 
int h, w;
int result = -1; 
queue <pair<int, int>> q; 
void bfs() {
	int x, y, nx, ny;
	while (q.size()) {
		tie(y, x) = q.front();
		q.pop(); 
		for (int i = 0; i < 4; i++) {
			nx = x+ dx[i]; 
			ny = y+ dy[i]; 
			if (nx < 0 || nx >= w || ny < 0 || ny >= h)continue; //map 범위 넘었을 때
			if (map[ny][nx] == 0 || check[ny][nx])continue; //바다거나 이미 체크된곳이면
			check[ny][nx] = check[y][x] + 1;//현지점+1 값을 조건 만족시 상하좌우에 갱신.
			result = max(check[ny][nx], result); //result 값 비교해서 최댓값이면 갱신.
			q.push({ ny,nx }); //queue에 다시 집어넣기
		}
	}
}

int main() {
	cin >> h >> w; 
	for (int i = 0; i < h; i++) {
		string s = ""; cin >> s; 
		for (int j = 0; j < w; j++) {
			if (s[j] == 'W')map[i][j] = 0; 			
			else map[i][j] = 1; 			
		}
	}
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			if (map[i][j] == 1) {
				check[i][j] = 1; 
				q.push({ i,j });
				bfs(); 
				for (int k = 0; k < h; k++) {
					for (int z = 0; z < w; z++) {
						check[k][z] = 0; 
					}
				}
			}
		}
	}
	cout << result-1; 
}