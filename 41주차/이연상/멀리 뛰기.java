// [PRG] 멀리 뛰기

class Solution {
    public long solution(int n) {
        long answer = 0;
        
        long d[] = new long[n + 1];
        d[0] = 1;
        d[1] = 2;
        for(int i = 2; i < n; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 1234567; 
        }
    
        return d[n - 1];
    }
}