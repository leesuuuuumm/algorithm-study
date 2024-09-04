import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(list, Collections.reverseOrder());

		int ans = 0;
		while (list.size() > 1) {
			int a = list.get(0);
			int b = list.get(1);

			if (a > 0 && b > 0) {
				if (a == 1 || b == 1) {
					ans += (a + b);
				} else {
					ans += (a * b);
				}
				list.remove(0);
				list.remove(0);
			}

			else if (a > 0 && b <= 0) {
				ans += list.remove(0);
			} else if (a == 0 && b < 0) {
				if (list.size() == 2) {
					list.remove(0);
				}
				list.remove(0);
				break;
			} else if (a <= 0 && b <= 0) {
				break;
			}
		}
		Collections.sort(list);

		while (list.size() > 1) {
			int a = list.remove(0);
			int b = list.remove(0);

			ans += (a * b);
		}

		for (int i = 0; i < list.size(); i++) {
			ans += list.get(i);
		}
		System.out.println(ans);

	}
}
