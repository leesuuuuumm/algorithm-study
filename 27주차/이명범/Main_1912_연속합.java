package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합 {
	static int N, result;
	static int[] num;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		num = new int[N];
		result = Integer.MIN_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			result = Math.max(result, num[i]);
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (sum + num[i] < 0) {
				sum = 0;
				continue;
			}
			sum += num[i];
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}
}
