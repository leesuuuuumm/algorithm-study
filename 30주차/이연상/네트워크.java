// [PRG] 네트워크

import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visit[i])
               continue;
            dfs(computers, i, visit);
            answer += 1;
        }
        return answer;
    }
    
    public void dfs(int[][] computers, int n, boolean[] visit) {
        visit[n] = true;
        for(int i = 0; i < computers[n].length; i++) {
            if(computers[n][i] == 1 && i != n && visit[i] == false)
                dfs(computers, i, visit);
        }
    }
}