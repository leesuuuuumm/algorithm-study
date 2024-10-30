import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드 와샬

public class 궁금한민호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] origin = new int[N + 1][N + 1];
		int[][] dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				dist[i][j] = origin[i][j];
			}
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {

					if (i == j || i == k || j == k) continue;

					if(dist[i][j]>dist[i][k]+dist[k][j]){
						System.out.println(-1);
						return;
					}
					if(dist[i][j]==dist[i][k]+dist[k][j]){
						origin[i][j]=0;
					}
				}
			}
		}
		int result=0;
		for(int i=1;i<=N;i++){
			for(int j=i+1;j<=N;j++){
				result+=origin[i][j];
			}
		}
		System.out.println(result);
	}
}
