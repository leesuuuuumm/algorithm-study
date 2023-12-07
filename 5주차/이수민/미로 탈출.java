import java.util.*;
class Solution {
     class Point{
        int r;
        int c;
        int cnt;
        
        public Point(int r, int c, int cnt){
            this.r =r;
            this.c =c;
            this.cnt =cnt;
            
        }
    }
    
    String[][] map;
    int[] dr = {0,1,-1,0};
    int[] dc = {1,0,0,-1};
   Point S;
		Point L;
		Point E;
    Queue<Point> que;
    boolean[][]v;
    int answer;
    int N,M;
   public int solution(String[] maps) {
		answer = 0;
		N = maps.length;
		M = maps[0].length();
		map = new String[N][M];

		que = new LinkedList<>();
		v = new boolean[N][M];
		for (int i = 0; i < maps.length; i++) {
			map[i] = maps[i].split("");
		}

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				if (map[r][c].equals("S")) {
					S = new Point(r, c, 0);
					que.offer(new Point(r, c, 0));
					v[r][c] = true;
				} else if (map[r][c].equals("E")) {
					E = new Point(r, c, 0);
				} else if (map[r][c].equals("L")) {
					L = new Point(r, c, 0);
				}
			}
		}

		// 레버 위치까지 bfs()
		BFS(L.r, L.c);
		int exit = BFS(E.r, E.c);
       
       

		answer = exit==0? -1:exit;
		return answer;
	}

	private int BFS(int er, int ec) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				// 레버 위치에서 출구까지 bfs()
				if (nr == er && nc == ec) {
					que.clear();
					v = new boolean[N][M];
					que.offer(new Point(nr, nc, cur.cnt + 1));
					v[nr][nc] = true;
					return cur.cnt + 1;
				}

				if (!v[nr][nc] && !map[nr][nc].equals("X")) {
					que.offer(new Point(nr, nc, cur.cnt + 1));
					v[nr][nc] = true;
				}
			}
		}
		return -1;

	}

	private boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
