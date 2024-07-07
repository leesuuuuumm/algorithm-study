class Solution {
    int[] map;
    int ans;
    public int solution(int n) {
        ans = 0;
        map = new int[n];
        
        dfs(0, n);
        
        return ans;
    }
    private void dfs(int depth, int n){
        if(n == depth){
           ans++;
            return;
        }
        
        for(int i=0;i<map.length;i++){
            map[depth] = i;
            if(check(depth)) dfs(depth+1,n);
        }
    }
    private boolean check(int depth){
        for(int i=0;i<depth;i++){
            if(map[depth] == map[i] || Math.abs(depth-i) == Math.abs(map[depth] -map[i])) return false;
        }
        return true;
    }
}
