import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] b = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int ans = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			// 좌측 빌딩 기울기 확인 하기 (기울기가 최대값보다 작아야함)
			int cnt = 0;
			double max = 0;
			for (int j = i - 1; j >= 0; j--) {
				double num = (double) (b[i] - b[j]) / (i - j);
				if (j == i - 1 || max > num) {
					cnt++;
					max = num;
				}

			}
			// 우측 빌딩 기울기 확인 하기(기울기가 최대값보다 커야함)
			for (int j = i + 1; j < N; j++) {
				double num = (double) (b[i] - b[j]) / (i - j);
				if (j == i + 1 || max < num) {
					max = num;
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);

	}
}
