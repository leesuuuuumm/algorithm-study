class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] v = new boolean[computers.length];
        
        for(int i = 0; i < computers.length; i++){
            v[i] = false;
        }
        
        for(int i = 0; i < computers.length; i++){
            if(v[i] == false){
                answer++;
                dfs(i, v, computers);
            }            
        }
        return answer;
    }
    
    private void dfs(int n, boolean[] v, int[][] computers){
        v[n] = true;
        
        for(int i = 0; i < computers.length; i++){
            if(v[i] == false && computers[n][i] == 1){
                dfs(i, v, computers);
            }
        }
    }
}
