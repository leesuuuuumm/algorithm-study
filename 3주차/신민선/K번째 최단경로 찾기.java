/*
알고리즘 코딩테스트 자바 8장
백준 1854
날짜 2023.11.24
*/
import java.io.*;
import java.util.*;
class Beakjoon_1854_Node{
	int edge;
	int weight;
	Beakjoon_1854_Node(int edge, int weight){
		this.edge=edge;
		this.weight=weight;
	}
}
public class Beakjoon_1854 {
	static int N;
	static int M;
	static int K;
	static ArrayList<ArrayList<Beakjoon_1854_Node>> graph;
	static PriorityQueue<Integer>[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		graph=new ArrayList<>();
		distance=new PriorityQueue[N+1];
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
			distance[i]=new PriorityQueue<Integer>(Collections.reverseOrder());
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			graph.get(s).add(new Beakjoon_1854_Node(e,w));
		}
		k_dijkstra();
		for(int i=1;i<=N;i++) {
			if(distance[i].size()==K) {//K번째가 있음.
				System.out.println(distance[i].peek());
			}
			else {
				System.out.println(-1);
			}
		}
		
	}
	static void k_dijkstra() {
		distance[1].add(0);//시작점 1의 dis는 0임.
		PriorityQueue<Beakjoon_1854_Node> pq=new PriorityQueue<>((o1,o2)->{
			return o1.weight-o2.weight;
		});
		pq.add(new Beakjoon_1854_Node(1,0));
		while(!pq.isEmpty()) {
			Beakjoon_1854_Node now=pq.poll();
			//System.out.println("now: "+now.edge);
			for(Beakjoon_1854_Node next : graph.get(now.edge)) {
				int count=distance[next.edge].size();
				int cost=next.weight+now.weight;
				//System.out.println("next: "+next.edge + " count: "+count + " cost: "+cost);
				if(count<K) {//그냥 삽입
					distance[next.edge].add(cost);
					pq.add(new Beakjoon_1854_Node(next.edge, cost));
				}
				else {//가장 큰걸 지우고 삽입
					if(cost<distance[next.edge].peek()) {
						distance[next.edge].poll();
						distance[next.edge].add(cost);
						pq.add(new Beakjoon_1854_Node(next.edge, cost));
					}
				}
			}
		}
	}
}
