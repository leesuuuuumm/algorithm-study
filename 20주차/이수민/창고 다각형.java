import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});

		int midx = 0;
		int max = arr[0][1];

		for (int i = 1; i < N; i++) {
			if (max < arr[i][1]) {
				midx = i;
				max = arr[i][1];
			}
		}
		int ans = arr[midx][1];

		int h = arr[0][1];
		for (int i = 0; i < midx; i++) {
			if (h < arr[i + 1][1]) {
				ans += (arr[i + 1][0] - arr[i][0]) * h;
				h = arr[i + 1][1];
			} else {
				ans += (arr[i + 1][0] - arr[i][0]) * h;
			}
		}

		h = arr[N - 1][1];
		for (int i = N - 1; i > midx; i--) {

			if (h < arr[i - 1][1]) {
				ans += (arr[i][0] - arr[i - 1][0]) * h;
				h = arr[i - 1][1];
			} else {
				ans += (arr[i][0] - arr[i - 1][0]) * h;
			}

		}
		System.out.println(ans);

	}
}
