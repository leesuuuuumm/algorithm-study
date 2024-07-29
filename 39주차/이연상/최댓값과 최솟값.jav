// [PRG] 최댓값과 최솟값

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] temp = s.split(" ");
        
        ArrayList<Integer> list = new ArrayList<>();
        for(String num : temp) {
            list.add(Integer.parseInt(num));
        }
        Collections.sort(list);
        answer += Integer.toString(list.get(0));
        answer += " ";
        answer += Integer.toString(list.get(list.size() - 1));
        
        return answer;
    }
}