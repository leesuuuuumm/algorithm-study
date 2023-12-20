import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] truck = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}

		Queue<Integer> que = new LinkedList<>();

		for (int i = 0; i < w; i++) {
			que.offer(0);
		}
		int sum = 0;
		int cnt = 0;
		int i = 0;

		while (que.peek() != -1) {

			if (que.peek() != 0) {
				sum -= que.poll();
			} else {
				que.poll();
			}
			if (i < n) {

				if (sum + truck[i] <= L) {
					que.offer(truck[i]);
					sum += truck[i];
					i++;
				} else {
					que.offer(0);
				}

			} else {

				que.offer(-1);
			}

			cnt++;

		}
		System.out.println(cnt);

	}
}
