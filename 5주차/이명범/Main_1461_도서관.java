package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1461_도서관 {

	static int N, M;
	static int[] books;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		int l = 0;
		int r = N;
		int m = 0;
		for (int i = 0; i <= N; i++) {
			if (books[i] == 0)
				m = i;
		}
		int sum = 0;
		while (l < m) {
			sum += books[l] * -2;
			l += M;
		}
		while (r > m) {
			sum += books[r] * 2;
			r -= M;
		}
		sum -= Math.max(-1 * books[0], books[N]);
		System.out.println(sum);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		books = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		books[N] = 0;
		Arrays.sort(books);
	}
}
