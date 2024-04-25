import java.util.*;
import java.io.*;

public class Main {

	static int M, N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		selc = new int[M];
		set = new TreeSet<>();
		nCr(0, 0);

		Iterator iter = set.iterator();
		if (set.size() == 0)
			System.out.println(-1);
		else
			while (iter.hasNext()) {
				System.out.print(iter.next() + " ");
			}

	}

	static int[] selc;

	private static void nCr(int cnt, int start) {
		if (cnt == M) {
			checkPrime();
			return;
		}

		for (int i = start; i < N; i++) {
			selc[cnt] = i;
			nCr(cnt + 1, i + 1);

		}
	}

	static TreeSet<Integer> set;

	private static void checkPrime() {
		int sum = 0;
		for (int i = 0; i < selc.length; i++) {
			sum += arr[selc[i]];
		}

		for (int i = 2; i < sum / 2; i++) {
			if (sum % i == 0) {
				return;
			}
		}
		set.add(sum);
	}
}
