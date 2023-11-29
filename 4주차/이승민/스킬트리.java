import java.util.*;

class Solution {
    public int solution(String skill, String[] trees) {
        int ans = 0; // 정답
        for (String t : trees) {
            // trees[i].charAt(j)가 skill에 포함된 글자인 경우 sb에 추가
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < t.length(); i++) {
                if (skill.contains(t.charAt(i) + "")) {
                    sb.append(t.charAt(i));
                }
            }
            // sb.length == 0이면 ans++, 아니면 skill과 대조
            if (sb.length() == 0) {
                ans++;
                continue;
            }
            for (int i = skill.length(); i > 0; i--) {
                String tmp = skill.substring(0, i);
                if (tmp.equals(sb.toString())) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }
}