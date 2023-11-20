import java.util.*;

class Solution {
    char[] chs = {'A', 'E', 'I', 'O', 'U'};
    List<String> list = new ArrayList<>();

    public int solution(String word) {
        dfs(word, "");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                return i;
            }
        }

        return 0;
    }

    public void dfs(String word, String cur) {
        if (cur.length() > 5) {
            return;
        }

        if (!list.contains(cur)) {
            list.add(cur);
        }

        for (int i = 0; i < chs.length; i++) {
            dfs(word, cur + chs[i]);
        }
    }
}