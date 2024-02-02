package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16498_작은벌점 {

	static int A, B, C;
	static int[] ac, bc, cc1, cc2;

	public static void main(String[] args) throws Exception {
		input();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < B; j++) {
				int c1 = bs1(i, j);
				int c2 = bs2(i, j);
				min = Math.min(min, Math.min(calculate(ac[i], bc[j], cc1[c1]), calculate(ac[i], bc[j], -cc2[c2])));
			}
		}
		System.out.println(min);
	}

	private static int calculate(int a, int b, int c) {
		return Math.abs(Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c)));
	}

	private static int bs1(int a, int b) {
		int l = 0;
		int r = C - 1;

		int min = Math.min(ac[a], bc[b]);

		while (l < r) {
			int m = (l + r) / 2;

			if (cc1[m] < min)
				l = m + 1;
			else
				r = m;
		}
		return r;
	}

	private static int bs2(int a, int b) {
		int l = 0;
		int r = C - 1;

		int max = Math.max(ac[a], bc[b]);

		while (l < r) {
			int m = (l + r) / 2;

			if (-cc2[m] > max)
				l = m + 1;
			else
				r = m;
		}
		return r;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ac = new int[A];
		bc = new int[B];
		cc1 = new int[C];
		cc2 = new int[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			ac[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			bc[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			int input = Integer.parseInt(st.nextToken());
			cc1[i] = input;
			cc2[i] = -input;
		}
		Arrays.sort(cc1);
		Arrays.sort(cc2);
	}
}
