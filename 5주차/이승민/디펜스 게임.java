import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 무적권을 통해 막은 적의 숫자를 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        // enemy의 남은 부분을 순회하면서 pq보다 큰 경우 pq를 교체한다.
        for (int i = 0; i < enemy.length; i++) {
            if (k > 0) {
                k--;
                pq.offer(enemy[i]);
            } else {
                int num = enemy[i];
                // 무적권을 다 쓴 경우
                if (pq.peek() < enemy[i]) {
                    num = pq.poll();
                    pq.offer(enemy[i]);
                }
                if (n < num) {
                    break;
                } else {
                    n -= num;
                }
            }
            ans++;
        }

        return ans;
    }
}