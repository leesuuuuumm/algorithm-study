import java.util.*;
import java.io.*;

public class Main {
	static int[] root;
	static int N, M;
	static int[][] map;
	static int[] route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		StringTokenizer st;
		root = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					union(r, c);
				}
			}
		}
		route = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
			if (root[route[0]] != root[route[i]]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
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
		return root[x] = find(root[x]);
	}

}
