package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230_수고르기 {

	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int value = nums[binarySearch(i, N - 1)] - nums[i];

			if (value < M)
				continue;

			result = Math.min(result, value);
		}
		System.out.println(result);
	}

	private static int binarySearch(int l, int r) {
		int idx = l;
		while (l < r) {
			int m = (l + r) / 2;

			if (nums[m] - nums[idx] < M)
				l = m + 1;
			else
				r = m;
		}
		return r;
	}
}
