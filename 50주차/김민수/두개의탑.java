import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 두개의탑 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] sum = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		sum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		int total = sum[N - 1];
		int half = sum[N - 1] / 2;
		int max = Integer.MIN_VALUE;
		for (int i = N - 1; i >= 0; i--) {
			if (sum[i] < half)
				break;
			for (int j = 0; j < i; j++) {
				int dif = sum[i] - sum[j];
				int minVal = Math.min(dif, total - dif);
				max = Math.max(max, minVal);
			}
		}
		System.out.println(max);
	}
}
