import java.io.*;
import java.util.*;

public class Main {
    	static int N;
        static int[][] work;

   public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		work = new int[N][N];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				work[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new boolean[N];
		min = Integer.MAX_VALUE;
		nCr(0, 0);
		System.out.println(min);

	}

	static boolean[] selected;
	static int min;

	private static void nCr(int cnt, int start) {
		if (cnt == N / 2) {
			int morning = 0;
			int night = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (selected[i] && selected[j]) {
						morning += work[i][j];
					} else if (!selected[i] && !selected[j]) {
						night += work[i][j];
					}
				}
			}
			min = Math.min(Math.abs(morning - night), min);
			return;
		}

		for (int i = start; i < N; i++) {
			selected[i] = true;
			nCr(cnt + 1, i + 1);
			selected[i] = false;

		}

	}
}
