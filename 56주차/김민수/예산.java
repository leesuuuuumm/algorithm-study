import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 예산 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int right = -1;
		int left = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		int budget = Integer.parseInt(br.readLine());

		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] > mid) {
					sum += mid;
				} else
					sum += arr[i];
			}
			if (sum > budget) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(left-1);

	}
}
