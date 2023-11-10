import java.util.*;

class Solution {
    class Node {
        int idx;
        int pri;

        Node(int idx, int pri) {
            this.idx = idx;
            this.pri = pri;
        }
    }

    public int solution(int[] prior, int location) {
        int[] cur = new int[10];
        for (int i = 0; i < prior.length; i++) {
            cur[prior[i]]++;
        }
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < prior.length; i++) {
            q.offer(new Node(i, prior[i]));
        }

        int ans = 0;
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            boolean execute = true;
            ans++;
            cur[tmp.pri]--;

            for (int i = 1; i < cur.length; i++) {
                if (i > tmp.pri && cur[i] != 0) {
                    q.offer(tmp);
                    execute = false;
                    ans--;
                    cur[tmp.pri]++;
                    break;
                }
            }

            if (execute && tmp.idx == location) {
                break;
            }
        }

        return ans;
    }
}