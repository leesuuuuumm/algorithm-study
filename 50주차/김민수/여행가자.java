import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int M=Integer.parseInt(br.readLine());
		int[][] map=new int[N+1][N+1];

		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<=N;i++){
			map[i][i]=1;
		}


		for(int k=1;k<=N;k++){
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(i==j)
						continue;
					if(map[i][k]==1&&map[k][j]==1){
						map[i][j]=1;
					}
				}
			}
		}
		StringTokenizer st=new StringTokenizer(br.readLine());
		int startIdx=Integer.parseInt(st.nextToken());
		for(int i=0;i<M-1;i++){
			int endIdx=Integer.parseInt(st.nextToken());
			if(map[startIdx][endIdx]==0){
				System.out.println("NO");
				return;
			}else{
				startIdx=endIdx;
			}
		}
		System.out.println("YES");
	}
}
