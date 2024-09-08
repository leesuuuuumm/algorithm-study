import java.util.*;
class Solution {
    public int solution(int n) {
       int cnt = Integer.bitCount(n); 
        int answer =0;
        int k = n;
       while(true){
            if(Integer.bitCount(++k)==cnt) 
                break;
        }
                return k;
    }
}
