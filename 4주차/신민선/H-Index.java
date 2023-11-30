import java.util.*;
class Solution {
    public int solution(int[] c) {
        Arrays.sort(c);
        for(int h=c.length;h>0;h--){
            if(c[c.length-h] >= h)
                return h;
        }
        return 0;
    }
}
