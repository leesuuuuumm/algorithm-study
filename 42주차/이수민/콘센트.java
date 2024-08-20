import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = N - 1; i >= 0; i--) {
			if (pq.isEmpty() || pq.size() < M) {
				pq.offer(arr[i]);
			} else {
				pq.offer(pq.poll() + arr[i]);
			}

		}

		while (pq.size() > 1) {
			pq.poll();
		}
		System.out.println(pq.poll());

	}
}
