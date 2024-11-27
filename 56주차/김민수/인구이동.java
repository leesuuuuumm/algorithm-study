import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {
	static int N, L, R;

	//상, 좌, 하, 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		boolean[][] visited = new boolean[N][N];
		int flag = 0;
		while (flag < N * N) {
			flag = 0;
			int[][] newMap=new int[N][N];
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					newMap[i][j]=map[i][j];
				}
			}
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						flag += 1;
						ArrayList<int[]> merge = new ArrayList<>();
						Queue<int[]> que = new ArrayDeque<>();
						merge.add(new int[] {i, j});
						que.add(new int[] {i, j});
						visited[i][j] = true;
						int sum=map[i][j];
						while (!que.isEmpty()) {
							int[] q=que.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nx = q[0] + dx[dir];
								int ny = q[1] + dy[dir];
								if (isInRange(nx, ny) && !visited[nx][ny]) {
									int dif = Math.abs(map[nx][ny] - map[q[0]][q[1]]);
									if (dif >= L && dif <= R) {
										visited[nx][ny] = true;
										que.add(new int[] {nx, ny});
										merge.add(new int[] {nx, ny});
										sum+=map[nx][ny];
									}
								}
							}
						}

						if(merge.size()>1){
							int div=sum/merge.size();
							for(int[] m:merge){
								newMap[m[0]][m[1]]=div;
							}
						}
					}
				}
			}
			if(flag==N*N)
				break;

			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					map[i][j]=newMap[i][j];
				}
			}
			answer += 1;
		}
		System.out.println(answer);

	}

	public static boolean isInRange(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}
