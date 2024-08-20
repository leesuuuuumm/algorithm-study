// [BOJ] 꽃길

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	
	public static String T;
	public static int N;
	public static int[][] map;
	public static boolean[][] visited;
	public static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	map = new int[N][N];
    	visited = new boolean[N][N];
    	
    	for(int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<N;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken()); 
    		}
    	}
    
        simulate(0, 0); 
    	
		System.out.println(result);
    	
 
    }
    
    public static void simulate(int level, int total) {
    	if(level == 3) {
    		result = Math.min(result,  total);
    		return ;
    	}
    	
    	for(int i=1;i<N-1;i++) {
    		for(int j=1;j<N-1;j++) {
    			
    			if(visited[i][j] == true) continue;
    			if(visited[i-1][j] == true) continue;
    			if(visited[i][j-1] == true) continue;
    			if(visited[i+1][j] == true) continue;
    			if(visited[i][j+1] == true) continue;
    			
    			visited[i][j] = true;
    			visited[i-1][j] = true;
    			visited[i][j-1] = true;
    			visited[i+1][j] = true;
    			visited[i][j+1] = true;
    			int sum = map[i][j] + map[i-1][j] + map[i][j-1] + map[i+1][j] + map[i][j+1];
    			
    			simulate(level + 1, total + sum);
    			visited[i][j] = false;
    			visited[i-1][j] = false;
    			visited[i][j-1] = false;
    			visited[i+1][j] = false;
    			visited[i][j+1] = false;
    			
    		}
    	}
 
    }
   
}