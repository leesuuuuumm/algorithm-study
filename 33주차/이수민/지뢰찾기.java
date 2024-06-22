import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			char[] ch = br.readLine().toCharArray();
			int[] arr = new int[N];

			for (int i = 0; i < ch.length; i++) {
				arr[i] = ch[i] - '0';
			}

			br.readLine();

			int ans = 0;

			for (int i = 0; i < N; i++) {
				if (i == 0 && arr[i] > 0 && arr[i + 1] > 0) {
					arr[i] -= 1;
					arr[i + 1] -= 1;
					ans++;
				} else if (i > 0 && i < N - 1 && arr[i - 1] > 0 && arr[i] > 0 && arr[i + 1] > 0) {
					arr[i - 1] -= 1;
					arr[i] -= 1;
					arr[i + 1] -= 1;
					ans++;
				} else if (i == N - 1 && arr[i - 1] > 0 && arr[i] > 0) {
					arr[i - 1] -= 1;
					arr[i] -= 1;
					ans++;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
