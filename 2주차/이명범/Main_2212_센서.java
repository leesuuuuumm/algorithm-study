package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2212_센서 {

	static int N, K;
	static int[] sensors;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		int sum = 0;
		for (int i = 0; i < N - 1; i++) {
			int diff = sensors[i + 1] - sensors[i];
			pq.add(diff);
			sum += diff;
		}
		for (int i = 0; i < K - 1; i++) {
			Integer num = pq.poll();
			sum -= num == null ? 0 : num;
		}
		System.out.println(sum);
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		sensors = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensors);
	}
}
