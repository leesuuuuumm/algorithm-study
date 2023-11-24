/*
알고리즘 코딩테스트 자바 8장
백준 1753
날짜 2023.11.23
*/
import java.io.*;
import java.util.*;
class Beakjoon_1753_Node{
	int target;
	int weight;
	Beakjoon_1753_Node(int target, int weight){
		this.target=target;
		this.weight=weight;
	}
}

public class Beakjoon_1753 {
	static int INF=999999999;
	static ArrayList<ArrayList<Beakjoon_1753_Node>> list;
	static int[] distance;
	static boolean[] visited;
	static PriorityQueue<Beakjoon_1753_Node> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int V=Integer.parseInt(st.nextToken());
		int E=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		int K=Integer.parseInt(st.nextToken());
		list=new ArrayList<>();
		for(int i=0;i<=V;i++) {
			list.add(new ArrayList<>());
		}
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			list.get(u).add(new Beakjoon_1753_Node(v,w));
		}
		distance=new int[V+1];
		visited=new boolean[V+1];
		pq=new PriorityQueue<Beakjoon_1753_Node>((o1, o2 )->{
			return o1.weight-o2.weight;
		});
		for(int i=1;i<=V;i++) {
			distance[i]=INF;
		}
		pq.add(new Beakjoon_1753_Node(K,0));
		distance[K]=0;
		while(!pq.isEmpty()){
			Beakjoon_1753_Node now=pq.poll();
			if(visited[now.target])
				continue;
			visited[now.target]=true;
			for(Beakjoon_1753_Node next:list.get(now.target)) {
				if(distance[next.target]>distance[now.target]+next.weight)
				{
					distance[next.target]=distance[now.target]+next.weight;
					pq.add(new Beakjoon_1753_Node(next.target,distance[next.target]));
				}
			}
		}
		for(int i=1;i<=V;i++) {
			if(visited[i])
				System.out.println(distance[i]);
			else
				System.out.println("INF");
		}
	}

}
