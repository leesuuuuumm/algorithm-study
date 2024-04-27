package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2313_보석구매하기 {

	static int N, L;
	static int[] jewelries;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		long result = 0;
		int[][] pair = new int[N][2];
		for (int line = 0; line < N; line++) {
			L = Integer.parseInt(br.readLine());
			jewelries = new int[L];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < L; i++) {
				jewelries[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, jewelries[i]);
			}
			int p = 0;
			for (int i = 0; i < L; i++) {
				p = Math.max(0, p) + jewelries[i];
				max = Math.max(max, p);
			}
			p = 0;
			int si = 0, ei = 0, c = Integer.MAX_VALUE, l = 0;
			for (int i = 0; i < L; i++) {
				if (p <= 0) {
					l = i;
				}
				if ((p = Math.max(0, p) + jewelries[i]) == max && c > i - l) {
					si = l;
					ei = i;
					c = i - l;
				}
			}
			result += max;
			pair[line][0] = si + 1;
			pair[line][1] = ei + 1;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		for (int i = 0; i < N; i++) {
			sb.append(pair[i][0]).append(" ").append(pair[i][1]).append("\n");
		}
		System.out.print(sb);
	}
}
