package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_직사각형으로나누기 {
	static class Rectangle {
		int sr;
		int sc;
		int er;
		int ec;

		public Rectangle(int sr, int sc, int er, int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
	}
	static int N, M;
	static int[][] nums;
	static int[][] sums;
	static long result = 0;

	public static void main(String[] args) throws Exception {
		input();
		int sr1 = 1;
		int sc1 = 1;
		for (int er1 = sc1; er1 <= N; er1++) {
			for (int ec1 = sc1; ec1 <= M; ec1++) {
				Rectangle r1 = new Rectangle(sr1, sc1, er1, ec1);
				if (er1 == N) {
					for (int i = sr1; i < N; i++) {
						Rectangle r2 = new Rectangle(sr1, ec1 + 1, i, M);
						Rectangle r3 = new Rectangle(i + 1, ec1 + 1, N, M);
						proc(r1, r2, r3);
					}
					for (int i = ec1 + 1; i < M; i++) {
						Rectangle r2 = new Rectangle(sr1, ec1 + 1, N, i);
						Rectangle r3 = new Rectangle(sr1, i + 1, N, M);
						proc(r1, r2, r3);
					}
				} else if (ec1 == M) {
					for (int i = er1 + 1; i < N; i++) {
						Rectangle r2 = new Rectangle(er1 + 1, sc1, i, M);
						Rectangle r3 = new Rectangle(i + 1, sc1, N, M);
						proc(r1, r2, r3);
					}
					for (int i = sc1; i < M; i++) {
						Rectangle r2 = new Rectangle(er1 + 1, sc1, N, i);
						Rectangle r3 = new Rectangle(er1 + 1, i + 1, N, M);
						proc(r1, r2, r3);
					}
				} else if (er1 < N && ec1 < M) {
					Rectangle r2 = new Rectangle(sr1, ec1 + 1, er1, M);
					Rectangle r3 = new Rectangle(er1 + 1, sc1, N, M);
					proc(r1, r2, r3);

					r2 = new Rectangle(er1 + 1, sc1, N, ec1);
					r3 = new Rectangle(sr1, ec1 + 1, N, M);
					proc(r1, r2, r3);
				}
			}
		}
		System.out.println(result);
	}

	private static void proc(Rectangle r1, Rectangle r2, Rectangle r3) {
		long total = 1;
		total *= calculate(r1);
		total *= calculate(r2);
		total *= calculate(r3);
		result = Math.max(result, total);
	}

	private static int calculate(Rectangle r) {
		return sums[r.er][r.ec] - sums[r.sr - 1][r.ec] - sums[r.er][r.sc - 1] + sums[r.sr - 1][r.sc - 1];
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N + 1][M + 1];
		sums = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			char[] charArray = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				nums[i][j] = charArray[j - 1] - '0';
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + nums[i][j];
			}
		}
	}
}
