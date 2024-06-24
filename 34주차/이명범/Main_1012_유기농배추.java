import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 가로 길이
            M = Integer.parseInt(st.nextToken());
            // 세로 길이
            N = Integer.parseInt(st.nextToken());
            // 배추 개수
            int K = Integer.parseInt(st.nextToken());

            // 맵
            int[][] map = new int[N][M];

            // List
            List<Location> list = new ArrayList<>();

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = j + 1;

                list.add(new Location(y, x));
            }
            // 입력 끝

            bfs(map, list);

            // 중복 제거를 위한 set 컬렉션 사용
            Set<Integer> set = new HashSet<>();

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    set.add(map[j][k]);
                }
            }

            // 0은 빼준다.
            if(set.contains(0)) set.remove(0);
            System.out.println(set.size());
        }
    }

    private static void bfs(int[][] map, List<Location> list) {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        for (Location l : list) {
            // 이미 방문한 위치면 continue
            if (visit[l.r][l.c]) continue;

            queue.offer(l);
            visit[l.r][l.c] = true;

            while (!queue.isEmpty()) {
                Location cur = queue.poll();

                int[] dr = {-1, 1, 0, 0};
                int[] dc = {0, 0, -1, 1};

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    // 다음 위치가 배열의 범위를 벗어나면 continue
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    // 다음 위치가 이미 방문한 위치라면 continue
                    if (visit[nr][nc]) continue;

                    // 다음 위치가 0이면 continue
                    if (map[nr][nc] == 0) continue;

                    // 다 해당되지 않으면 다음 위치의 값을 현재 위치의 값으로 바꿔주고 큐에 넣고 방문체크한다.
                    map[nr][nc] = map[cur.r][cur.c];

                    queue.offer(new Location(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }
    }
}