package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7453_합이0인네정수 {

	static int N;
	static int[] A, B, C, D;
	static int[] F, S;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}
	private static void solve() {
		F = new int[N * N];
		S = new int[N * N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				F[i * N + j] = A[i] + B[j];
				S[i * N + j] = C[i] + D[j];
			}
		}
		Arrays.sort(F);
		Arrays.sort(S);
		long result = 0;
		for (int fk : F) {
			int l = lb(-fk);
			if (l == -1)
				continue;
			int r = ub(-fk);
			result += r - l;
		}
		System.out.println(result);
	}

	private static int lb(int sk) {
		int l = 0;
		int r = N * N;

		boolean success = false;
		while (l < r) {
			int m = (l + r) / 2;

			if (S[m] >= sk) {
				if (S[m] == sk)
					success = true;

				r = m;
			} else {
				l = m + 1;
			}
		}
		if (!success)
			return -1;

		return r;
	}

	private static int ub(int sk) {
		int l = 0;
		int r = N * N;

		while(l < r) {
			int m = (l + r) / 2;

			if(S[m] <= sk) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}
		return r;
	}

	// private static void solve() {
	// 	Map<Integer, Integer> fdb = new HashMap<>(N * N);
	// 	Map<Integer, Integer> sdb = new HashMap<>(N * N);
	// 	for (int i = 0; i < N; i++) {
	// 		for (int j = 0; j < N; j++) {
	// 			fdb.put(A[i] + B[j], fdb.getOrDefault(A[i] + B[j], 0) + 1);
	// 			sdb.put(C[i] + D[j], sdb.getOrDefault(C[i] + D[j], 0) + 1);
	// 		}
	// 	}
	// 	long result = 0;
	// 	for (Integer fk : fdb.keySet()) {
	// 		if (sdb.containsKey(-fk)) {
	// 			result += (long) fdb.get(fk) * sdb.get(-fk);
	// 		}
	// 	}
	// 	System.out.println(result);
	// }

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
	}
}
