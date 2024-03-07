import java.io.*;
import java.util.*;

public class Main {

	static int L, C;
	static String[] selected;
	static String[] encryption;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		selected = new String[L];
		v = new boolean[C];
		encryption = br.readLine().split(" ");
		ans = new StringBuilder();

		Arrays.sort(encryption);

		nPr(0);
		System.out.println(ans.toString().trim());

	}

	static StringBuilder ans;

	private static void nPr(int cnt) {
		if (cnt == L) {
			int count = 0;

			for (int i = 0; i < selected.length; i++) {
				if (selected[i].contains("a") || selected[i].contains("e") || selected[i].contains("i")
						|| selected[i].contains("o") || selected[i].contains("u")) {
					count++;
				}

			}

			if (count >= 1 && L - count >= 2) {

				for (int i = 0; i < selected.length; i++) {
					ans.append(selected[i]);
				}
				ans.append("\n");
			}

			return;
		}

		for (int i = 0; i < C; i++) {
			if (v[i])
				continue;

			if (cnt >= 1 && selected[cnt - 1].charAt(0) - encryption[i].charAt(0) > 0) {
				continue;
			}

			selected[cnt] = encryption[i];
			v[i] = true;
			nPr(cnt + 1);
			v[i] = false;

		}
	}
}

