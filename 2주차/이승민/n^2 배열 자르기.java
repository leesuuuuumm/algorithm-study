import java.util.*;

class Solution {
    /*
    n의 범위를 보면 알겠지만, 직접 배열에 값을 대입하고 잘라 붙이는 방법으로는 풀 수 없다.
    인덱스와 각 칸에 들어가는 값을 이용해서 규칙성을 찾아보자.
    */
    public int[] solution(int n, long left, long right) {
        int[] ans = new int[(int) (right - left) + 1];

        for (int i = 0; i < ans.length; i++) {
            long x = ((long) i + left) / (long) n + (long) 1;
            long y = ((long) i + left) % (long) n + (long) 1;
            ans[i] = (int) Math.max(x, y);
        }

        return ans;
    }
}