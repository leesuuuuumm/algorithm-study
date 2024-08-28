import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			if (pq.size() < arr[i][0]) {
				pq.offer(arr[i][1]);
			} else if (pq.size() >= arr[i][0]) {
				if (pq.peek() < arr[i][1]) {
					pq.poll();
					pq.offer(arr[i][1]);
				}
			}
		}

		while (!pq.isEmpty()) {
			ans += pq.poll();
		}
		System.out.println(ans);
	}
}
