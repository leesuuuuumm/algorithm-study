import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean[] v;
	static int tmp;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		v = new boolean[N + 1];
		num = new int[N + 1];
		for (int i = 1; i < num.length; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			v[i] = true;
			tmp = i;
			dfs(i);
			v[i] = false;
		}

		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));

		}

	}

	static ArrayList<Integer> list;

	private static void dfs(int cnt) {
		if (tmp == num[cnt]) {
			list.add(tmp);
		}
		if (!v[num[cnt]]) {
			v[num[cnt]] = true;
			dfs(num[cnt]);
			v[num[cnt]] = false;
		}
	}
}
