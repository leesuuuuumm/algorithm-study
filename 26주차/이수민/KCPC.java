import java.io.*;
import java.util.*;

public class Main {
	static class Team implements Comparable<Team> {
		int id;
		int score;
		int cnt; // 제출한 횟수
		int lt; // 마지막 제출

		public Team(int id, int score, int cnt, int lt) {
			this.id = id;
			this.score = score;
			this.cnt = cnt;
			this.lt = lt;
		}

		public int compareTo(Team o) {
			if (this.score == o.score) {

				if (this.cnt == o.cnt) {
					return Integer.compare(this.lt, o.lt);
				}

				return Integer.compare(this.cnt, o.cnt);
			}
			return Integer.compare(o.score, this.score);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int mid = Integer.parseInt(st.nextToken()); // 우리팀 id
			int m = Integer.parseInt(st.nextToken());
			PriorityQueue<Team> pq = new PriorityQueue<>();

			int[][] smap = new int[n][k];
			int[] lt = new int[n];
			int[] cnt = new int[n];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				// 팀 id, 문제번호, 획득한 점수
				int id = Integer.parseInt(st.nextToken()) - 1;
				int pnum = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				cnt[id]++;
				lt[id] = i + 1;
				smap[id][pnum] = Math.max(smap[id][pnum], s);
			}

			for (int r = 0; r < n; r++) {
				int sum = 0;
				for (int c = 0; c < k; c++) {
					sum += smap[r][c];
				}
				pq.offer(new Team(r, sum, cnt[r], lt[r]));
			}
			int j = 0;
			while (!pq.isEmpty()) {
				Team cur = pq.poll();
				j++;
				if (cur.id + 1 == mid) {
					sb.append(j).append("\n");
					break;
				}
			}

		}
		System.out.println(sb);

	}
}
