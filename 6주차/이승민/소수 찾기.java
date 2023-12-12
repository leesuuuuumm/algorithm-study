import java.util.*;

class Solution {
    static boolean[] vis;
    static char[] nums;
    static HashSet<Integer> set;

    public int solution(String numbers) {
        vis = new boolean[7];
        nums = numbers.toCharArray();
        set = new HashSet<>();

        choose("", 0);

        return set.size();
    }

    public void choose(String str, int curNum) {
        if (!str.equals("")) {
            int thisNum = Integer.parseInt(str);
            if (isPrime(thisNum)) {
                set.add(thisNum);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                choose(str + nums[i], curNum + 1);
                vis[i] = false;
            }
        }
    }

    public boolean isPrime(int num) {
        if (num < 2)
            return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}