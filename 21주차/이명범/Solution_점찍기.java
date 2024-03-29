class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (long i = 0; i <= d; i += k) {
            long v = (long) d * d - i * i;
            answer += Math.floor(Math.sqrt(v) / k) + 1; 
        }
        return answer;
    }
}