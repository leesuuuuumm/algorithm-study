/*
알고리즘 코딩테스트 자바 8장
백준 1197
날짜 2023.12.20
*/
import java.io.*;
import java.util.*;
class Edge_Beakjoon_1197{
	int s;
	int e;
	int w;
	Edge_Beakjoon_1197(int s, int e, int w){
		this.s=s;
		this.e=e;
		this.w=w;
	}
}

public class Beakjoon_1197 {
	static int N;
	static int[] parent;//union find 부모
	static PriorityQueue<Edge_Beakjoon_1197> pq;
		public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		parent=new int[N+1];
		
		pq=new PriorityQueue<>((o1,o2)->{
			return o1.w-o2.w;
		});
		
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			pq.add(new Edge_Beakjoon_1197(s,e,w));
		}
		int result=minimum_spanning_tree();
		System.out.println(result);
	}
		
	public static int minimum_spanning_tree() {
		int node_count=0;
		int result=0;
		while(node_count<N-1) {
			Edge_Beakjoon_1197 edge=pq.poll();
			if(find(edge.s)!=find(edge.e)) {
				union(edge.s, edge.e);
				node_count++;
				result+=edge.w;
			}
		}
		return result;
	}
	
	public static int find(int a) {
		if(parent[a]==a)
			return a;
		else
			return parent[a]=find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int p_a=find(a);
		int p_b=find(b);
		parent[p_a]=p_b;
	}
}
