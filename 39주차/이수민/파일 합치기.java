import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();

			for (int i = 0; i < K; i++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}
			long ans = 0;

			while (pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();

				ans += (a + b);

				pq.offer(a + b);
			}

			sb.append(ans).append("\n");

		}
		System.out.println(sb);
	}

}
