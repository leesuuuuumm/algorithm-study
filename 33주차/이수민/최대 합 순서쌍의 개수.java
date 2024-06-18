import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] sum = new long[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[0] = arr[0];
			}
			if (i > 0) {
				sum[i] = arr[i] + sum[i - 1];
			}
		}

		HashMap<Integer, Integer> mIdx = new HashMap<>();
		HashMap<Integer, Long> mVal = new HashMap<>();

		long max = 0;
		for (int i = 0; i < N; i++) {
			if (mIdx.size() == 0 || !mIdx.containsKey(arr[i])) {
				mIdx.put(arr[i], i);
				mVal.put(arr[i], (long) arr[i]);
				max = Math.max(arr[i], max);
			} else if (mIdx.containsKey(arr[i])) {
				int idx = mIdx.get(arr[i]);
				if (idx == 0) {
					mVal.put(arr[i], sum[i]);
					max = Math.max(sum[i], max);
				} else {
					mVal.put(arr[i], sum[i] - sum[idx - 1]);
					max = Math.max(max, sum[i] - sum[idx - 1]);
				}
			}
		}
		int cnt = 0;

		for (Integer i : mVal.keySet()) {
			if (mVal.get(i) == max)
				cnt++;
		}
		System.out.println(max + " " + cnt);

	}
}
