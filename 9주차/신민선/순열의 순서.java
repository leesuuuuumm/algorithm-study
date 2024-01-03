/*
알고리즘 코딩테스트 자바 10장
백준 1722
날짜 2024.01.03
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1722 {
	static int N;
	static long K;
	static long[] F;
	static int[] S;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		int q=sc.nextInt();
		S=new int[N+1];
		F=new long[N+1];
		visited=new boolean[N+1];
		F[0]=1;
		for(int i=1;i<=N;i++) {
			F[i]=F[i-1]*i;
		}
		
		if(q==1) {//k입력 받기
			K=sc.nextLong();
			for(int i=1;i<=N;i++) {
				for(int j=1,cnt=1;j<=N;j++) {//
					if(visited[j])
						continue;
					if(K<=cnt*F[N-i]) {
						K=K-(F[N-i]*(cnt-1));
						S[i]=j;
						visited[j]=true;
						break;
					}
					cnt++;
				}
			}
			for(int i=1;i<=N;i++) {
				System.out.print(S[i]+" ");
			}
		}
		else {//순열 입력 받기
			K=1;
			for(int i=1;i<=N;i++) {
				S[i]=sc.nextInt();
				long cnt=0;
				for(int j=1;j<S[i];j++) {
					if(visited[j]==false) {
						cnt++;
					}
				}
				K+=cnt*F[N-i];
				visited[S[i]]=true;
			}
			System.out.println(K);
		}
	}

}
