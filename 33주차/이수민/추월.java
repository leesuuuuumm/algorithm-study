import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			map.put(br.readLine(), i);
		}
		int[] out = new int[N];
		for (int i = 0; i < N; i++) {
			out[i] = map.get(br.readLine());
		}

		int cnt = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (out[i] > out[j]) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
