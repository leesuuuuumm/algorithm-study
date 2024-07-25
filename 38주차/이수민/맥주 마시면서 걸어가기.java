import java.util.*;
import java.io.*;

public class Main {

	static ArrayList<ArrayList<Integer>> distance;
	static int[][] map;

	static int answer, N;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			int x = 0;
			int y = 0;
			distance = new ArrayList<>();
			que = new LinkedList<>();
			map = new int[N + 2][N + 2];

			v = new boolean[N + 2];
			answer = 0;
			for (int i = 0; i < N + 2; i++) {
				distance.add(new ArrayList<>());
			}
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				distance.get(i).add(x);
				distance.get(i).add(y);
			}

			for (int i = 0; i < N + 2; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					if ((Math.abs(distance.get(i).get(0) - distance.get(j).get(0)))
							+ (Math.abs(distance.get(i).get(1) - distance.get(j).get(1))) <= 1000) {
						map[i][j] = 1;
						map[j][i] = 1;

					}

				}
			}
			floydWarshall();
			System.out.println(map[0][N+1]==1 ? "happy" : "sad");


		}
	}


	private static void floydWarshall() {
		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;

					}

				}
			}

		}

	}
}
