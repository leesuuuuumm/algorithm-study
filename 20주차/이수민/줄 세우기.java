import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		int[] line = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			line[b]++;
		}
		Queue<Integer> que = new LinkedList<>();

		for (int i = 1; i < line.length; i++) {
			if (line[i] == 0) {
				que.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur).append(" ");

			for (int i = 0; i < list.get(cur).size(); i++) {
				if (list.get(cur).get(i) == 0)
					continue;
				line[list.get(cur).get(i)] -= 1;

				if (line[list.get(cur).get(i)] == 0) {
					que.offer(list.get(cur).get(i));
				}

			}

		}
		System.out.println(sb.toString());

	}

}
