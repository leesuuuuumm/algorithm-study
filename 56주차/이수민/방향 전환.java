import java.util.*;
import java.io.*;

public class Solution {
	static int x2, y2, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			min = Integer.MAX_VALUE;
			dist(x1, y1, 0, true);
			dist(x1, y1, 0, false);
			System.out.println("#" + t + " " + min);
		}
	}

	private static void dist(int x1, int y1, int cnt, boolean c) {
		if (cnt > min) {
			return;
		}

		if (x1 == x2 && y1 == y2) {
			if (cnt < min) {
				min = cnt;
				return;
			}

		}

		if (c) {
			if (x1 <= x2) {
				dist(x1 + 1, y1, cnt + 1, false);
			} else if (x1 >= x2) {
				dist(x1 - 1, y1, cnt + 1, false);
			}
		} else if (!c) {
			if (y1 <= y2) {
				dist(x1, y1 + 1, cnt + 1, true);
			} else if (y1 >= y2) {
				dist(x1, y1 - 1, cnt + 1, true);
			}
		}
	}

}
