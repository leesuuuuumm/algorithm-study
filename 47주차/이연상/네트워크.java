// [PRG] 네트워크

class Solution {
    public static boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visit[i])
                continue;
            
            dfs(i, computers);
            answer++;
        }
        
        return answer;
    }
    
    public void dfs(int n, int[][] computers) {
        if(visit[n])
            return;
        
        visit[n] = true;
        for(int i = 0; i < computers[n].length; i++) {
            if(i == n || computers[n][i] == 0)
                continue;
            
            dfs(i, computers);
        }
    }
}