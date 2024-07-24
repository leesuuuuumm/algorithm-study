class Solution
{
    public int solution(int [][]board)
    {
        int[][] map = new int[board.length+1][board[0].length+1];
        for(int i=0; i<board.length; i++)
            for(int j=0; j<board[i].length; j++)
                map[i+1][j+1] = board[i][j];
        
        int max = 0;
        
        for(int i=1; i<map.length; i++){
            for(int j=1; j<map[i].length; j++){
                if(map[i][j] == 1){
                    int l = map[i-1][j];    
                    int u = map[i][j-1];     
                    int lu = map[i-1][j-1];
                    int min = Math.min(l, Math.min(u, lu)); 
                    map[i][j] = min+1;
                    max = Math.max(max, min+1);
                }
            }
        }
        return  max * max;
    }
}
