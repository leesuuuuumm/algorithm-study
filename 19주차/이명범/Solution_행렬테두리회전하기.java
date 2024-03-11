import java.util.*;
import java.lang.*;

class Solution {
    static int[][] arr;
    
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        arr = new int[rows + 1][columns + 1];
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                arr[i][j] = (i - 1) * columns + j;
            }
        }
        
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            
            answer[i] = rotate(x1, y1, x2, y2);
        }
        
        return answer;
    }
    
    private static int rotate(int x1, int y1, int x2, int y2) {
        int dir = 0;
        int min = Integer.MAX_VALUE;
        
        int cx = x1;
        int cy = y1;
        
        int temp = arr[x1][y1];
        
        while (dir < 4) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            
            if (nx < x1 || nx > x2 || ny < y1 || ny > y2) {
                dir++;
                continue;
            }
            
            min = Math.min(min, arr[cx][cy]);
            
            arr[cx][cy] = arr[nx][ny];
            cx = nx; 
            cy = ny;
        }
        
        arr[x1][y1 + 1] = temp;
        
        System.out.println("zz");
        
        return min;
    }
}