/*
알고리즘 코딩테스트 자바 7장
백준 1456
날짜 2023.11.10
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1456 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long A=sc.nextLong();
		long B=sc.nextLong();
		int size=(int) Math.sqrt(B);//뒤에서 차피 소수의 거듭제곱의 수 구하는거여서 루트B보다 작은 소수만 구하면 됨.
		long[] check = new long[size+1];
		for(int i=0;i<=size;i++) {
			check[i]=i;
		}
		check[1]=0;//1은 소수가 아님.
		for(int i=2;i<=size;i++) {
			if(check[i]==0) {//이미 소수가 아님.
				continue;
			}
			for(int j=i+i;j<=size;j=j+i) {//배수 찾기
				check[j]=0;
			}
		}
		long result=0l;
		for(long i=2l;i<=size;i++) {
			if(check[(int) i]!=0) {//소수이면
				for(long j=i;j<=B/i;j=j*i) {//거듭제곱 구하기
					if(A<=j*i) {
						result++;
					}
				}
			}			
		}
		System.out.println(result);
	}
}
