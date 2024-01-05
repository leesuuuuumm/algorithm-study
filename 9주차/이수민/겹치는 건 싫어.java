import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		HashMap<Integer, Integer> map = new HashMap<>();

		int s = 0;
		int e = 0;
		map.put(arr[0], 1);

		int max = 1;

		while (s < N - 1 && e < N - 1) {
			if (e < N - 1 && (s == e || !map.containsKey(arr[e + 1]) || map.get(arr[e + 1]) + 1 <= K)) {
				e++;
				map.put(arr[e], map.getOrDefault(arr[e], 0) + 1);
			} else {
				if (map.get(arr[s]) == 1) {
					map.remove(arr[s]);
				} else {
					map.put(arr[s], map.get(arr[s]) - 1);
				}
				s++;
			}
			max = Math.max(max, (e - s) + 1);

		}
		System.out.println(max);

	}
}
