import java.io.*;
import java.util.*;

public class Main {

	static int[] arr;
	static int N;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		N = Integer.parseInt(str);
		
		arr = new int[N];
		
		dfs(0);
		System.out.println(count);
}
	public static void dfs(int depth) {
		
		if(depth == N) {
			count++;
			return;
		}
		
		for(int i = 0 ; i < N; i++) {
			arr[depth] = i;
			if(possible(depth)) {
				dfs(depth+1);
			}
		}	
	}
	
	public static boolean possible(int col) {
		for(int i = 0 ; i < col ; i++) {
		if(arr[i]==arr[col]) {
			return false;
		}
		else if(Math.abs(col-i) == Math.abs(arr[col]-arr[i])) {
			return false;
		}
			
		}
		
		return true;
	}
}
