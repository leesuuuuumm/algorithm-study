import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int num;
		int cnt;

		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			if (this.cnt == o.cnt) {
				return Integer.compare(this.num, o.num);
			}

			return Integer.compare(this.cnt, o.cnt);
		}

	}

	static int R, C, K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());

		map = new int[100][100];

		for (int r = 0; r < 3; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;

		while (true) {
			if (time > 100 || map[R][C] == K)
				break;

			rotateArray();

			time++;

		}
		System.out.println(time > 100 ? -1 : time);

	}

	private static void rotateArray() {
		int rs = 0;
		int cs = 0;

		for (int r = 0; r < 100; r++) {
			int size = 0;
			for (int c = 0; c < 100; c++) {
				if (map[r][c] == 0) {
					continue;
				}
				size++;
			}
			cs = Math.max(cs, size);
		}

		for (int c = 0; c < 100; c++) {
			int size = 0;
			e: for (int r = 0; r < 100; r++) {
				if (map[r][c] == 0) {
					continue e;
				}
				size++;
			}
			rs = Math.max(rs, size);
		}

		if (rs >= cs) {
			RR();
		} else {
			CR();
		}

	}

	private static void RR() {
		for (int r = 0; r < 100; r++) {
			HashMap<Integer, Integer> hm = new HashMap<>();
			PriorityQueue<Point> pq = new PriorityQueue<>();

			for (int c = 0; c < 100; c++) {
				if (map[r][c] == 0)
					continue;
				hm.put(map[r][c], hm.getOrDefault(map[r][c], 0) + 1);
				map[r][c] = 0;

			}
			if (hm.size() == 0)
				continue;

			for (Integer i : hm.keySet()) {
				pq.offer(new Point(i, hm.get(i)));
			}

			int i = 0;
			while (!pq.isEmpty()) {
				map[r][i++] = pq.peek().num;
				map[r][i++] = pq.poll().cnt;

				if (i == 100)
					break;
			}
		}
	}

	private static void CR() {
		for (int c = 0; c < 100; c++) {
			HashMap<Integer, Integer> hm = new HashMap<>();
			PriorityQueue<Point> pq = new PriorityQueue<>();

			for (int r = 0; r < 100; r++) {
				if (map[r][c] == 0)
					continue;
				hm.put(map[r][c], hm.getOrDefault(map[r][c], 0) + 1);
				map[r][c] = 0;

			}
			if (hm.size() == 0)
				continue;

			for (Integer i : hm.keySet()) {
				pq.offer(new Point(i, hm.get(i)));
			}

			int i = 0;
			while (!pq.isEmpty()) {
				map[i++][c] = pq.peek().num;
				map[i++][c] = pq.poll().cnt;

				if (i == 100)
					break;
			}

		}

	}

}
