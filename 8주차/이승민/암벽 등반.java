mport java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    /*
    bfs로 풀이

    경우의 수를 줄이기 위해 x 좌표를 기준으로 정렬한 후
    현재 좌표보다 x가 작은 좌표, 큰 좌표들을 차례대로 살펴본다. 조건에 안 맞으면 break로 빠져나와서 경우의 수를 줄인다.
     */
    static int n, t;
    static ArrayList<Coordinate> input; // n개의 좌표를 저장
    static Queue<Integer> q;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        input = new ArrayList<>();
        q = new LinkedList<>();
        dist = new int[n + 1]; // 맨 처음 위치 (0,0)을 포함하기 위해서 n + 1을 크기로
        Arrays.fill(dist, -1); // 방문 x인 경우를 위해서 -1로 초기화

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            input.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        input.add(new Coordinate(0, 0)); // (0,0) 추가 후 x 좌표 기준 오름차순 정렬
        Collections.sort(input, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate c1, Coordinate c2) {
                int tmp = c1.x - c2.x;
                if (tmp == 0)
                    return c1.y - c2.y;
                return tmp;
            }
        });

        q.offer(0);
        dist[0] = 0;

        System.out.println(bfs());
    }

    public static int bfs() {
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (input.get(cur).y == t) {
                return dist[cur];
            }

            for (int i = cur - 1; i > 0; i--) {
                if (Math.abs(input.get(cur).x - input.get(i).x) > 2) {
                    break;
                }

                if (dist[i] == -1 && Math.abs(input.get(cur).y - input.get(i).y) <= 2) {
                    dist[i] = dist[cur] + 1;
                    q.offer(i);
                }
            }

            for (int i = cur + 1; i <= n; i++) {
                if (Math.abs(input.get(cur).x - input.get(i).x) > 2) {
                    break;
                }

                if (dist[i] == -1 && Math.abs(input.get(cur).y - input.get(i).y) <= 2) {
                    dist[i] = dist[cur] + 1;
                    q.offer(i);
                }
            }
        }

        return -1;
    }
}
