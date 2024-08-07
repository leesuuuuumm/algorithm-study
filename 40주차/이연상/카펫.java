// [PRG] 카펫 

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int val = brown + yellow;
        for(int i = 1; i < val; i++) {
            if(val % i == 0) {
                if((val / i) <= i) {
                    if(i *2 + (val / i) * 2 == brown+4) {
                        answer[0] = i;
                        answer[1] = val / i;
                    }  
                }
            }
        }
        
        return answer;
    }
}