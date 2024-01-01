import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    비용이 계속 누적되므로 매순간 비용이 가장 적은 파일끼리 합친다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> sub = new PriorityQueue<>();
            for (int i = 0; i < k; i++) {
                sub.offer(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while (sub.size() != 1) {
                long sub1 = sub.poll();
                long sub2 = sub.poll();

                ans += sub1 + sub2;
                sub.offer(sub1 + sub2);
            }

            System.out.println(ans);
        }
    }
}
