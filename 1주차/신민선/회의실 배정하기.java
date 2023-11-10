/*
알고리즘 코딩테스트 자바 6장
백준 1931
날짜 2023.11.09
*/
import java.io.*;
import java.util.*;
class Beakjoon_1931_Time{
	int start;
	int finish;
	Beakjoon_1931_Time(int start, int finish){
		this.start=start;
		this.finish=finish;
	}
}
public class Beakjoon_1931 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		PriorityQueue<Beakjoon_1931_Time> pq=new PriorityQueue<>((o1,o2)->{
			if(o1.start!=o2.start) {
				return o1.start-o2.start;
			}
			else {
				return o1.finish-o2.finish;
			}
		});
		for(int i=0;i<N;i++) {
			pq.add(new Beakjoon_1931_Time(sc.nextInt(), sc.nextInt()));
		}
		int index=0;
		int result=0;
		for(int i=0;i<N;i++) {
			Beakjoon_1931_Time t=pq.poll();
			if(index<=t.start) {//회의 시작할 수 있음.
				result++;
				index=t.finish;
			}
			else {
				if(t.finish<index) {
					index=t.finish;
				}
			}
		}
		System.out.println(result);
	}
}
