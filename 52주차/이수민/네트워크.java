class Solution {
    public int solution(int n, int[][] map) {
        int answer = 0;
        boolean[] v = new boolean[n];
        for(int i=0;i<n;i++){
            if(!v[i]){
                dfs(i,v,n,map);
                answer++;
            }
        }
        return answer;
    }
    private void dfs(int r, boolean[] v, int n, int[][] map){
        v[r] = true;
        for(int c=0;c<n;c++){
            if(map[r][c] == 1 && !v[c]){
                dfs(c,v,n,map);
            }
        }
    }
}
