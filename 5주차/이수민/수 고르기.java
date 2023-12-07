import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int s = 0;
		int e = 0;
		long answer = Long.MAX_VALUE;

		while (true) {
			if (arr[e] - arr[s] < M) {
				e++;
			} else if (arr[e] - arr[s] > M) {
				answer = Math.min(arr[e] - arr[s], answer);
				s++;
			}

			if (e == N)
				break;

			if (arr[e] - arr[s] == M) {
				answer = M;
				break;
			}
		}
		System.out.println(answer);

	}

}
