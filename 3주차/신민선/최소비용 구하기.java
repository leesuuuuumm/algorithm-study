/*
알고리즘 코딩테스트 자바 8장
백준 1916
날짜 2023.11.24
*/
import java.io.*;
import java.util.*;
class Beakjoon_1916_Node{
	int edge;
	int weight;
	Beakjoon_1916_Node(int edge, int weight){
		this.edge=edge;
		this.weight=weight;
	}
}
public class Beakjoon_1916 {
	static int N;
	static int M;
	static ArrayList<ArrayList<Beakjoon_1916_Node>> graph;
	static int[] distance;
	static boolean[] visited;
	static int INF=999999999;
	static int start;
	static int end;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		graph=new ArrayList<>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		distance=new int[N+1];
		visited=new boolean[N+1];
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			graph.get(s).add(new Beakjoon_1916_Node(e,w));
		}
		st=new StringTokenizer(br.readLine());
		start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
		dijkstra();
		System.out.println(distance[end]);
	}
	static void dijkstra() {
		for(int i=1;i<=N;i++) {
			distance[i]=INF;
		}
		distance[start]=0;
		PriorityQueue<Beakjoon_1916_Node> pq=new PriorityQueue<>((o1,o2)->{
			return o1.weight-o2.weight;
		});
		pq.add(new Beakjoon_1916_Node(start, 0));
		while(!pq.isEmpty()) {
			Beakjoon_1916_Node now=pq.poll();
			if(visited[now.edge])
				continue;
			visited[now.edge]=true;
			for(Beakjoon_1916_Node next : graph.get(now.edge)) {
				if((!visited[next.edge]) && next.weight+distance[now.edge]<distance[next.edge]) {
					distance[next.edge]=next.weight+distance[now.edge];
					pq.add(new Beakjoon_1916_Node(next.edge, distance[next.edge]));
				}
			}
		}
	}
}
