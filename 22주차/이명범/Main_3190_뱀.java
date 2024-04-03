import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Snake {
        int r = 1;
        int c = 1;
        Queue<Location> q = new ArrayDeque<>();
        Set<Location> body = new HashSet<>();
        int dir = 0;

        public Snake() {
            q.add(new Location(1, 1));
            body.add(new Location(1, 1));
        }

        public boolean process() {
            if (!move()) return false;
            if (!hasApple()) remove();
            else apple.remove(new Location(r, c));
            return true;
        }

        public void changeDir(String mode) {
            if (mode.equals("D")) {
                dir = (dir + 1) % 4;
            } else {
                dir = (dir + 3) % 4;
            }
        }

        private boolean move() {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 1 || nr > N || nc < 1 || nc > N) return false;
            if (body.contains(new Location(nr, nc))) return false;
            r = nr;
            c = nc;
            q.add(new Location(r, c));
            body.add(new Location(r, c));
            return true;
        }

        private boolean hasApple() {
            return apple.contains(new Location(r, c));
        }

        private void remove() {
            Location tail = q.poll();
            body.remove(tail);
        }
    }

    static int[] dr = new int[]{0, 1, 0, -1};
    static int[] dc = new int[]{1, 0, -1, 0};

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return r == location.r && c == location.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    static int N, K, L;
    static Set<Location> apple = new HashSet<>();
    static Map<Integer, String> dirInfo = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apple.add(new Location(r, c));
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            dirInfo.put(time, dir);
        }
        int time = 0;
        Snake snake = new Snake();
        while (true) {
            time++;
            if (!snake.process()){
                System.out.println(time);
                break;
            }
            if (dirInfo.containsKey(time)) snake.changeDir(dirInfo.get(time));
        }
    }
}