/*
알고리즘 코딩테스트 자바 8장
백준 1219
날짜 2023.12.15
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
public class Beakjoon_1219 {
	static int N;
	static int M;
	static int start;
	static int end;
	static long INF=Long.MAX_VALUE;
	static long[] distance;
	static int[] get;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		distance=new long[N];
		get=new int[N];
		edgeList=new ArrayList<>();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(s,e,w));
		}
		st=new StringTokenizer(br.readLine());

		for(int i=0;i<N;i++) {
			get[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<N;i++) {
			distance[i]=Long.MIN_VALUE;
		}
		distance[start]=get[start];
		Bellman_ford();
		
	}
	static public void Bellman_ford() {
		for(int i=0;i<=N+100;i++) {
			for(int j=0;j<M;j++) {
				Edge e=edgeList.get(j);
				if(distance[e.s]==Long.MIN_VALUE)
					continue;
				else if(distance[e.s]==INF){
					distance[e.e]=INF;
				}
				else if(distance[e.e]<distance[e.s]+get[e.e]-e.w) {
					distance[e.e]=distance[e.s]+get[e.e]-e.w;
					if(N-1<=i) {
						distance[e.s]=INF;
					}
				}
			
			}
		}
		if(distance[end]==INF)
			System.out.println("Gee");
		else if(distance[end]==Long.MIN_VALUE)
			System.out.println("gg");
		else 
			System.out.println(distance[end]);
	}
}
