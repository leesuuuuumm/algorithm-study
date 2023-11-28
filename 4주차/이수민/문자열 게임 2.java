import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			char[] chr = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());

			HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

			for (int i = 0; i < chr.length; i++) {
				if (!map.containsKey(chr[i])) {
					map.put(chr[i], new ArrayList<>());
				}
				map.get(chr[i]).add(i);
			}
			int max = -1;
			int min = Integer.MAX_VALUE;

			for (Character i : map.keySet()) {
				if (map.get(i).size() >= K) {

					for (int j = 0; j <= map.get(i).size() - K; j++) {
						max = Math.max(max, (map.get(i).get(j + K - 1) - map.get(i).get(j)) + 1);
						min = Math.min(min, (map.get(i).get(j + K - 1) - map.get(i).get(j)) + 1);
					}

				}
			}
      
			if (max == -1 && min == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(min).append(" ").append(max).append("\n");
			}
		}
    
		System.out.println(sb.toString());
	}

}
