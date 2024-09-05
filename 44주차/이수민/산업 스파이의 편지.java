import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static boolean[] v;
	static String[] str;

	static HashSet<Integer> set;

	static String[] selc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			str = br.readLine().split("");

			v = new boolean[str.length];
			set = new HashSet<>();

			for (int j = 0; j < str.length; j++) {
				set.add(Integer.parseInt(str[j]));
			}

			for (int j = 1; j < str.length; j++) {
				selc = new String[j + 1];
				v = new boolean[str.length];
				nPr(0, j + 1);
			}

			int ans = 0;
			con: for (Integer j : set) {
				if (j <= 1)
					continue;
				for (int k = 2; k <= Math.sqrt(j); k++) {
					if (j % k == 0) {
						continue con;
					}
				}

				ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void nPr(int cnt, int R) {
		if (cnt == R) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < selc.length; i++) {
				sb.append(selc[i]);
			}
			set.add(Integer.parseInt(sb.toString()));
			return;
		}

		for (int i = 0; i < str.length; i++) {
			if (v[i])
				continue;

			selc[cnt] = str[i];
			v[i] = true;
			nPr(cnt + 1, R);
			v[i] = false;
		}
	}

}
