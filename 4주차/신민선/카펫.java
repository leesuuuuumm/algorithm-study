class Solution {
    public int[] solution(int brown, int yellow) {
        int sum=brown+yellow;
        for(int i=1;i<=Math.sqrt(yellow);i++){
            if(yellow%i!=0)
                continue;
            int j=yellow/i;
            if((i+2)*(j+2)==sum){
                int[] answer = {j+2,i+2};
                return answer;
            }
        }
        int[] answer = {};
        return answer;
    }
}
