import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int s;
		int e;

		public Point(int s, int e) {
			this.s = s;
			this.e = e;
		}

		public int compareTo(Point o) {
			if (this.s == o.s) {
				return Integer.compare(o.e, this.e);
			}
			return Integer.compare(this.s, o.s);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Point> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] arr = br.readLine().split(" ");
			String s = "";
			String e = "";

			for (int j = 0; j < 2; j++) {
				if (Integer.parseInt(arr[j]) < 10) {
					s += ("0" + arr[j]);
				} else {
					s += arr[j];
				}

			}

			for (int j = 2; j < 4; j++) {
				if (Integer.parseInt(arr[j]) < 10) {
					e += "0" + arr[j];
				} else {
					e += arr[j];
				}
			}
			list.add(new Point(Integer.parseInt(s), Integer.parseInt(e)));
		}
		Collections.sort(list);

		int m = 0;
		int ans = 0;
		int s = 301;
		int idx = 0;

		while (s < 1201) {
			boolean f = false;

			for (int i = idx; i < N; i++) {

				if (list.get(i).s > s)
					break;
				if (list.get(i).s <= s && m < list.get(i).e) {
					m = list.get(i).e;
					idx = i + 1;
					f = true;
				}
			}

			if (f) {
				s = m;
				ans++;
			} else
				break;

		}
		System.out.println(m < 1201 ? 0 : ans);

	}
}
