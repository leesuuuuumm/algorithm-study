import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int arr[][];
	static int dist[][]; 
	static boolean visit[][];
	static int N = 0, M = 0;
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //행
		M = sc.nextInt(); //열
		sc.nextLine(); //정수 입략후 엔터 삭제
		arr = new int[N][M]; //미로 저장
		visit = new boolean[N][M];
		dist = new int[N][M]; //시작점부터 해당 위치까지의 거리
		
		
		for(int i = 0; i < N; i++) { //미로 저장
			String s = sc.nextLine(); //i행 미로 모양
			for(int j = 0; j < M; j++) {
				arr[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		
		
		//시작 점이 무조건 (0,0)이므로 큐에 넣고 빼는 bfs를 사용하면 됨
		
		bfs(0,0);
		System.out.println(dist[N-1][M-1]);
	}
	
	
	
	public static void bfs(int n1, int n2) {
		//큐에 저장하기 위해 위치 번호 계산, 행번호*6 + 열번호
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(n1,n2));
		visit[n1][n2] = true;
		dist[n1][n2] = 1;
		 
		while(!q.isEmpty()) {
			Node node = q.poll();
			int tx = node.x; //지금 위치의 x
			int ty = node.y; //지금 위치의 y
			//상하좌우 탐색
			if(tx > 0) { //위
				if(arr[tx-1][ty] == 1 && !visit[tx-1][ty]) {
					q.add(new Node(tx-1,ty));
					visit[tx-1][ty] = true;
					dist[tx-1][ty] = dist[tx][ty]+1;
				}
			}
			if(ty > 0) { //좌
				if(arr[tx][ty-1] == 1 && !visit[tx][ty-1]) {
					q.add(new Node(tx,ty-1));
					visit[tx][ty-1] = true;
					dist[tx][ty-1] = dist[tx][ty]+1;
				}
			}
			if(tx < N-1) { //아래
				if(arr[tx+1][ty] == 1 && !visit[tx+1][ty]) {
					q.add(new Node(tx+1,ty));
					visit[tx+1][ty] = true;
					dist[tx+1][ty] = dist[tx][ty] +1;
				}
			}
			if(ty < M-1) { //우
				if(arr[tx][ty+1] == 1 && !visit[tx][ty+1]) {
					q.add(new Node(tx,ty+1));
					visit[tx][ty+1] = true;
					dist[tx][ty+1] = dist[tx][ty] + 1;
				}
			}
			
			
		}
	}
}
