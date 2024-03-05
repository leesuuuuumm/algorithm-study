class Solution {
    int[] memo;
    
    public int solution(int x, int y, int n) {
        int answer = 0;
        memo = new int[y+1];
        
        for(int i = 0; i < y+1; i++) memo[i] = -1;
        memo[x] = 0;
        
        if(x == y) return 0;
        
        for(int i = x; i < y+1; i++){
            if(memo[i] == -1) continue; //만들 수 없는 수
            
            if(i+n <= y){
                if(memo[i+n] == -1){ //비어있는 칸이라면
                    memo[i+n] = memo[i] + 1;
                }
                else { //기존의 저장된 횟수와 비교
                    memo[i+n] = Math.min(memo[i]+1, memo[i+n]);
                }
            }
            if(i*2 <= y){
                if(memo[i*2] == -1){ //비어있는 칸이라면
                    memo[i*2] = memo[i] + 1;
                }
                else { //기존의 저장된 횟수와 비교
                    memo[i*2] = Math.min(memo[i]+1, memo[i*2]);
                }
            }
            if(i*3 <= y){
                if(memo[i*3] == -1){ //비어있는 칸이라면
                    memo[i*3] = memo[i] + 1;
                }
                else { //기존의 저장된 횟수와 비교
                    memo[i*3] = Math.min(memo[i]+1, memo[i*3]);
                }
            }
        }
        
        answer = memo[y];
        
        return answer;
    }
}
