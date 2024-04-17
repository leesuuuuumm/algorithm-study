import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Character> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(br.readLine().charAt(0));
		}

		StringBuilder sb = new StringBuilder();

		while (true) {
			if (list.size() == 3) {
				if (list.get(0) <= list.get(list.size() - 1)) {
					sb.append(list.remove(0));
				} else {
					sb.append(list.remove(2));
				}
			}
			if (list.size() < 3) {
				Collections.sort(list);
				for (int i = 0; i < list.size(); i++) {
					sb.append(list.get(i));
				}
				break;
			}

			char a = list.get(0);
			char b = list.get(list.size() - 1);

			if (a < b) {
				sb.append(list.remove(0));
			} else if (a > b) {
				sb.append(list.remove(list.size() - 1));
			} else if (a == b) {
				int s = 0;
				int e = list.size() - 1;
				while (true) {
					if (list.get(s) == list.get(e)) {
						s++;
						e--;
					}

					if (s == e || s > e || list.get(s) > list.get(e)) {
						sb.append(list.remove(list.size() - 1));
						break;
					} else if (list.get(s) < list.get(e)) {
						sb.append(list.remove(0));
						break;
					}
				}
			}

		}
		int len = sb.length();
		for (int i = 1; i <= len; i++) {
			System.out.print(sb.substring(i - 1, i));
			if (i % 80 == 0) {
				System.out.println();
			}
		}
	}
}
