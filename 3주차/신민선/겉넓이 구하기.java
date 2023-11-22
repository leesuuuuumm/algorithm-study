/*
백준 1931
날짜 2023.11.21
*/
import java.util.*;
import java.io.*;
public class Beakjoon_16931 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int[][] table=new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				table[i][j]=sc.nextInt();
			}
		}
		int sum=0;
		for(int i=0;i<N;i++) {
			int a=table[i][0];
			//System.out.println("a: "+a);
			sum+=a;
			for(int j=1;j<M;j++) {
				int b=table[i][j];
				if(a<b) {
					sum+=(b-a);
					a=b;
				}
				else {
					sum+=(a-b);
					a=b;
				}
				//System.out.printf("%d ", sum);
			}
			//System.out.println();
				sum += (table[i][M-1]);
			//System.out.println("total: "+sum);
		}
		for(int i=0;i<M;i++) {
			int a=table[0][i];
			sum+=a;
			//System.out.println("a: "+a);

			for(int j=1;j<N;j++) {
				int b=table[j][i];
				if(a<b) {
					sum+=(b-a);
					a=b;
				}
				else {
					sum+=(a-b);
					a=b;
					//System.out.printf("%d ", sum);

				}
			}
			//System.out.println();
				sum += (table[N-1][i]);
			//System.out.println("total: "+sum);
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(table[i][j]!=0)
					sum+=2;
			}
		}
		
		System.out.println(sum);
	}

}
