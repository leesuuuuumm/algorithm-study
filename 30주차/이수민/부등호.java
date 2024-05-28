import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static String[] comp;
	static int[] selc;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		comp = new String[N];
		selc = new int[N + 1];
		v = new boolean[10];

		comp = br.readLine().split(" ");
		max = "";
		min = "";

		dfs(0);
		System.out.println(max);
		System.out.println(min);

	}

	static String max, min;

	private static void dfs(int cnt) {
		if (cnt == N + 1) {
			if (isRight()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i <= N; i++) {
					sb.append(selc[i]);
				}
				if (min.equals("") || sb.toString().compareTo(min) == -1) {
					min = sb.toString();
				}
				if (max.equals("") || max.compareTo(sb.toString()) == -1) {
					max = sb.toString();
				}
			}
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (v[i])
				continue;

			selc[cnt] = i;
			v[i] = true;
			dfs(cnt + 1);
			v[i] = false;

		}
	}

	private static boolean isRight() {
		for (int i = 0; i < N; i++) {
			if (comp[i].equals("<")) {
				if (selc[i] > selc[i + 1])
					return false;
			} else if (comp[i].equals(">")) {
				if (selc[i] < selc[i + 1]) {
					return false;
				}
			}
		}
		return true;
	}
}
