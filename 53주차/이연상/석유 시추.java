// [PRG] 석유 시추

import java.util.*;

class Solution {
    
    static boolean[][] visit;
    static int[][] move = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    static int n, m, clusterId = 2;
    static Map<Integer, Integer> oilSizes = new HashMap<>();
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        visit = new boolean[n][m];
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (land[x][y] == 1 && !visit[x][y]) {
                    int size = bfs(land, x, y, clusterId);
                    oilSizes.put(clusterId, size);
                    clusterId++; 
                }
            }
        }
        
        int maxOil = 0;
        for (int y = 0; y < m; y++) {
            Set<Integer> visitedClusters = new HashSet<>(); 
            int columnOil = 0;
            for (int x = 0; x < n; x++) {
                int id = land[x][y];
                if (id > 1 && !visitedClusters.contains(id)) { 
                    columnOil += oilSizes.get(id);
                    visitedClusters.add(id);
                }
            }
            maxOil = Math.max(maxOil, columnOil); 
        }
        
        return maxOil;
    }
    
    static int bfs(int[][] land, int x, int y, int clusterId) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visit[x][y] = true;
        land[x][y] = clusterId; 
        int size = 1;
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curX = current[0];
            int curY = current[1];
            
            for (int[] d : move) {
                int nx = curX + d[0];
                int ny = curY + d[1];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny] && land[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    land[nx][ny] = clusterId; 
                    q.add(new int[]{nx, ny});
                    size++;
                }
            }
        }
        
        return size;
    }
}