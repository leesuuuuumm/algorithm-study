/*
알고리즘 코딩테스트 자바 5장
Beakjoon_1300
날짜 2023.11.07
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1300 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		
		int start=1;
		int finish=K;
		int mid=0;
		int result=0;
		
		while(start<=finish) {
			mid=(start+finish)/2;
			int count=0;
			for(int i=1;i<=N;i++) {
				count+=Math.min(mid/i,N);
			}
			if(count<K) {//더 큰 수로 설정해야함.
				start=mid+1;
			}
			else {//이게 답일 수도 있고 더 작은 수가 답일 수도 있음.(같은 수가 중복으로 있기 때문)
				finish=mid-1;
				result=mid;
			}
		}
		System.out.println(result);
		
	}

}
