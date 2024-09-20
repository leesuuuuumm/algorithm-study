import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 맥주마시면서걸어가기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());  // 편의점 수
            int[][] locations = new int[n + 2][2];  // 집, 편의점, 페스티벌 좌표

            // 상근이의 집 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            locations[0][0] = Integer.parseInt(st.nextToken());
            locations[0][1] = Integer.parseInt(st.nextToken());

            // 편의점 좌표
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                locations[i][0] = Integer.parseInt(st.nextToken());
                locations[i][1] = Integer.parseInt(st.nextToken());
            }

            // 페스티벌 좌표
            st = new StringTokenizer(br.readLine());
            locations[n + 1][0] = Integer.parseInt(st.nextToken());
            locations[n + 1][1] = Integer.parseInt(st.nextToken());

            // BFS를 통해 페스티벌까지 갈 수 있는지 확인
            if (canReachFestival(locations, n)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    public static boolean canReachFestival(int[][] locations, int n) {
        // BFS를 위한 큐와 방문 여부 배열
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 2];  // 집, 편의점, 페스티벌 총 n + 2개 장소

        // 시작 위치(상근이 집)를 큐에 추가
        queue.offer(new int[]{locations[0][0], locations[0][1]});
        visited[0] = true;

        while(!queue.isEmpty()){
            int[] cur= queue.poll();
            if(distance(cur[0],cur[1],locations[n+1][0],locations[n+1][1])<=1000){
                return true;
            }
            for(int i=1;i<=n;i++){
                if(!visited[i]&&distance(cur[0],cur[1],locations[i][0],locations[i][1])<=1000){
                    queue.offer(new int[]{locations[i][0],locations[i][1]});
                    visited[i]=true;
                }
            }
        }

        // BFS가 끝났는데 페스티벌에 도달하지 못한 경우
        return false;
    }

    // 두 좌표 사이의 맨해튼 거리 계산
    public static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
