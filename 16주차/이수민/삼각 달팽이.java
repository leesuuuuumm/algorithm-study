class Solution {
    public int[] solution(int N) {
        int[][] map = new int[N][N];
        
        int num = 0;
        int d  = 0;
        int cr=-1;
        int cc= 0;
        int[] dr = {1,0,-1};
        int[] dc = {0,1,-1};
        for(int i=N;i>=1;i--){
            for(int j=0;j<i;j++){
                cr += dr[d%3];
                cc += dc[d%3];
                
                map[cr][cc] = ++num;
                
            }
            d++;
        }
        
        int[] answer = new int[num];
        int i = 0;
        for(int r=0;r<N;r++){
            for(int c=0;c<N;c++){
                if(map[r][c] >0){
                    answer[i++] = map[r][c];
                }
            }
        }
        return answer;
    }
}
