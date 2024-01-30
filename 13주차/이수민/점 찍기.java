import java.util.*;
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        double dd = (double) d*d;
        for(int r=0;r<=d;r+=k){
            int y = (int) Math.sqrt(dd - Math.pow(r,2));
            answer+=((y/k)+1);
        }
        return answer;
    }
}
