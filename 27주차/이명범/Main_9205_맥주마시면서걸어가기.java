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

    static int n;
    static Location[] locs;
    // key : 현재 인덱스, value: 갈 수 있는 인덱스들
    static Map<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            locs = new Location[n + 2];
            map = new HashMap<>();

            for (int j = 0; j < n + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                locs[j] = new Location(r, c);
            }

            for (int j = 0; j < n + 2; j++) {
                for (int k = 0; k < n + 2; k++) {
                    if (j != k && getDistance(j, k) <= 1000) {
                        map.computeIfAbsent(j, kk ->new ArrayList<>());
                        map.get(j).add(k);
                    }
                }
            }

            sb.append(bfs() ? "happy" : "sad").append("\n");
        }
        System.out.print(sb);
    }

    private static int getDistance(int cur, int next) {
        return Math.abs(locs[cur].r - locs[next].r) + Math.abs(locs[cur].c - locs[next].c);
    }

    private static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n + 2];

        queue.offer(0);
        visit[0] = true;

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            if (cur == n + 1) return true;

            List<Integer> nextIndexes = map.get(cur);
            
            if (nextIndexes == null) return false;

            for (Integer index : nextIndexes) {
                if (visit[index]) continue;

                queue.offer(index);
                visit[index] = true;
            }
        }
        return false;
    }
}