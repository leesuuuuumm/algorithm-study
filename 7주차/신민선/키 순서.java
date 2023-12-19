/*
백준 2458
날짜 2023.12.19
*/
import java.io.*;
import java.util.*;
public class Beakjoon_2458 {
	static int N;
	static int M;
	static int[][] distance;
	static int INF=500000001;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		distance=new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==j)
					distance[i][j]=0;
				else
					distance[i][j]=INF;
			}
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			distance[a][b]=1;
		}
		
		floyd_warshall();
		int result=find();
		System.out.println(result);
	}
	static public void floyd_warshall() {
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(distance[i][j]>distance[i][k]+distance[k][j])
						distance[i][j]=distance[i][k]+distance[k][j];
				}
			}
		}
	}
	static public int find() {
		int[] small=new int[N+1];
		int[] big=new int[N+1];
		int count=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(distance[i][j]!=INF && distance[i][j]!=0) {
					big[i]++;
					small[j]++;
				}
			}
		}
		for(int i=1;i<=N;i++) {
			if(small[i]+big[i]==N-1) {
				count++;
			}
		}
		return count;
	}

}
