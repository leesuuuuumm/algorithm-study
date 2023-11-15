/*
알고리즘 코딩테스트 자바 7장
Beakjoon_11689
날짜 2023.11.13
*/
import java.io.*;
import java.util.*;
public class Beakjoon_11689 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long result=n;
		for(long i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0) {//소인수(뒤에 코드에서 나누기로 제거해서 약수가 소인수임이 증명됨)
				result=result-result/i;//i의 배수의 갯수를 빼줌.
				while(n%i==0){
					n/=i;
				}
			}
		}
		if(n>1)
			result=result-result/n;
		System.out.println(result);
	}

}
