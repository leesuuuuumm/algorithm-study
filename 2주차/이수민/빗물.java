import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] block = new int[W];
		// 최대 - H까지 갈 수 있음
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < W - 1; i++) {
			int L = block[i];
			int R = block[i];

			for (int j = i - 1; j >= 0; j--) {
				L = Math.max(L, block[j]);
			}
			for (int j = i + 1; j < W; j++) {
				R = Math.max(R, block[j]);
			}
			ans += Math.min(L, R) - block[i];
		}
		System.out.println(ans);

	}

}
