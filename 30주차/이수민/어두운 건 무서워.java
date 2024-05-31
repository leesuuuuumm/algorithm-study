import java.io.*;
import java.util.*;

public class Main {

	static int Q, R, C;
	static int[][] map;
	static StringTokenizer st;
	static BufferedReader br;
	static int ans;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];
		sb = new StringBuilder();

		makeMap();

		getAvg();
		System.out.println(sb.toString());
	}

	private static void makeMap() throws IOException {
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int c = 1; c <= C; c++) {
				sum += Integer.parseInt(st.nextToken());
				map[r][c] = sum;

			}
		}
	}

	private static void getAvg() throws IOException {
		for (int k = 0; k < Q; k++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int tmp = 0;
			if (c1 > 0) {
				tmp = c1 - 1;
			}

			for (int r = r1; r <= r2; r++) {
				ans += (map[r][c2] - map[r][tmp]);
			}
			int avg = ans/((r2-r1+1) * (c2-c1+1));
			sb.append(avg).append("\n");

		}

	}

}
