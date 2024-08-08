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
		for (int i = 0; i < N; i++) {
			int L = 0;
			int R = list.size() - 1;

			while (L <= R) {
				int mid = (L + R) / 2;

				if (list.get(mid) < arr[i]) {
					L = mid + 1;
				} else {
					R = mid - 1;
				}
			}
			if (list.size() <= L) {
				list.add(arr[i]);
			} else {
				list.set(L, arr[i]);
			}

		}
		System.out.println(list.size());

	}
}
