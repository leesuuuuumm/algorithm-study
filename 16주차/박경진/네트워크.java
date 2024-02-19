class Solution {
    int cnt;
    boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        cnt = 0;
        visit = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                dfs(i, n, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    void dfs(int i, int n, int[][] com){
        visit[i] = true;
        
        for(int j = 0; j < n; j++){
            if(com[i][j] == 1 && !visit[j] && i != j){
                dfs(j, n, com);
            }
        }
    }
}
