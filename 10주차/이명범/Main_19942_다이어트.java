package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_19942_다이어트 {

	static class Ingredient {
		int p;
		int f;
		int s;
		int v;
		int c;

		public Ingredient(int p, int f, int s, int v, int c) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}

	static int N, mp, mf, ms, mv;
	static Ingredient[] ingredients;
	static int min;
	static List<String> minString = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < N; i++) {
			solve(0, 0, 0, i + 1);
		}
		output();
	}

	private static void solve(int cnt, int start, int flag, int limit) {
		if (cnt == limit) {
			int sp = 0;
			int sf = 0;
			int ss = 0;
			int sv = 0;
			int total = 0;
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) == 0)
					continue;
				sp += ingredients[i].p;
				sf += ingredients[i].f;
				ss += ingredients[i].s;
				sv += ingredients[i].v;
				total += ingredients[i].c;
			}
			if (mp <= sp && mf <= sf && ms <= ss && mv <= sv && min >= total) {
				if (min != total) {
					minString.clear();
				}
				min = total;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < N; i++) {
					if ((flag & 1 << i) == 0)
						continue;
					sb.append(i + 1).append(" ");
				}
				sb.setLength(sb.length() - 1);
				minString.add(sb.toString());
			}
			return;
		}

		for (int i = start; i < N; i++) {
			solve(cnt + 1, i + 1, flag | 1 << i, limit);
		}
	}

	private static void output() {
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		Collections.sort(minString);
		StringBuilder sb = new StringBuilder();
		sb.append(min).append("\n").append(minString.get(0));
		System.out.print(sb);
	}

	private static void input() throws Exception {
		N = read();
		mp = read();
		mf = read();
		ms = read();
		mv = read();
		ingredients = new Ingredient[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int p = read();
			int f = read();
			int s = read();
			int v = read();
			int c = read();
			ingredients[i] = new Ingredient(p, f, s, v, c);
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
