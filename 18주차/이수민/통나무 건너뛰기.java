import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

			for (int i = 0; i < N; i++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}

			Deque<Integer> dq = new ArrayDeque<>();

			dq.offer(pq.poll());

			int i = 0;
			while (!pq.isEmpty()) {
				if (i % 2 == 0) {
					dq.offerFirst(pq.poll());
				} else {
					dq.offerLast(pq.poll());
				}
				i++;

			}

			int ans = -1;
			while (dq.size() > 1) {
				ans = Math.max(ans, Math.abs(dq.poll() - dq.peek()));
			}
			System.out.println(ans);
		}

	}

}
