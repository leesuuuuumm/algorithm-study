package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_표병합 {

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"});
	}

	static class Solution {
		static final int N = 50;

		static int[] groups = new int[N * N];
		static String[] values = new String[N * N];
		List<String> result = new ArrayList<>();

		public String[] solution(String[] commands) {
			init();
			Arrays.stream(commands).forEach(this::process);
			return result.toArray(new String[0]);
		}

		private void process(String command) {
			String[] com = command.split(" ");

			if (com[0].equals("UPDATE")) {
				if (com.length == 3) {
					String value1 = com[1];
					String value2 = com[2];

					update(value1, value2);
				}

				if (com.length == 4) {
					int r = Integer.parseInt(com[1]) - 1;
					int c = Integer.parseInt(com[2]) - 1;
					String value = com[3];

					update(r, c, value);
				}
				return;
			}

			if (com[0].equals("MERGE")) {
				int r1 = Integer.parseInt(com[1]) - 1;
				int c1 = Integer.parseInt(com[2]) - 1;
				int r2 = Integer.parseInt(com[3]) - 1;
				int c2 = Integer.parseInt(com[4]) - 1;

				merge(r1, c1, r2, c2);
				return;
			}

			if (com[0].equals("UNMERGE")) {
				int r = Integer.parseInt(com[1]) - 1;
				int c = Integer.parseInt(com[2]) - 1;

				unmerge(r, c);
				return;
			}

			if (com[0].equals("PRINT")) {
				int r = Integer.parseInt(com[1]) - 1;
				int c = Integer.parseInt(com[2]) - 1;

				print(r, c);
				return;
			}
		}

		private void init() {
			for (int i = 0; i < N * N; i++) {
				groups[i] = i;
				values[i] = "";
			}
		}

		private void update(int r, int c, String value) {
			int idx = down(r, c);
			values[idx] = value;
		}

		private void update(String value1, String value2) {
			Arrays.stream(values).filter(v -> v.equals(value1))
				.forEach(v -> v = value2);
		}

		private void merge(int r1, int c1, int r2, int c2) {
			int idx1 = down(r1, c1);
			int idx2 = down(r2, c2);

			union(idx1, idx2);
			values[idx1] = values[idx1].isEmpty() ? values[idx2] : values[idx1];
			values[idx2] = "";
		}

		private void unmerge(int r, int c) {
			int idx = down(r, c);
			int groupIdx = find(idx);
			for (int i = 0; i < N * N; i++) {
				if (find(i) != groupIdx)
					continue;

				groups[i] = i;
			}

			if (idx == groupIdx)
				return;

			values[idx] = values[groupIdx];
			values[groupIdx] = "";
		}

		private void print(int r, int c) {
			int idx = down(r, c);
			int groupIdx = find(idx);
			System.out.println(values[groupIdx].isEmpty() ? "EMPTY" : values[groupIdx]);
		}

		private int find(int a) {
			if (a == groups[a])
				return a;

			return groups[a] = find(groups[a]);
		}

		private void union(int a, int b) {
			int pa = find(a);
			int pb = find(b);

			if (pa == pb)
				return;

			groups[pb] = pa;
		}

		private int down(int r, int c) {
			return r * N + c;
		}

		private int[] up(int v) {
			return new int[]{v / N, v % N};
		}
	}
}

