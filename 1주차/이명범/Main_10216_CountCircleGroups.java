package boj;

import java.util.HashSet;
import java.util.Set;

public class Main_10216_CountCircleGroups {

	static class Area {
		int x;
		int y;
		int r;

		public Area(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	static int T, N;
	static Area[] areas;
	static int[] groups;

	public static void main(String[] args) throws Exception {
		T = read();
		StringBuilder result = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = read();
			areas = new Area[N];
			groups = new int[N];
			for (int i = 0; i < N; i++) {
				int x = read();
				int y = read();
				int r = read();

				areas[i] = new Area(x, y, r);
				groups[i] = i;
			}
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (isUnion(i, j))
						continue;
					if (!contains(areas[i], areas[j]))
						continue;

					union(i, j);
				}
			}
			Set<Integer> count = new HashSet<>();
			for (int i = 0; i < N; i++) {
				count.add(find(groups[i]));
			}
			result.append(count.size()).append("\n");
		}
		System.out.println(result);
	}

	private static boolean contains(Area a, Area b) {
		int distance = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
		int range = (a.r + b.r) * (a.r + b.r);

		return distance <= range;
	}

	private static int find(int a) {
		if (groups[a] == a)
			return a;
		return groups[a] = find(groups[a]);
	}

	private static boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return;

		groups[pb] = pa;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
