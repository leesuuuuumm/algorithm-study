// [PRG] 점프와 순간이동

import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;

        while(n != 1) {
            if(n % 2 != 0) {
                ans += 1;
            }
            n /= 2;
        }

        return ans;
    }
}