/*
알고리즘 코딩테스트 자바 11장
백준 13398
날짜 2024.01.16
*/
import java.io.*;
import java.util.*;
public class Beakjoon_13398 {
	static int[] L;
	static int[] R;
	static int[] a;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		L=new int[N];
		R=new int[N];
		a=new int[N];
		for(int i=0;i<N;i++) {
			a[i]=sc.nextInt();
		}
		L[0]=a[0];
		R[N-1]=a[N-1];
		int result=a[0];
		for(int i=1;i<N;i++) {
			L[i]=Math.max(a[i], a[i]+L[i-1]);
			result=Math.max(result, L[i]);
		}
		for(int i=N-2;i>=0;i--) {
			R[i]=Math.max(a[i], a[i]+R[i+1]);
		}
		
		for(int i=1;i<N-1;i++) {
			int tmp=L[i-1]+R[i+1];
			result=Math.max(result,tmp);
		}
		System.out.println(result);
		
	}

}
