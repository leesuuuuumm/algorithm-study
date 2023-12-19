import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] root;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		root = new int[N + 1];
		list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}

		st = new StringTokenizer(br.readLine());

		int k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < k; i++) {
			root[Integer.parseInt(st.nextToken())] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());

			list.add(new ArrayList<>());
			list.get(i).add(first);

			if (k == 1)
				continue;
			for (int j = 1; j < k; j++) {
				int m = Integer.parseInt(st.nextToken());
				list.get(i).add(m);
				union(first, list.get(i).get(j));

			}

		}

		int ans = 0;
		e: for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				if (find(list.get(i).get(j)) == 0)
					continue e;

			}
			ans++;
		}

		System.out.println(ans);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA > rootB) {
			root[rootA] = rootB;
		} else {
			root[rootB] = rootA;
		}

	}

	private static int find(int x) {
		if (root[x] == x)
			return x;
		else
			return root[x] = find(root[x]);
	}

}
