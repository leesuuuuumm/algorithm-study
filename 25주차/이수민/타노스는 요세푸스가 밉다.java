import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> que = new LinkedList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}

		while (que.size() > 1) {
			int f = que.poll();

			for (int i = 0; i < K - 1; i++) {
				que.poll();

				if (que.isEmpty())
					break;
			}

			que.offer(f);
		}
		System.out.println(que.poll());

	}
}
