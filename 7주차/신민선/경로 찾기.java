/*
알고리즘 코딩테스트 자바 8장
백준 11403
날짜 2023.12.18
*/
import java.io.*;
import java.util.*;
public class Beakjoon_11403 {
	static int N;
	static int[][] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		distance=new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int w=Integer.parseInt(st.nextToken());
				distance[i][j]=w;
			}
		}
		
		floyd_warshall();
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				System.out.printf("%d ", distance[i][j]);
			}
			System.out.println();
		}
		
	}
	static public void floyd_warshall() {
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(distance[i][k] ==1 && distance[k][j]==1) {
						distance[i][j]=1;
					}
				}
			}
		}
	}
}
