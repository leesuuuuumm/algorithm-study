/*
알고리즘 코딩테스트 자바 8장
백준 17472
날짜 2023.12.20
*/
import java.io.*;
import java.util.*;
class Node_Beakjoon_17472{
	int x;
	int y;
	Node_Beakjoon_17472(int x, int y){
		this.x=x;
		this.y=y;
	}
}
class Edge_Beakjoon_17472{
	int s;
	int e;
	int w;
	Edge_Beakjoon_17472(int s, int e, int w){
		this.s=s;
		this.e=e;
		this.w=w;
	}
}
public class Beakjoon_17472 {
	static int N;
	static int M;
	static int island;
	static int[][] map;
	static boolean[][] visited;
	static int[][] bridge;//섬-섬 다리길이의 최솟값을 저장. 1보다 커야함.
	static boolean[] find;//다리 길이를 찾은 섬인지 확인(시작하는 섬)
	static int[] dx= {0,0,-1,1};
	static int[] dy= {-1,1,0,0};
	static PriorityQueue<Edge_Beakjoon_17472> pq;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		visited=new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		island=make_island();
		
		find=new boolean[island+1];
		bridge=new int[island+1][island+1];
		visited=new boolean[N+1][M+1];
		pq=new PriorityQueue<>((o1,o2)->{
			return o1.w-o2.w;
		});
		parent=new int[island+1];
		
		for(int i=1;i<=island;i++) {
			for(int j=1;j<=island;j++){
				bridge[i][j]=Integer.MAX_VALUE;
			}
		}
		for(int i=1;i<=island;i++) {
			parent[i]=i;
		}
		
		make_bridge();
		
		int result=minimum_spanning_tree();
		System.out.println(result);
	}
	public static int make_island() {
		int num=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j])
					continue;
				if(map[i][j]==0)
					continue;
				BFS_island(i,j,num);
				num++;
			}
		}
		return num-1;
	}
	public static void BFS_island(int x, int y, int num) {
		Queue<Node_Beakjoon_17472> q=new LinkedList<>();
		q.add(new Node_Beakjoon_17472(x,y));
		visited[x][y]=true;
		map[x][y]=num;
		
		while(!q.isEmpty()) {
			Node_Beakjoon_17472 node=q.poll();
			for(int i=0;i<4;i++) {
				int next_x=dx[i]+node.x;
				int next_y=dy[i]+node.y;
				if(can_go(next_x, next_y) && !visited[next_x][ next_y] && map[next_x][next_y]!=0) {
					q.add(new Node_Beakjoon_17472(next_x, next_y));
					map[next_x][next_y]=num;
					visited[next_x][next_y]=true;
				}					
			}
		}
	}
	public static boolean can_go(int x, int y) {
		if(0<=x && x<N && 0<=y && y<M) {
			return true;
		}
		else
			return false;
	}
	public static void make_bridge() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0)
					continue;
				if(!find[map[i][j]]) {
					BFS_bridge(i,j,map[i][j]);
					find[map[i][j]]=true;
				}
			}
		}
	}
	public static void BFS_bridge(int x, int y, int num) {
		Queue<Node_Beakjoon_17472> q=new LinkedList<>();
		q.add(new Node_Beakjoon_17472(x,y));
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			Node_Beakjoon_17472 node=q.poll();
			
			for(int i=0;i<4;i++) {
				int next_x=dx[i]+node.x;
				int next_y=dy[i]+node.y;
				
				if(can_go(next_x, next_y)) {
					if(map[next_x][next_y]==num) {
						if(!visited[next_x][next_y]) {
							q.add(new Node_Beakjoon_17472(next_x, next_y));
							visited[next_x][next_y]=true;
						}
					}
					else {//거리 찾기
						int distance=1;
						next_x+=dx[i];
						next_y+=dy[i];
						while(can_go(next_x,next_y) && map[next_x][next_y]==0) {
							next_x+=dx[i];
							next_y+=dy[i];
							distance++;
						}
						if(can_go(next_x,next_y)) {//다른 섬 만나서 멈춘거.
							//섬-섬 다리 길이 최솟값으로 업데이트. 1은 안됨.
							if(distance!=1) {
								bridge[num][map[next_x][next_y]]=Math.min(distance,bridge[num][map[next_x][next_y]]);
								bridge[map[next_x][next_y]][num]=Math.min(distance,bridge[map[next_x][next_y]][num]);
							}
						}
					}
				}
			}
		}
	}
	public static int minimum_spanning_tree() {
		int count=0;
		int result=0;
		for(int i=1;i<=island;i++) {//엣지리스트 생성
			for(int j=i;j<=island;j++) {
				if(bridge[i][j]!=Integer.MAX_VALUE) {
					pq.add(new Edge_Beakjoon_17472(i,j,bridge[i][j]));
				}
			}
		}
		while(!pq.isEmpty()) {
			Edge_Beakjoon_17472 edge=pq.poll();
			if(find(edge.s)!=find(edge.e)) {
				union(edge.s, edge.e);
				count++;
				result+=edge.w;
			}
			if(count==island-1)
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
