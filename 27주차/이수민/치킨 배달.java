import java.io.*;
import java.util.*;

public class Main {

	static class Chicken {
		int r;
		int c;

		public Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int N, M; 
	static int min;

	static Chicken[] ck;
	static ArrayList<Chicken> crry;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		crry = new ArrayList<>();
		min = Integer.MAX_VALUE;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					crry.add(new Chicken(r, c));
				}
			}
		}

		ck = new Chicken[M];

		nCr(0, 0);
		System.out.println(min);

	}

	static int m;
	static int[][] house;

	private static void nCr(int cnt, int start) {
		if (cnt == M) {
			int sum = getDistance();
			min = Math.min(sum, min);
			return;
		}

		for (int i = start; i < crry.size(); i++) {
			ck[cnt] = crry.get(i);
			nCr(cnt + 1, i + 1);
		}
	}

	private static int getDistance() {
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				m = Integer.MAX_VALUE;

				if (map[r][c] == 1) {
					for (int i = 0; i < M; i++) {
						int dist = Math.abs(ck[i].r - r) + Math.abs(ck[i].c - c);
						m = Math.min(dist, m);
					}
					sum += m;
				}

			}
		}
		return sum;
	}

}
