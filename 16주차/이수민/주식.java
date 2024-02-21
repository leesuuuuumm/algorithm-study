import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int max = arr[N - 1];
			long val = 0;
			for (int i = N - 2; i >= 0; i--) {
				if (max < arr[i])
					max = arr[i];
				else if (max > arr[i]) {
					val+= (max - arr[i]);
				}
			}
			sb.append(val).append("\n");
		}
		System.out.println(sb.toString());

	}
}
