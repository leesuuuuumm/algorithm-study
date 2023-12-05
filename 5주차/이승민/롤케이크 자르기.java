import java.util.*;

class Solution {
    /*
    범위가 너무 크다...완전탐색으로 풀기엔 매번 종류를 파악하는게 O(n^2)
    */
    public int solution(int[] topping) {
        HashMap<Integer, Integer> old = new HashMap<>();
        HashMap<Integer, Integer> young = new HashMap<>();

        for (int t : topping) {
            old.put(t, old.getOrDefault(t, 0) + 1);
        }
        int ans = 0;
        for (int t : topping) {
            young.put(t, young.getOrDefault(t, 0) + 1);
            if (old.get(t) - 1 == 0) {
                old.remove(t);
            } else {
                old.put(t, old.get(t) - 1);
            }
            if (old.size() == young.size()) {
                ans++;
            }
        }

        return ans;
    }
}