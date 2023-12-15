import java.io.*;
import java.util.*;
 
public class Main {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	public static void hanoi(int N, int from, int to, int tmp) {
		if(N == 0) { 
			return;		
		}else{
			hanoi(N-1, from, tmp, to);
			sb.append(from + " " + to + "\n");
			cnt++;
			hanoi(N-1, tmp, to, from);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		hanoi(N, 1, 3, 2);
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
