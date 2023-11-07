/*
알고리즘 코딩테스트 자바 5장
Beakjoon_2343
날짜 2023.11.07
*/
import java.io.*;
import java.util.*;
public class Beakjoon_2343 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int start=0;
		int finish=0;
		int mid=0;
		int[] list=new int[N];
		for(int i=0;i<N;i++) {
			int a=sc.nextInt();
			list[i]=a;
			start=Math.max(start,a);
			finish+=a;
		}
		while(start<=finish){
			mid=(start+finish)/2;
			//System.out.printf("start: %d, finish: %d, mid: %d\n",start, finish,mid);
			int sum=0;//강의 길이 합
			int count=0;//사용한 블루레이 숫자
			for(int i=0;i<N;i++) {
				if(sum+list[i]<=mid) {//지금 블루레이에 강의를 더 추가해도 mid를 넘지 않음.
					sum+=list[i];
				}
				else {//블루레이에 추가하면 mid를 넘어감. 블루레이를 교체
					count++;
					sum=list[i];
				}
			}
			if(sum!=0)//블루레이가 하나 더 필요함.
				count++;
			if(count<=M)//주어진 블루레이 안에 다 저장할 수 있음.
				finish=mid-1;//크기를 줄여나감.
			else
				start=mid+1;
		}
		
		System.out.println(start);
	}

}
