import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int s;
        int e;

        public Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Lecture o) {
            return e - o.e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(s, e));
            pq.add(s);
        }

        Collections.sort(lectures);

        int res = 0;
        int count = 0;
        for (Lecture lecture : lectures) {
            while (!pq.isEmpty() && pq.peek() < lecture.e) {
                pq.poll();
                count++;
            }
            res = Math.max(res, count--);
        }
        System.out.println(res);
    }
}