/*
알고리즘 코딩테스트 자바 8장
백준 18352
날짜 2023.11.15
*/
import java.io.*;
import java.util.*;

public class Beakjoon_18352 {
	static ArrayList<Integer>[]A;
	static int visited[];
	static int N,M,K,X;
	static List<Integer> answer;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		X=sc.nextInt();
		A=new ArrayList[N+1];
		answer=new ArrayList<>();
		for(int i=1;i<=N;i++) {
			A[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<M;i++) {
			int s=sc.nextInt();
			int e=sc.nextInt();
			A[s].add(e);
		}
		visited=new int[N+1];
		for(int i=0;i<=N;i++) {
			visited[i]=-1;
		}
		BFS(X);
		for(int i=0;i<=N;i++) {
			if(visited[i]==K) {
				answer.add(i);
			}
		}
		if(answer.isEmpty()) {
			System.out.println("-1");
		}
		else {
			Collections.sort(answer);
			for(int tmp:answer) {
				System.out.println(tmp);
			}
		}
	}
	static void BFS(int Node) {
		Queue<Integer> q=new LinkedList<>();
		q.add(Node);
		visited[Node]++;
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int i:A[now]) {
				if(visited[i]==-1) {
					visited[i]=visited[now]+1;
					q.add(i);
				}
			}
		}
	}

}
