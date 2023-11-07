import java.util.*;

class Solution {
    static HashMap<String, Integer> wa;
    static HashMap<String, Integer> cur;
    static int total;
    static int cnt;

    public int solution(String[] want, int[] number, String[] discount) {
        total = 0;
        for (int i = 0; i < number.length; i++) {
            total += number[i];
        }
        wa = new HashMap<>();
        cur = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wa.put(want[i], number[i]);
        }

        int r = -1;
        cnt = 0;
        for (int l = 0; l < discount.length; l++) {
            while (r < discount.length - 1 && r - l + 1 < total) {
                r++;
                cur.put(discount[r], cur.getOrDefault(discount[r], 0) + 1);
            }

            if (check()) {
                cnt++;
            }

            cur.put(discount[l], cur.getOrDefault(discount[l], 0) - 1);
        }

        return cnt;
    }

    public static boolean check() {
        for (String key : wa.keySet()) {
            if (wa.get(key) != cur.getOrDefault(key, 0)) {
                return false;
            }
        }

        return true;
    }
}