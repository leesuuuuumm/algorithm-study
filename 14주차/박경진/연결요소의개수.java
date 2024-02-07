import java.util.Scanner;

public class Main {
	static int graph[][];
	static boolean visit[];
	static int N = 0;
	
	public static void main(String[] args) {
		int sum = 0;
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //정점의 개수
		int M = sc.nextInt(); //간선의 개수
		graph = new int[N][N]; //정점끼리 연결돼있는지 체크
		visit = new boolean[N];
		
		for(int i = 0; i < M; i++) {
			int u = sc.nextInt(); //정점의 번호
			int v = sc.nextInt(); //정점의 번호
			graph[u-1][v-1] = 1; //연결돼있으면 1로 저장
			graph[v-1][u-1] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			if(visit[i] == false) {
				dfs(i);
				sum++;
			}
		}
		
		System.out.println(sum);
	}
	
	public static int dfs(int node) {
		visit[node] = true;
		
		for(int i = 0; i < N; i++) {
			if(i == node) continue;
			if(graph[node][i] == 1 && visit[i] == false) {
				dfs(i);
			}
		}
		
		
		return 0;
	}
}
