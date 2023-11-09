import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int val;
        int cnt;

        Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return n.cnt - this.cnt;
        }
    }

    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        List<Node> list = new ArrayList<>();
        for (Integer c : map.keySet()) {
            list.add(new Node(c, map.get(c)));
        }
        Collections.sort(list);

        int amount = k;
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            amount -= list.get(i).cnt;
            ans++;
            if (amount <= 0) {
                return ans;
            }
        }

        return ans;
    }
}