import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] solution = new int[N];
		for (int i = 0; i < N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(solution);
		int s = 0;
		int e = solution.length - 1;

		int s1 = solution[s];
		int s2 = solution[e];

		int min = Integer.MAX_VALUE;
		while (s != e) {

			if (min > Math.abs(solution[s] + solution[e])) {
				s1 = solution[s];
				s2 = solution[e];

				min = Math.abs(solution[s] + solution[e]);
			}

			if (min == 0)
				break;

			if (solution[s] + solution[e] < 0) {
				s++;
			} else {
				e--;
			}
		}
		System.out.println(s1 + " " + s2);

	}
}
