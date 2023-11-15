import java.io.*;
import java.util.*;

public class Main {
	static class Words implements Comparable<Words> {
		String word;
		int cnt;

		public Words(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Words o) {
			if (o.cnt == this.cnt) {
				if (o.word.length() == this.word.length()) {
					for (int i = 0; i < word.length(); i++) {
						if (this.word.charAt(i) != o.word.charAt(i)) {
							return Integer.compare(this.word.charAt(i), o.word.charAt(i));
						}
					}
				}
				return Integer.compare(o.word.length(), this.word.length());
			}
			return Integer.compare(o.cnt, this.cnt);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if (s.length() < M)
				continue;
			map.put(s, map.getOrDefault(s, 0) + 1);
		}

		PriorityQueue<Words> pq = new PriorityQueue<>();

		for (String k : map.keySet()) {
			pq.offer(new Words(k, map.get(k)));
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			sb.append(pq.poll().word).append("\n");
		}
		System.out.println(sb);
	}
}

//  시간 초과 난 이유: ..... 출력할 때... StringBuilder로 모아서 안하고... 그냥 출력했기 때문... 그냥 바보..ㅎㅅㅎ..
