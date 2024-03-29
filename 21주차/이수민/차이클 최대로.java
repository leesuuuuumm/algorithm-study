import java.io.*;
import java.util.*;

public class Main {

	static int N, max;
	static int[] arr, num;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new int[N]; 
		num = new int[N]; 
		v = new boolean[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;

		nPr(0);
		System.out.println(max);
	}

	private static void nPr(int cnt) {

		if (cnt == N) {
			int tmp = 0;

			for (int i = 0; i < N - 1; i++) {
				tmp += Math.abs(arr[i] - arr[i + 1]);
			}
			max = Math.max(max, tmp);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;
			arr[cnt] = num[i];

			v[i] = true;
			nPr(cnt + 1);
			v[i] = false;

		}

	}
}
