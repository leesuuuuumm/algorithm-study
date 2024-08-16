import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] A = st.nextToken().split("");
		String[] B = st.nextToken().split("");
		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		arr[0] = Integer.parseInt(A[0]);
		arr[1] = Integer.parseInt(A[1]);
		arr[N - 2] = Integer.parseInt(B[0]);
		arr[N - 1] = Integer.parseInt(B[1]);


		a: for (int i = 0; i < N - 1; i++) {
			if (arr[i + 1] == 0) {
				e: for (int j = 1; j <= 9; j++) {
					int num = arr[i] * 10 + j;

					for (int k = 2; k <= (int) Math.sqrt(num); k++) {
						if (num % k == 0) {
							continue e;
						}
					}
					arr[i + 1] = j;
					continue a;
				}
				System.out.println(-1);

			} else {
				if (arr[i] == 0) { // 마지막에서 N-2

					e: for (int j = 1; j <= 9; j++) {
						int num = (j * 10) + arr[i + 1];

						for (int k = 2; k <= (int) Math.sqrt(num); k++) {
							if (num % k == 0)
								continue e;
						}
						arr[i] = j;
						continue a;
					}
					System.out.println(-1);
					return;

				} else { // 1
					int num = (arr[i] * 10) + arr[i + 1];

					for (int k = 2; k <= (int) Math.sqrt(num); k++) {
						if (num % k == 0) {
							System.out.println(-1);
							return;
						}
					}

				}
			}

		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<arr.length;i++) {
			sb.append(arr[i]);
		}
		System.out.println(sb);

	}
}
