import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] sum = new int[N];
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				if (i - 1 < 0) {
					sum[i] += 1;
				} else
					sum[i] = sum[i - 1] + 1;
			} else {
				if (i - 1 >= 0) {
					sum[i] = sum[i - 1];
				}
			}
		}
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			int xnum = 0;
			int ynum = 0;

			if (x - 1 >= 0)
				xnum = sum[x - 1];

			if (y - 1 >= 0)
				ynum = sum[y - 1];

			sb.append(ynum - xnum).append("\n");
		}
		System.out.println(sb);

	}
}
