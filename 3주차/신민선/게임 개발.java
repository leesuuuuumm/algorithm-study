/*
알고리즘 코딩테스트 자바 8장
백준 1516
날짜 2023.11.21
*/
import java.util.*;
import java.io.*;

public class Beakjoon_1516 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();//나 다음으로 지어야하는 건물들 저장.
		int[] degree=new int[N+1];//나한테 들어오는 노드 수. 즉 나보다 먼저 지어야하는 건물의 수
		int[] time=new int[N+1];//시간
		int[] result=new int[N+1];
		for(int i=0;i<=N;i++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i=1;i<=N;i++) {
			time[i]=sc.nextInt();
			int a=sc.nextInt();
			while(a!=-1) {
				list.get(a).add(i);
				degree[i]++;
				a=sc.nextInt();
			}
		}
		
		Queue<Integer> q=new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(degree[i]==0)
				q.add(i);
		}
		while(!q.isEmpty()) {
			int now=q.poll();			
			for(int next:list.get(now)) {
				degree[next]--;
				result[next]=Math.max(result[next], result[now]+time[now]);
				if(degree[next]==0) {
					q.add(next);
				}
			}
		}
		for(int i=1;i<=N;i++) {
			System.out.println(result[i]+time[i]);
		}
		
	}

}
