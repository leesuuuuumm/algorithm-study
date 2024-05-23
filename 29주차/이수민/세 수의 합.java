import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				set.add(arr[i] + arr[j]);
			}
		}

		for (int i = 0; i < N - 1; i++) {
			set.add(arr[i] * 3);
		}

		for (int i = N - 1; i >= 1; i--) {
			if (set.contains(arr[i])) {
				System.out.println(arr[i]);
				return;
			}

			for (int j = i - 1; j >= 0; j--) {
				if (set.contains(arr[i] - arr[j])) {
					System.out.println(arr[i]);
					return;
				}
			}
		}

	}
}
