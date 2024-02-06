import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int l;
		String n;

		public Point(int l, String n) {
			this.l = l;
			this.n = n;
		}

		@Override
		public int compareTo(Point o) {
			return this.n.compareTo(o.n);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Point>> list = new ArrayList<>();

		for (int i = 0; i < P; i++) {
			list.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());

		list.get(0).add(new Point(Integer.parseInt(st.nextToken()), st.nextToken()));

		for (int i = 1; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();

			for (int j = 0; j < P; j++) {
				if (list.get(j).size() == 0
						|| list.get(j).get(0).l - 10 <= l && list.get(j).get(0).l + 10 >= l && list.get(j).size() < M) {
					list.get(j).add(new Point(l, n));
					break;
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).size() == 0)
				break;
			Collections.sort(list.get(i));
			if (list.get(i).size() < M) {
				System.out.println("Waiting!");
			} else if (list.get(i).size() == M) {
				System.out.println("Started!");
			}
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.println(list.get(i).get(j).l + " " + list.get(i).get(j).n);
			}
		}

	}
}
