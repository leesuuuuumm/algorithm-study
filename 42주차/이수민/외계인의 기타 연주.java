import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}

		});

		Stack<int[]> stack = new Stack<>();
		int ans = 0;

		for (int i = 0; i < N; i++) {
			if (stack.isEmpty()) {
				stack.add(new int[] { arr[i][0], arr[i][1] });
				ans++;
				continue;

			}
			if (stack.peek()[0] != arr[i][0]) {
				stack.clear();
			}

			if (!stack.isEmpty() && stack.peek()[1] > arr[i][1]) {
				while (!stack.isEmpty() && stack.peek()[1] > arr[i][1]) {
					ans++;
					stack.pop();
				}
			} if (!stack.isEmpty() && stack.peek()[1] == arr[i][1]) {
				continue;
			}

			stack.push(new int[] { arr[i][0], arr[i][1] });
			ans++;
		}
		System.out.println(ans);

	}
}
