import java.util.*;

class Solution {
    public static int solution(String[][] clothes) {
        Map<String, Integer> map = getClothesPartCountMap(clothes);
        return getAnswer(map) -1;
    }

    private static int getAnswer(Map<String, Integer> map) {
        int answer = 1;
        for (String s : map.keySet()) {
            answer *= (map.get(s)+1);
        }
        return answer;
    }

    private static Map<String, Integer> getClothesPartCountMap(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            if (map.containsKey(clothe[1])) {
                map.put(clothe[1], map.get(clothe[1]) + 1);
                continue;
            }
            map.put(clothe[1], 1);
        }
        return map;
    }
}
