import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N];

		int max = 0;
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, map[i]);
			sum += map[i];
		}

		Arrays.sort(map);
		int left = 0;
		int right = max;
		int answer = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			long count = calculate(mid);
			if (count >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}

	public static long calculate(int val) {
		long answer = 0;
		for (int i = 0; i < map.length; i++) {
			if (map[i] > val) {
				answer += map[i] - val;
			}
		}
		return answer;
	}
}
