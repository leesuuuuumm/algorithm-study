import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> list = new ArrayList<>();
		a: for (int i = 0; i < N; i++) {
			if (list.size() == 0 || list.get(list.size() - 1) < arr[i]) {
				list.add(arr[i]);
			} else {
				int L = 0;
				int R = list.size() - 1;

				while (L <= R) {
					int mid = (L + R) / 2;

					if (list.get(mid) > arr[i]) {
						R = mid - 1;
					} else if (list.get(mid) < arr[i]) {
						L = mid + 1;
					} else if (arr[i] == list.get(mid))
						continue a;
				}
				list.set(L, arr[i]);
			}
		}
		System.out.println(list.size());

	}
}
