package boj;

public class Main_10971_외판원순회2 {
	static int N;
	static boolean[] visited;
	static int[][] map;
	static long result_min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = read();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = read();
			}
		}

		for(int i = 0; i< N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i, 0);
		}
		System.out.println(result_min);
	}

	public static void dfs(int start, int now, long cost){
		if (allVisited()) {
			if(map[now][start]!=0){
				result_min = Math.min(result_min, cost+map[now][0]);
			}
			return;
		}

		for(int i = 1; i< N; i++){
			if (!visited[i] && map[now][i] != 0) {
				visited[i] = true;
				dfs(start, i, cost + map[now][i]);
				visited[i] = false;
			}
		}
	}

	public static boolean allVisited() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
