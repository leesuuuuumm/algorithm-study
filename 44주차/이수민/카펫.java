class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown+yellow;
        int temp = 1;
        
        for(int i=2;i<=sum/2;i++){
            int r = 0;
            int c = 0;
            if(sum%i==0){
                if(sum/i>i){
                  r = sum/i;
                  c = i;
                }else{
                  r = i;
                  c = sum/i;
                }
                if(yellow == (r*c) - brown && yellow == (r-2) * (c-2)){
                    answer[0] = r;
                    answer[1] = c;
                }
                 
            }
        }
       
        return answer;
    }
}
