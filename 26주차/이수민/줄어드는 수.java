import java.io.*;
import java.util.*;

public class Main {

	static int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	static ArrayList<Long> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();

		dfs(0, 0);
		Collections.sort(list);

		if (N > list.size())
			System.out.println(-1);
		else {
			System.out.println(list.get(N - 1));
		}

	}

	private static void dfs(long num, int cnt) {
		if (cnt == 10) {
			if (!list.contains(num)) {
				list.add(num);
			}
			return;
		}

		dfs(num, cnt + 1);
		dfs(num * 10 + arr[cnt], cnt + 1);

	}

}
