/*
알고리즘 코딩테스트 자바 7장
Beakjoon_1016
날짜 2023.11.13
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1016 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long min=sc.nextLong();
		long max=sc.nextLong();
		
		boolean check[]=new boolean[(int) ((max-min)+1)];//0=min~ max 까지
		
		for(long i=2; i*i<=max;i++) {
			long pow=i*i;
			long start=min/pow;
			if(min%pow!=0)//나머지 있으면 1을 더해줘야함. min=10, max 20, i=2 pow=4 start=3 j*pow=12
				start++;
			for(long j=start;j*pow<=max;j++) {
				//System.out.printf("j: %d, pow :%d\n",j,pow);;
				check[(int) ((j*pow)-min)]=true;
			}
		}
		int count=0;
		for(int i=0;i<=max-min;i++) {
			if(check[i]==false) {
				//System.out.println(i+min);
				count++;
			}
		}
		System.out.println(count);
	}
}
