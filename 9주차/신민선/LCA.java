/*
알고리즘 코딩테스트 자바 9장
백준 11437
날짜 2024.01.03
*/
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static ArrayList<Integer>[] tree;
	static int[] parent;
	static int[] depth;
	static boolean[] visited;
 	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		
		tree=new ArrayList[N+1];
		parent=new int[N+1];
		depth=new int[N+1];
		visited=new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			tree[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<N-1;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			tree[a].add(b);
			tree[b].add(a);
		}
		BFS(1);
		M=sc.nextInt();
		for(int i=0;i<M;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int LCA=getLCA(a,b);
			System.out.println(LCA);
		}
	}
 	static int getLCA(int a, int b) {
 		if(depth[a]<depth[b]) {//b가 더 깊음. a가 더 깊은 거로 세팅하기 위해서 두개 바꿈.
 			int tmp=a;
 			a=b;
 			b=tmp;
 		}
 		while(depth[a]!=depth[b]) {//깊이 맞춰줌.
 			a=parent[a];
 		}
 		
 		while(a!=b) {
 			a=parent[a];
 			b=parent[b];
 		}
 		return a;
 	}
	static void BFS(int root) {
		Queue<Integer> q=new LinkedList<>();
		q.add(root);
        visited[root]=true;
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int next: tree[now]) {
				if(!visited[next]) {
					visited[next]=true;
					parent[next]=now;
					depth[next]=depth[now]+1;
					q.add(next);
				}
			}	
		}
	}
}
