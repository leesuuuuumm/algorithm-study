import java.util.*;
import java.io.*;

public class Main {

	private static int[] lotto;
	private static int N = -1;
	private static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			lotto = new int[N];
			selected = new int[6];
			for (int i = 0; i < N; i++) {
				lotto[i] = Integer.parseInt(st.nextToken());

			}
			nCr(0, 0);
			System.out.println();

		}
	}

	private static void nCr(int cnt, int start) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				System.out.print(selected[i] + " ");
			}
			System.out.println();
			return;
		} 

		for (int i = start; i < N; i++) {
			selected[cnt] = lotto[i];
			nCr(cnt + 1, i + 1);
		}

	}
}
