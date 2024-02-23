import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Long> pq = new PriorityQueue<>();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}

		for (int i = 0; i < M; i++) {
			long n1 = pq.poll();
			long n2 = pq.poll();

			long sum = n1 + n2;

			pq.offer(sum);
			pq.offer(sum);
		}

		long ans = 0;
		while (!pq.isEmpty()) {
			ans += pq.poll();
		}
		System.out.println(ans);

	}

}
