import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추 {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			boolean[][] visited = new boolean[N][M];
			int answer=0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					Queue<int[]> que = new ArrayDeque<>();
					if (!visited[i][j]) {
						visited[i][j] = true;
						if(map[i][j]==1)
							que.add(new int[]{i,j});
					}
					if(!que.isEmpty())
						answer+=1;
					while(!que.isEmpty()){
						int[] pos=que.poll();

						for(int d=0;d<4;d++){
							int nx=pos[0]+dx[d];
							int ny=pos[1]+dy[d];
							if(isInRange(nx,ny)&&!visited[nx][ny]&&map[nx][ny]==1){
								visited[nx][ny]=true;
								que.add(new int[]{nx,ny});
							}
						}
					}
				}
			}
			System.out.println(answer);

		}

	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < M);
	}

}
