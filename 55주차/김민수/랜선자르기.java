import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선자르기 {

	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		map = new int[K];
		long max = 0;
		for (int i = 0; i < K; i++) {
			map[i] = Integer.parseInt(br.readLine());
			max = Math.max(map[i], max);
		}

		long left = 1;
		long right =max;

		long result=0;
		while (left <= right) {
			long mid = (right + left) / 2;
			long count = calculate(mid);
			if (count >= N){
				result=mid;
				left = mid + 1;
			}
			else
				right = mid - 1;

		}
		System.out.println(result);

	}

	public static long calculate(long val) {
		long answer = 0;
		for (int i = 0; i < map.length; i++) {
			answer += map[i] / val;
		}
		return answer;
	}
}
