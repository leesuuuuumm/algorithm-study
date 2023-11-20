/*
알고리즘 코딩테스트 자바 8장
백준 2252
날짜 2023.11.21
*/
import java.util.*;
import java.io.*;
public class Beakjoon_2252 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();//학생 수
		int M=sc.nextInt();//비교 횟수
		int[] degree=new int[N+1];
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		for(int i=0;i<=N;i++) {//학생은 1부터 N까지
			list.add(new ArrayList<Integer>());
		}
		for(int i=0;i<M;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			list.get(a).add(b);
			degree[b]++;
		}
		Queue<Integer> q=new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(degree[i]==0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int now=q.poll();
			System.out.print(now+" ");
			for(int next : list.get(now)) {
				degree[next]--;
				if(degree[next]==0) {
					q.add(next);
				}
			}
		}
	}

}
