// [PRG] 모음사전

import java.util.*;

class Solution {
    static String arr[] = {"A", "E", "I", "O", "U"};
    static int cnt = 0;
    static int ans = 0;
    
    public int solution(String word) {
        perm(0, "", word);
        return ans;
    }
    
    static void perm(int idx, String s, String word) {
        if(idx == 5)
            return;
        
        for(int i = 0; i < arr.length; i++) {
            String ss = s + arr[i];
            cnt++;
            if(ss.equals(word)) {
                ans = cnt;
                return;
            }
            perm(idx + 1, ss, word);
        }
    }
    
}