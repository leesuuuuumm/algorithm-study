import java.io.*;
import java.util.*;

public class Main {

	static boolean[] v;
	static char[] ch;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ch = br.readLine().toCharArray();
		v = new boolean[ch.length];
		sb = new StringBuilder();

		dfs(0, ch.length - 1);
		System.out.println(sb);
	}

	private static void dfs(int start, int end) {
		int min = Integer.MAX_VALUE;
		int s = 0;

		for (int i = start; i <= end; i++) {
			if (!v[i] && ch[i] < min) {
				min = ch[i];
				s = i;
			}
		}

		if (min == Integer.MAX_VALUE)
			return;

		v[s] = true;
		for (int i = 0; i < v.length; i++) {
			if (v[i])
				sb.append(ch[i]);
		}
		sb.append("\n");

		dfs(s + 1, end);
		dfs(start, s - 1);

	}
}
