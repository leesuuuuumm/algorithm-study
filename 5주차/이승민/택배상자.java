import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> sub = new Stack<>();
        int ans = 0; // 정답
        int i = 0;
        int cur = 1;
        while (i < order.length) {
            // order[i] > cur(메인 벨트 앞) -> sub에 넣기
            // order[i] < cur -> sub peek이 같은 한 계속 트럭에 싣기, 아니면 작업 종료
            // order[i] == cur -> 트럭에 싣기
            if (order[i] == cur) {
                ans++;
                cur++;
                i++;
                continue;
            } else if (order[i] > cur) {
                sub.push(cur);
                cur++;
            } else {
                while (!sub.isEmpty() && sub.peek() == order[i]) {
                    sub.pop();
                    ans++;
                    i++;
                }
                // 트럭에 모두 싣거나, cur보다 order[i]가 작으면 종료
                if (i >= order.length || order[i] < cur) {
                    break;
                }
            }
        }
        return ans;
    }
}