package boj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main_14002_가장긴증가하는부분수열4 {

	static int N;
	static int[] nums;
	static int[] dp;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N];
		dp = new int[N];
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}
		Arrays.fill(parents, -1);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					parents[i] = j;
				}
			}
		}
		int index = 0;
		for (int i = 1; i < N; i++) {
			if (dp[index] >= dp[i])
				continue;
			index = i;
		}
		Deque<Integer> deque = new ArrayDeque<>();
		while (index != -1) {
			deque.add(nums[index]);
			index = parents[index];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(deque.size()).append("\n");
		while (!deque.isEmpty()) {
			sb.append(deque.pollLast()).append(" ");
		}
		System.out.print(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
