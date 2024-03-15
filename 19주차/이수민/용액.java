import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int n;
		int m;

		public Point(int n, int m) {
			this.n = n;
			this.m = m;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.n, o.n);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point[] arr = new Point[N];
		for (int i = 0; i < N; i++) {
			int k = Integer.parseInt(st.nextToken());
			if (k < 0) {
				arr[i] = new Point(k * -1, k);
			} else {
				arr[i] = new Point(k, k);
			}
		}
		Arrays.sort(arr);

		int min = Integer.MAX_VALUE;
		int[] brr = new int[2];

		for (int i = 0; i < N - 1; i++) {
			if (Math.abs(arr[i + 1].m + arr[i].m) < min) {
				brr[0] = arr[i].m;
				brr[1] = arr[i + 1].m;
				min = Math.abs(arr[i + 1].m + arr[i].m);
			}
		}
		Arrays.sort(brr);
		System.out.println(brr[0] + " " + brr[1]);
	}
}
