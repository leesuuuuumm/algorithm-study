
import java.io.*;
import java.util.*;

public class 쉼터 {

    static int n, k;
    static long res;
    static Queue<int[]> que = new LinkedList<>();
    static HashSet<Integer> visited = new HashSet<>();
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            que.offer(new int[] {num, 0}); // 0 : 샘터의 위치, 0 : 샘터와 떨어진 거리
            visited.add(num);
        }


        bfs();
        System.out.println(res);

    }

    static void bfs() {
        int score = 1; // 불행도

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int idx = cur[0];
            int dist = cur[1];
            // 왼쪽 체크(좌, 우 순서 상관 x)
            int nx = idx - 1;
            if (!visited.contains(nx)) {
                k--;
                que.offer(new int[] {nx, dist + 1});
                res += dist + 1; // 이전 거리 + 1
                visited.add(nx); // 방문 처리
            }
            if (k == 0) { // k == 0이라면, 집을 다 지었다는 것이니 return;
                return;
            }
            nx = idx + 1;
            if (!visited.contains(nx)) {
                que.offer(new int[] {nx, dist + 1});
                k--;
                res += dist + 1;
                visited.add(nx); // 방문 처리
            }

            if (k == 0) {
                return;
            }

        }

    }

}