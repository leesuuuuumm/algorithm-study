// [PRG] 프렌즈 4블록

import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
       
        char[][] bd = new char[m][n];
        for(int i = 0; i < m; i++) {
            char[] temp = board[i].toCharArray();
            for(int j = 0; j < n; j++) {
                bd[i][j] = temp[j];
            }
        }
        
        boolean boom = true;
        while(boom) {
            boom = false;
            boolean[][] visit = new boolean[m][n];
            
            for(int i = 0; i < m; i++) {
                if(i == m - 1) continue;
                
                for(int j = 0; j < n; j++) {
                    if(j == n - 1) continue;
                    if(bd[i][j] == 'x') continue;
                
                    if(!(bd[i + 1][j] == bd[i][j])) continue;
                    if(!(bd[i][j + 1] == bd[i][j])) continue;
                    if(!(bd[i + 1][j + 1] == bd[i][j])) continue;
                    
                    boom = true;
                    visit[i][j] = true;
                    visit[i + 1][j] = true;
                    visit[i][j + 1] = true;
                    visit[i + 1][j + 1] = true;
                }
            }
            
            if(boom) {
                for(int i = 0; i < m; i++) {
                    for(int j = 0; j < n; j++) {
                        if(visit[i][j]) {
                            bd[i][j] = 'x';
                            answer += 1;
                        }
                    }
                }
            }

            for(int j = 0; j < n; j++) {
                for(int i = m - 2; i >= 0; i--) {
                    for(int k = i; k < m - 1; k++) {
                        if(bd[k + 1][j] == 'x') {
                            bd[k + 1][j] = bd[k][j];
                            bd[k][j] = 'x';
                        }
                    }
                }
            }
            
        }
        
        return answer;
    }
}