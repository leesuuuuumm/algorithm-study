import java.io.*;
import java.util.*;

public class Main {

	static boolean[] v;
	static int N;
	static HashSet<Integer> set;
	static int[] arr;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			M += num;
			arr[i] = num;
		}
		set = new HashSet<>();
		v = new boolean[N];

		subset(0);
		System.out.println(M - (set.size() - 1));
	}

	private static void subset(int cnt) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (v[i]) {
					sum += arr[i];
				}
			}
			set.add(sum);
			return;
		}
		v[cnt] = true;
		subset(cnt + 1);
		v[cnt] = false;
		subset(cnt + 1);
	}

}
