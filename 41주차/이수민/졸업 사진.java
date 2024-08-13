import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> { 
		String name;
		int s;
		int e;
		int num;

		public Point(String name, int s, int e, int num) {
			this.name = name;
			this.s = s;
			this.e = e;
			this.num = num;

		}

		public int compareTo(Point o) {
			if (this.num == o.num) {
				if (this.name.compareTo(o.name) == 0) {
					return Integer.compare(this.s, o.s);
				}
				return this.name.compareTo(o.name);
			}
			return Integer.compare(o.num, this.num);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		HashSet<String> set = new HashSet<>();
		TreeMap<String, int[]> map1 = new TreeMap<>();

		int allMax = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String place = st.nextToken();
			if (set.contains(name))
				continue;
			set.add(name);

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (!map1.containsKey(place)) {
				map1.put(place, new int[50001]);
			}

			int max = 0;
			for (int j = s; j < e; j++) {
				map1.get(place)[j]++;
				max = Math.max(max, map1.get(place)[j]);
			}

			allMax = Math.max(max, allMax);
		}
		PriorityQueue<Point> pq = new PriorityQueue<>();

		for (String i : map1.keySet()) {
			int max = 0;
			int s = 0;
			int e = 0;
			for (int j = 1; j < map1.get(i).length; j++) {
				max = Math.max(max, map1.get(i)[j]);
			}

			boolean f = false;
			for (int j = 1; j < map1.get(i).length; j++) {
				if (!f && map1.get(i)[j] == max) {
					s = j;
					f = true;
				}

				if (f && (map1.get(i)[j] < max || j == map1.get(i).length - 1)) {
					e = j;
					break;
				}
			}

			pq.offer(new Point(i, s, e, max));
		}

		Point cur = pq.poll();
		System.out.println(cur.name + " " + cur.s + " " + cur.e);

	}
}
