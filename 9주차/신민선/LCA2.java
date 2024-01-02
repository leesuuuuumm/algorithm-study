/*
알고리즘 코딩테스트 자바 9장
백준 11438
날짜 2024.01.03
*/
import java.io.*;
import java.util.*;
public class Beakjoon_11438 {
	static int N;
	static int M;
	static ArrayList<Integer>[] tree;
	static int[][] parent;
	static int[] depth;
	static boolean[] visited;
	static int kmax;
 	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		tree=new ArrayList[N+1];
		depth=new int[N+1];
		visited=new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			tree[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<N-1;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		kmax=0;
		int tmp=1;
		while(tmp<=N) {//depth 최댓값 구하기. 2의 몇제곱까지 갈 수 있는지
			tmp<<=1;
			kmax++;
		}
		parent=new int[kmax+1][N+1];
		
		BFS(1);
		
		for(int k=1;k<=kmax;k++) {
			for(int n=1;n<=N;n++) {
				parent[k][n]=parent[k-1][parent[k-1][n]];
			}
		}
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
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
 		for(int k=kmax;k>=0;k--) {
 			if(Math.pow(2,k)<=depth[a]-depth[b]) {
 				if(depth[b]<=depth[parent[k][a]]) {
 					a=parent[k][a];
 				}
 			}
 		}
 		for(int k=kmax;k>=0;k--) {
 			if(parent[k][a]!=parent[k][b]) {
 				a=parent[k][a];
 				b=parent[k][b];
 			}
 		}
 		int LCA=a;
 		if(a!=b)
 			LCA=parent[0][LCA];
 		return LCA;
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
					parent[0][next]=now;
					depth[next]=depth[now]+1;
					q.add(next);			
					}
			}	
		}
	}
}
