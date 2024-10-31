import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 폭탄던지는태영이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] input = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				input[i][j] = -Long.parseLong(st.nextToken());
			}
		}
		int r = M / 2;
		int start = r + 1;
		int end = N - r;
		long[][] result = new long[N + 1][N + 1];

		for (int i = start; i <= end; i++) {
			for (int j = start; j <= end; j++) {
				result[i][j] =
					input[i - r][j - r] - input[i - r - 1][j - r] - input[i - r][j - r - 1] + input[i - r - 1][j - r
						- 1];
				if (i - M >= 0)
					result[i][j] += result[i - M][j];
				if (j - M >= 0)
					result[i][j] += result[i][j - M];
				if (i - M >= 0 && j - M >= 0)
					result[i][j] -= result[i - M][j - M];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
