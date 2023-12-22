/*
알고리즘 코딩테스트 자바 8장
백준 1414
날짜 2023.12.21
*/
import java.io.*;
import java.util.*;

class Edge{
	int s;
	int e;
	int w;
	Edge(int s, int e, int w){
		this.s=s;
		this.e=e;
		this.w=w;
	}
}

public class Beakjoon_1414 {
	static int N;
	static PriorityQueue<Edge> pq;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		int sum=0;
		pq=new PriorityQueue<>((o1,o2)->{
			return o1.w-o2.w;
		});
		parent=new int[N+1];	
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			for(int j=0;j<N;j++) {
				char c=s.charAt(j);
				if(c!='0') {
					int w;
					if('a'<= c && c<='z')
						w=c-'a'+1;
					else
						w=c-'A'+1+26;
					pq.add(new Edge(i,j,w));
					sum+=w;
				}
				
			}
		}
		if(N==1 && pq.size()==0)
			System.out.println(0);
		else {
			int used=minimun_spanning_tree();
			if(used==-1) {
				System.out.println(-1);
			}
			else {
				System.out.println(sum-used);
			}
		}
	}
	public static int minimun_spanning_tree() {
		int count=0;
		int result=0;
		while(!pq.isEmpty()) {
			Edge edge=pq.poll();
			System.out.printf("edge: %d %d %d\n", edge.s, edge.s, edge.w);
			if(find(edge.s)!=find(edge.e)) {
				union(edge.s,edge.e);
				result+=edge.w;
				count++;
			}
			if(count==N-1)
				return result;
		}
		return -1;
	}
	
	public static void union(int a, int b) {
		int parent_a=find(a);
		int parent_b=find(b);
		parent[parent_a]=parent_b;
	}
	public static int find(int a) {
		if(parent[a]==a)
			return a;
		return parent[a]=find(parent[a]);
	}
}
