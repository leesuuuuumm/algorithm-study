import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map = new char[5][5];
	static int[] pos = new int[7];
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int result =0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 조합
		Combination(0, 0);
		System.out.println(result);
	}
	
	public static void Combination(int start, int count) {
		if(count==7) {
			// 가로 세로 인접 체크
			if(BFS()) result++;
			return;
		}
		
		for(int i=start; i<25; i++) {
			pos[count] = i;
			Combination(i+1, count+1);
		}
	}
	
	public static boolean BFS() {
		
		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];
		boolean[][] temp = new boolean[5][5];
		
		for(int i=0; i<7; i++) {
			int p = pos[i];
			temp[p/5][p%5] = true;
		}
		int s_count = 0;
		int count = 0;
		
		q.add(new Location(pos[0]/5, pos[0]%5));
		visited[pos[0]/5][pos[0]%5]=true;
		
		
		while(!q.isEmpty()) {
			
			Location cur = q.poll();
			count++;
			
			int cx = cur.x;
			int cy = cur.y;
			
			if(map[cy][cx] == 'S') s_count++;
			
			for(int i=0; i<4; i++) {
				int ny = cy + dir[i][0];
				int nx = cx + dir[i][1];
				
				if(ny<0 || nx<0 || ny>=5 || nx >=5) continue;
				if(visited[ny][nx]) continue;
				if(!temp[ny][nx]) continue;
				
				
				q.add(new Location(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
		return count == 7 && s_count>=4;
	}
	static class Location{
		int x;
		int y;
		public Location(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}
}