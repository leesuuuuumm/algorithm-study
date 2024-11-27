import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] arr = br.readLine().split(" ");
		long N = Long.valueOf(arr[0]);
		long L = Long.valueOf(arr[1]);
		
		boolean flag = true;
		StringBuilder sb = new StringBuilder();

		while (L <= 100) {
			long start = N / L - (L - 1) / 2;
			if (start < 0)
				break;

			if (N == (start * 2 + L - 1) * L / 2) {
				for (long i = 0; i < L; i++)
					sb.append((start + i) + " ");
				flag = false;
				break;
			}

			L += 1;
		}

		if (flag)
			sb.append(-1);

		System.out.println(sb);
	}
}
