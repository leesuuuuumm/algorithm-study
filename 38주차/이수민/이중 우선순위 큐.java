import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String d = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				if (d.equals("D") && map.size() == 0)
					continue;

				if (d.equals("I")) {
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else if (d.equals("D") && num == -1) {
					int key = map.firstKey();
					if (map.get(key) == 1) {
						map.remove(key);
					} else {
						map.put(key, map.get(key) - 1);
					}
				} else if (d.equals("D") && num == 1) {
					int key = map.lastKey();
					if (map.get(key) == 1) {
						map.remove(key);
					} else {
						map.put(key, map.get(key) - 1);
					}
				}
			}

			if (map.size() == 0) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.println(sb);

	}
}
