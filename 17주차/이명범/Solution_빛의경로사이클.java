import java.util.*;

class Solution {
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};
    boolean[][][] light;
    
    int rows;
    int columns;
    
    String[] grids;
    int distance;
    
    public List<Integer> solution(String[] grid) {
        grids = grid;
        rows = grid.length;
        columns = grid[0].length();
        
        // 동(0), 서(1), 남(2), 북(3)
        light = new boolean[rows][columns][4];
        
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < 4; k++) {
                    distance = 0;
                    
                    int r = i; int c = j; int dir = k;
                    
                    while (!light[r][c][dir]) {
                        light[r][c][dir] = true;
        
                        int nr = r + dr[dir];
                        int nc = c + dc[dir];

                        if (nr < 0) nr = rows - 1;
                        if (nr >= rows) nr = 0;
                        if (nc < 0) nc = columns - 1;
                        if (nc >= columns) nc = 0;

                        // 다음 위치에서 어디로 쏠 것인지?
                        int ndir = calculateNextDirection(nr, nc, dir);

                        distance++;
                        
                        r = nr; c = nc; dir = ndir;
                    }
                    
                    // dfs(i, j, k);
                    
                    if (distance != 0) result.add(distance);
                }
            }
        }
        
        Collections.sort(result);
    
        return result;
    }
    
    private int calculateNextDirection(int nr, int nc, int dir) {
        char next = grids[nr].charAt(nc);
        
        // 동(0), 서(1), 남(2), 북(3)
        if ((next == 'S' && dir == 1) || (next == 'R' && dir == 2) || (next == 'L' && dir == 3)) return 1;
        if ((next == 'S' && dir == 3) || (next == 'R' && dir == 1) || (next == 'L' && dir == 0)) return 3;
        if ((next == 'S' && dir == 0) || (next == 'R' && dir == 3) || (next == 'L' && dir == 2)) return 0;
        if ((next == 'S' && dir == 2) || (next == 'R' && dir == 0) || (next == 'L' && dir == 1)) return 2;
        
        return -1;
    }
}