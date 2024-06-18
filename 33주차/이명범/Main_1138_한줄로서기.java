package boj;

import java.util.Arrays;

public class Main_1138_한줄로서기 {

	static int N;
	static int[] nums, result;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N];
		result = new int[N];
		Arrays.fill(result, Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (result[j] != Integer.MAX_VALUE)
					continue;
				if (nums[i] == count) {
					result[j] = i + 1;
					break;
				}
				if (result[j] == Integer.MAX_VALUE)
					count++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(result[i]).append(" ");
		}
		sb.setLength(sb.length() - 1);
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
