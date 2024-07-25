class Solution {
    public int[] solution(long begin, long end) {
        int f = (int)begin;
        int l = (int)end;
        int[] answer = new int[l - f + 1];
        
        for (int i = f; i < l + 1; i++) {
            answer[i - f] = 1;
            
            for (int j = 2; j <= Math.floor(Math.sqrt(i)); j++)
                if (i % j == 0 && i / j <= 10000000) {
                    answer[i - f] = i / j;
                    break;
                }
        }
        
        if (f == 1)
            answer[0] = 0;
        
        return answer;
    }
}
