import java.util.*;

class Solution {
    class Node {
        int start, end, diff;

        Node(int start, int end, int diff) {
            this.start = start;
            this.end = end;
            this.diff = diff;
        }
    }

    public int[] solution(int[] seq, int k) {
        List<Node> list = new ArrayList<>();

        int r = -1;
        int sum = 0;
        for (int l = 0; l < seq.length; l++) {
            while (r < seq.length - 1 && sum < k) {
                r++;
                sum += seq[r];
            }

            if (sum == k) {
                list.add(new Node(l, r, r - l + 1));
            }

            sum -= seq[l];
        }

        Collections.sort(list, (n1, n2) -> {
            if (n1.diff == n2.diff) {
                return n1.start - n2.start;
            }
            return n1.diff - n2.diff;
        });

        return new int[]{list.get(0).start, list.get(0).end};
    }
}