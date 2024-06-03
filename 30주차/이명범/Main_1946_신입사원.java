import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Score implements Comparable<Score> {
        // 서류 점수
        int ds;
        // 면접 점수
        int is;

        public Score(int ds, int is) {
            this.ds = ds;
            this.is = is;
        }

        @Override
        public int compareTo(Score o) {
            return ds - o.ds;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Score> scores = new PriorityQueue<>();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int ds = Integer.parseInt(st.nextToken());
                int is = Integer.parseInt(st.nextToken());
                scores.offer(new Score(ds, is));
            }
            // 입력 끝

            int min = Integer.MAX_VALUE;
            int count = 0;

            while (!scores.isEmpty()) {
                Score cur = scores.poll();

                if (min > cur.is) {
                    min = cur.is;
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}