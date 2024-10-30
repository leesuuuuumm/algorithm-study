import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		e: for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] numbers = new String[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = br.readLine();
			}

			Arrays.sort(numbers, new Comparator<String>() {

				public int compare(String o1, String o2) {
					if (o1.compareTo(o2) == 0) {
						return Integer.compare(o1.length(), o2.length());
					}
					return o1.compareTo(o2);
				}

			});

			String s = numbers[0];
			a: for (int i = 1; i < numbers.length; i++) {
				if (s.length() <= numbers[i].length()) {
					for (int j = 0; j < s.length(); j++) {
						if (s.charAt(j) != numbers[i].charAt(j)) {
							s = numbers[i];
							continue a;
						}
					}
					sb.append("NO").append("\n");
					continue e;
				}
				s = numbers[i];
			}
			sb.append("YES").append("\n");
		}
		System.out.println(sb);

	}
}

