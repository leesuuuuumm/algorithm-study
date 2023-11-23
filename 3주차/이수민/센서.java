import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] senser;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		senser = new int[N];
		dist = new int[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			senser[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(senser);

		for (int i = 1; i < N; i++) {
			dist[i - 1] = senser[i] - senser[i - 1];

		}
		Arrays.sort(dist);

		int min = 0;
		for (int i = 0; i < N - K; i++) {
			min += dist[i];
		}
		System.out.println(min);

	}
}
