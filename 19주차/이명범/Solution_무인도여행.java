import java.util.*;

class Location {
    int r;
    int c;
    
    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    public List<Integer> solution(String[] maps) {
        Queue<Location> q = new LinkedList<>(); 
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (visit[i][j]) continue;
                if (maps[i].charAt(j) == 'X') continue;
                
                q.offer(new Location(i, j));
                visit[i][j] = true;
                
                int sum = 0;
                
                while (!q.isEmpty()) {
                    Location cur = q.poll();
                    
                    sum += maps[cur.r].charAt(cur.c) - '0';
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = cur.r + dr[d];
                        int nc = cur.c + dc[d];
                        
                        if (nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length()) continue;
                        if (visit[nr][nc]) continue;
                        if (maps[nr].charAt(nc) == 'X') continue;
                        
                        q.offer(new Location(nr, nc));
                        visit[nr][nc] = true;
                    }
                }
                
                answer.add(sum);
            }
        }
        
        Collections.sort(answer);
        
        if (answer.isEmpty()) answer.add(-1);
        
        return answer;
    }
}