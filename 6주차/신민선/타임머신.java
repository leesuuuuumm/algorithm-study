/*
알고리즘 코딩테스트 자바 8장
백준 11657
날짜 2023.12.13
*/
import java.io.*;
import java.util.*;
class Edge{
	int s;
	int e;
	int w;
	Edge(int s,int e,int w){
		this.s=s;
		this.e=e;
		this.w=w;
	}
}
public class Beakjoon_11657 {
	static long INF=Long.MAX_VALUE;
	static long[] distance;
	static ArrayList<Edge> edgeList;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		distance=new long[N+1];
		edgeList=new ArrayList<>();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(s,e,w));
		}
		
		distance[1]=(long) 0;
		for(int i=2;i<=N;i++) {
			distance[i]=INF;
		}
		Bellman_ford();
	}
	static public void Bellman_ford(){
		for(int i=1;i<N;i++) {
			for(int j=0;j<M;j++) {
				Edge e=edgeList.get(j);
				
				if(distance[e.s]!=INF && distance[e.e]>distance[e.s]+e.w) {
					distance[e.e]=distance[e.s]+e.w;
				}
			}
		}
		boolean cycle=false;
		for(int i=0;i<M;i++) {
			Edge e=edgeList.get(i);
			if(distance[e.s]!=INF && distance[e.e]>distance[e.s]+e.w) {
				distance[e.e]=distance[e.s]+e.w;
				cycle=true;
			}
		}
		if(cycle) {
			System.out.println("-1");
		}
		else {
			for(int i=2;i<=N;i++) {
				if(distance[i]!=INF)
					System.out.println(distance[i]);
				else
					System.out.println("-1");
			}
		}
	}

}
