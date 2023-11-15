import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        String ss = s.replace("{", "").replace("}", "");
        StringTokenizer st = new StringTokenizer(ss, ", ");


        while (st.hasMoreTokens()) {
            int tmp = Integer.parseInt(st.nextToken());
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue, Collections.reverseOrder()));

        int[] ans = new int[entryList.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : entryList) {
            ans[i++] = e.getKey();
        }

        return ans;
    }
}