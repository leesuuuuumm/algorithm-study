import java.util.*;

class Solution {   
    static class Location {
        int r;
        int c;
        
        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int N, M;
    static Location s, e;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    char[][] map;
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);
                
                if (map[i][j] == 'R') s = new Location(i, j);
                else if (map[i][j] == 'G') e = new Location(i, j);
            }
        }
        return bfs();
    }
    
    private int bfs() {
        Queue<Location> q = new ArrayDeque();
        boolean[][] visit = new boolean[N][M];
        q.add(s);
        visit[s.r][s.c] = true;
        
        int result = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int t = 0; t < size; t++) {
                Location cur = q.poll();
                
                if (cur.r == e.r && cur.c == e.c) return result;
            
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] == 'D') continue;

                    while (!(nr + dr[i] < 0 || nr + dr[i] >= N || nc + dc[i] < 0 || nc + dc[i] >= M || 
                           map[nr + dr[i]][nc + dc[i]] == 'D'))  {
                        nr += dr[i];
                        nc += dc[i];
                    }

                    if (visit[nr][nc]) continue;

                    q.add(new Location(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            result++;
        }
        return -1;
    }
}