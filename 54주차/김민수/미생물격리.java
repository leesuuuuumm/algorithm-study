package solve;

import java.util.*;
import java.io.*;

public class 미생물격리 {

	static int N, M, K;
	static PriorityQueue<Node> pq;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<Node>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int count=Integer.parseInt(st.nextToken());
				int dir=Integer.parseInt(st.nextToken());
				pq.add(new Node(x,y,count,dir-1));
			}
			for(int m=0;m<M;m++) {
				simulation();
			}
			int answer=0;
			while(!pq.isEmpty()) {
				Node n=pq.poll();
				answer+=n.count;
			}
			System.out.println("#"+t+" "+answer);
		}
		
		
	}

	public static void simulation() {
		int[][][] map=new int[N][N][2]; //0:미생물 개수, 1:방
		while(!pq.isEmpty()) {
			Node n=pq.poll();
			int nx=n.x+dx[n.dir];
			int ny=n.y+dy[n.dir];
			if(isInEdge(nx,ny)) {
				int newVal=n.count/2;
				int newDir=getOppositeDir(n.dir);
				map[nx][ny][0]+=newVal;
				map[nx][ny][1]=newDir;
			}else {
				map[nx][ny][0]+=n.count;
				map[nx][ny][1]=n.dir;
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j][0]>0) {
					pq.add(new Node(i,j,map[i][j][0],map[i][j][1]));
				}
			}
		}
		
	}

	public static class Node implements Comparable<Node> {
		public int x, y;
		public int count, dir;

		public Node(int x, int y, int count, int dir) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dir = dir;
		}

		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}
	
	public static boolean isInEdge(int nx, int ny) {
		return (nx==0||ny==0||nx==N-1||ny==N-1);
	}
	
	public static int getOppositeDir(int dir) {
		if(dir%2==0) {
			return dir+1;
		}else {
			return dir-1;
		}
	}

}
