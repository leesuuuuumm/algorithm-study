/*
알고리즘 코딩테스트 자바 8장
백준 11404
날짜 2023.12.18
*/
import java.io.*;
import java.util.*;

public class Beakjoon_11404 {
	static int INF=10000001;//뒤에서 덧샘 연산 해야해서 MAX_VALUE로 하면 범위 벗어남.
	static int N;
	static int M;
	static int[][] distance;
	static public void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		distance=new int[N+1][N+1];//도시는 1~N. 0으로 초기화. 거리는 0보다 큰 자연수.
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++){
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
			int c=Integer.parseInt(st.nextToken());
			if(c < distance[a][b]) {//더 짧은 값으로 갱신
				distance[a][b]=c;
			}
		}

		floyd_warshall();
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(distance[i][j]==INF)
					System.out.printf("%d ",0);
				else
					System.out.printf("%d ",distance[i][j]);
			}
			System.out.println();
		}
	}
	static public void floyd_warshall() {
		for(int k=1;k<=N;k++) {// 경유지
			for(int i=1;i<=N;i++) {//출발지
				for(int j=1;j<=N;j++) {//도착지
					if(distance[i][j]>distance[i][k]+distance[k][j])
						distance[i][j]=distance[i][k]+distance[k][j];
				}
			}
		}
	}
}
