package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1838_버블정렬 {

	static int N;
	static int[] origin;
	static int[] sorted;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		origin = new int[N];
		sorted = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			origin[i] = num;
			sorted[i] = num;
		}
		Arrays.sort(sorted);

		Map<Integer, Integer> indexOfNum = new HashMap<>();
		for (int i = 0; i < N; i++) {
			indexOfNum.put(sorted[i], i);
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, i - indexOfNum.get(origin[i]));
		}
		System.out.println(max);
	}
}
