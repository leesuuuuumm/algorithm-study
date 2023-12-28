import java.io.*;
import java.util.*;

public class Main {

	static int T, N;
	static final char[] fixed = { ' ', '+', '-' };
	static char[] selc;
	static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		answer = new StringBuilder();
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			selc = new char[N - 1];

			duplicateNPR(0);

			answer.append("\n");

		}
		System.out.println(answer.toString());

	}

	private static void duplicateNPR(int cnt) {
		if (cnt == N - 1) {
			calcuration();
			return;

		}
		for (int i = 0; i < 3; i++) {
			selc[cnt] = fixed[i];
			duplicateNPR(cnt + 1);
		}

	}

	private static void calcuration() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(i);
			if (i < N) {
				if (selc[i - 1] == ' ') {
					continue;
				}
				sb.append(" ").append(selc[i - 1]);
			}
		}

		String[] st = sb.toString().split(" ");
		int num = 0;

		for (int i = 0; i < st.length; i++) {
			num += Integer.parseInt(st[i]);
		}
		if (num == 0) {
			for (int i = 1; i <= N; i++) {
				answer.append(i);
				if (i < N) {
					answer.append(selc[i - 1]);
				}
			}
			answer.append("\n");
		}

	}
}
