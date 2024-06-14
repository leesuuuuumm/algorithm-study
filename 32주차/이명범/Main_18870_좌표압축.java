package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_18870_좌표압축 {
	static int N;
	static int[] arr, sortedArr;
	static Set<Integer> set;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		sortedArr = new int[set.size()];
		int idx = 0;
		for (Integer i : set) {
			sortedArr[idx++] = i;
		}
		Arrays.sort(sortedArr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(binarySearch(arr[i])).append(" ");
		}
		System.out.println(sb);
	}

	private static int binarySearch(int target) {
		int l = 0;
		int r = set.size() - 1;

		while (l <= r) {
			int m = (l + r) / 2;

			if (sortedArr[m] == target) {
				return m;
			} else if (sortedArr[m] > target) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return -1;
	}
}
