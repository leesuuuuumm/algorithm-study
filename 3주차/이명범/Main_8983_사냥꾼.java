package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_8983_사냥꾼 {

	static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	final static int INF = 1_000_000_000;
	static int M, N, L;
	static List<Integer> areas = new ArrayList<>();
	static List<Location> targets = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		input();
		Collections.sort(areas);
		int count = 0;
		for (Location cur : targets) {
			count += binarySearch(cur);
		}
		System.out.println(count);
	}

	private static int binarySearch(Location target) {
		int l = 0;
		int r = areas.size() - 1;
		while (l <= r) {
			int m = (l + r) / 2;

			int distance = calculateDistance(target, areas.get(m));

			if (distance <= L)
				return 1;

			if (target.x == areas.get(m))
				return 0;

			if (target.x > areas.get(m))
				l = m + 1;

			if (target.x < areas.get(m))
				r = m - 1;
		}
		return 0;
	}

	private static int calculateDistance(Location target, int area) {
		return Math.abs(target.x - area) + target.y;
	}

	private static void input() throws Exception {
		M = read();
		N = read();
		L = read();
		for (int i = 0; i < M; i++) {
			int area = read();
			areas.add(area);
		}
		for (int i = 0; i < N; i++) {
			int x = read();
			int y = read();
			targets.add(new Location(x, y));
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
