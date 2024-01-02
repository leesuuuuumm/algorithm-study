package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2015_수들의합4 {

	static int N, K;
	static int[] sum;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sum = new int[N];
		map = new HashMap<>();
		map.put(0, 1);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sum[i] = i == 0 ? Integer.parseInt(st.nextToken()) : sum[i - 1] + Integer.parseInt(st.nextToken());
		}
		long result = 0;
		for (int i = 0; i < N; i++) {
			result += map.getOrDefault(sum[i] - K, 0);
			map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
		}
		System.out.println(result);
	}
}
