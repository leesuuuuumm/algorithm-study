import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        int[] answer = new int[4];

        for (int[] edge : edges) { // (1)
            map1.put(edge[0], map1.getOrDefault(edge[0], 0) + 1);
            map2.put(edge[1], map2.getOrDefault(edge[1], 0) + 1);
        }

        for (int node : map1.keySet()) {
            if (map1.get(node) > 1) { // (2)
                if (!map2.containsKey(node)) {
                    answer[0] = node;
                } else {
                    answer[3] += 1;
                }
            }
        }

        for (int node : map2.keySet()) {
            if (!map1.containsKey(node)) { // (3)
                answer[2] += 1;
            }
        }
        answer[1] = map1.get(answer[0]) - answer[2] - answer[3]; // (4)
        return answer;
    }
}
