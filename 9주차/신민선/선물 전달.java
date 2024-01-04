/*
알고리즘 코딩테스트 자바 10장
백준 1947
날짜 2024.01.04
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1947 {

	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		int N=sc.nextInt();
		long mod=1000000000;
		long [] D=new long[1000001];
		D[1]=0;
		D[2]=1;
		for(int i=3;i<=N;i++) {
			D[i]=(i-1)*(D[i-1]+D[i-2])%mod;
		}
		System.out.println(D[N]);
	}

}
