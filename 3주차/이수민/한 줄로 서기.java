import java.io.*;
import java.util.*;

public class Main {

	static int[] selc;
	static boolean[] v;
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		selc = new int[N];
		v = new boolean[N];
		stop = false;

		nPr(0);

	}

	static boolean stop;

	private static void nPr(int cnt) {
		if (!stop && cnt == N) {
			for (int i = 0; i < N; i++) {
				int tCnt = 0;
				for (int j = 0; j < N; j++) {
					if (selc[j] == i + 1)
						break;

					if (selc[j] > i + 1) {
						tCnt++;
					}
				}
				if (tCnt != arr[i])
					return;
			}
			stop = true;
			for (int i = 0; i < N; i++) {
				System.out.print(selc[i] + " ");
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;

			selc[cnt] = i + 1;
			v[i] = true;
			nPr(cnt + 1);
			v[i] = false;
		}

	}
}
