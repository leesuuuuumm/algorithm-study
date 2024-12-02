import java.util.ArrayList;
import java.util.List;

class Solution {
    static boolean[][][] visited;
    static int R, C;

    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        R = grid.length;
        C = grid[0].length();
        visited = new boolean[R][col][4];

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                for(int d=0;d<4;d++) {
                    if(!visited[i][j][d]) {
                        answer.add(light(grid, i, j, d));
                    }
                }
            }
        }

        return answer.stream().sorted().mapToInt(i->i).toArray();
    }
  
    static int[] dr = {-1, 0, 1, 0}; 
    static int[] dc = {0, -1, 0, 1};

    public int light(String[] grid, int i, int j, int d) {
        int count = 0;

        while(!visited[i][j][d]) {
            count++;
            visited[i][j][d] = true;

            if(grid[i].charAt(j) == 'L') {
                d = (d+3) % 4; 
            }
            if(grid[i].charAt(j) == 'R') {
                d = (d+1) % 4; 
            }
            
            i = (i+dr[d]+R) % R;
            j = (j+dc[d]+C) % C;
        }
        return count;
    }
}
