import java.io.*;
import java.util.*;

public class Main {

	static class ElecLine implements Comparable<ElecLine> {
		int L1;
		int L2;

		public ElecLine(int L1, int L2) {
			this.L1 = L1;
			this.L2 = L2;
		}

		public int compareTo(ElecLine o) {
			return Integer.compare(this.L1, o.L1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<ElecLine> pq = new PriorityQueue<>();
		int[] elec = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new ElecLine(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		for (int i = 0; i < N; i++) {
			elec[i] = pq.poll().L2;
		}
		int[] dp = new int[N];

		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (elec[i] > elec[j] && dp[i]<=dp[j]) {
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);	
		}	
		System.out.println(N - max);

	}
}
