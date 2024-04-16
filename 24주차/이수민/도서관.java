import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> p = new ArrayList<>();
		ArrayList<Integer> m = new ArrayList<>();
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (max < Math.abs(num)) {
				max = Math.abs(num);
			}
			if (num > 0) {
				p.add(num);
			} else {
				m.add(Math.abs(num));
			}
		}

		Collections.sort(p, Collections.reverseOrder());
		Collections.sort(m, Collections.reverseOrder());
		int dist = 0;

		for (int i = 0; i < p.size(); i++) {
			if (i % M == 0 && p.get(i) == max) {
				dist += p.get(i);
			} else if (i % M == 0) {
				dist += p.get(i) * 2;
			}
		}

		for (int i = 0; i < m.size(); i++) {
			if (i % M == 0 && m.get(i) == max) {
				dist += m.get(i);

			} else if (i % M == 0) {
				dist += m.get(i) * 2;

			}
		}
		System.out.println(dist);

	}

}
