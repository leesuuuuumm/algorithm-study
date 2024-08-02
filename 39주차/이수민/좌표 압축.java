import java.io.*;
import java.util.*;
 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeSet<Integer> set = new TreeSet<>();

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		int[] tmp = new int[set.size()];

		int j = 0;
		for (Integer i : set) {
			tmp[j++] = i;
		}

		StringBuilder sb = new StringBuilder();
		int L = 0;
		int R = N - 1;
		for (int i = 0; i < N; i++) {
			L = 0;
			R = tmp.length - 1;

			while (L <= R) {
				int mid = (L + R) / 2;

				if (tmp[mid] >= arr[i]) {
					R = mid - 1;
				} else {
					L = mid + 1;
				}
			}
			sb.append(R + 1).append(" ");

		}
		System.out.println(sb.toString());
	}
}
