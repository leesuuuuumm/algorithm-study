/*
알고리즘 코딩테스트 자바 6장
백준 1744
날짜 2023.11.08
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1744 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		ArrayList<Integer>list=new ArrayList<>();
		for(int i=0;i<N;i++) {
			list.add(sc.nextInt());
		}
		Collections.sort(list);
		int sum=0;
		int index=0;
		while(0<=index && index<N && list.get(index)<0) {
			int a=list.get(index);
			index++;
			if(0>index || index>=N) {//수가 더 없음.
				sum+=a;
				break;
			}
			if(list.get(index)==0) {//다음 수가 0인 경우
				index++;
				break;
			}
			else if(list.get(index)>0){//다음 수가 0보다 큰 경우
				sum+=a;
				index++;
				break;
			}
			else {
				int b=list.get(index);
				sum+=a*b;
				index++;
			}
		}

		index=N-1;
		while(0<=index && index<N && list.get(index)>1) {
			int a=list.get(index);
			index--;
			if(0>index || index>=N) {//수가 더 없음.
				sum+=a;
				break;
			}
			if(list.get(index)==1) {
				sum+=a+1;
				index--;
				break;
			}
			else if(list.get(index)<1) {
				sum+=a;
				index--;
				break;
			}
			else {
				int b=list.get(index);
				sum+=a*b;
				index--;
			}
		}
		while(0<=index && index<N && list.get(index)==1) {
			sum++;
			index--;
		}
		System.out.println(sum);
	}

}
