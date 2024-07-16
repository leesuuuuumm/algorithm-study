import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] m = new int[4];
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m.length; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		String[] level = br.readLine().split("");

		int sum = 0;

		for (int i = 0; i < level.length; i++) {

			if (level[i].equals("B")) {
				sum = m[0] - 1;

				if (i == 0) {
					arr[0] = sum;
					continue;
				}
				arr[i] = sum - arr[i - 1];
                
			} else if (level[i].equals("S")) {
				sum = m[1] - 1;

				if (i == 0) {
					arr[0] = sum;
					continue;
				}

				arr[i] = sum - arr[i - 1];

			} else if (level[i].equals("G")) {
				sum = m[2] - 1;

				if (i == 0) {
					arr[0] = sum;
					continue;
				}

				arr[i] = sum - arr[i - 1];

			} else if (level[i].equals("P")) {
				sum = m[3] - 1;

				if (i == 0) {
					arr[0] = sum;
					continue;
				}

				arr[i] = sum - arr[i - 1];

			} else if (level[i].equals("D")) {
				arr[i] = m[3];

			}
		}

		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			ans+= arr[i];
		}
		System.out.println(ans);

	}

}
