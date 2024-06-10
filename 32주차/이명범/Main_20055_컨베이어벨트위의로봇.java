package boj;

import java.util.LinkedList;
import java.util.List;

public class Main_20055_컨베이어벨트위의로봇 {
    static class Robot {
        int cur;

        public Robot(int cur) {
            this.cur = cur;
        }
    }

    static int N, K;
    static int[] belt;
    static List<Robot> robots = new LinkedList<>();
    static int index = 0;

    public static void main(String[] args) throws Exception {
        N = read();
        K = read();
        belt = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = read();
        }
        int count = 0;
        for (int i = 1; i < 1_000_000_000; i++) {
            // 1.
            index = (index + 2 * N - 1) % (2 * N);
            if (!robots.isEmpty() && robots.get(0).cur == (index + N - 1) % (2 * N)) robots.remove(0);
            // 2.
            for (int j = 0; j < robots.size(); j++) {
                if (j != 0 && robots.get(j - 1).cur == (robots.get(j).cur + 1) % (2 * N)) continue;
                if (belt[(robots.get(j).cur + 1) % (2 * N)] == 0) continue;
                robots.get(j).cur = (robots.get(j).cur + 1) % (2 * N);
                belt[robots.get(j).cur]--;
                if (belt[robots.get(j).cur] == 0) count++;
            }
            if (!robots.isEmpty() && robots.get(0).cur == (index + N - 1) % (2 * N)) robots.remove(0);
            // 3.
            if (belt[index] != 0) {
                robots.add(new Robot(index));
                belt[index]--;
                if (belt[index] == 0) count++;
            }
            // 4.
            if (count >= K) {
                System.out.println(i);
                break;
            }
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
