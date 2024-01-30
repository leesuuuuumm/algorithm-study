package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_24525_SKK문자열 {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = str.length();
		char[] input = new char[N + 1];
		int[] sc = new int[N + 1];
		int[] kc = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			input[i] = str.charAt(i - 1);
			sc[i] += sc[i - 1] + (input[i] == 'S' ? 1 : 0);
			kc[i] += kc[i - 1] + (input[i] == 'K' ? 1 : 0);
		}
		if (Math.min(sc[N], kc[N] / 2) < 1) {
			System.out.println(-1);
			return;
		}
		int[] sum = new int[N + 1];
		Arrays.fill(sum, 100_000);
		for (int i = 1; i <= N; i++) {
			if (input[i] == 'S') {
				sum[i] += 2;
			} else if (input[i] == 'K') {
				sum[i] -= 1;
			}
			sum[i] += sum[i - 1] - 100_000;
		}
		int[] index = new int[300_001];
		Arrays.fill(index, -1);
		int max = 0;
		for (int i = 0; i <= N; i++) {
			if (index[sum[i]] == -1) {
				index[sum[i]] = i;
				continue;
			}
			int l = index[sum[i]];
			if (sc[i] - sc[l] == 0)
				continue;
			if (2 * (sc[i] - sc[l]) == kc[i] - kc[l]) {
				max = Math.max(max, i - l);
			}
		}
		System.out.println(max == 0 ? -1 : max);
	}
}
