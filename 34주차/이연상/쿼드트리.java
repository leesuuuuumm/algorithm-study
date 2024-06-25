// [BOJ] 쿼드트리

import java.io.*;
import java.io.*;

public class Main {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		concur(0,0,N); 
		System.out.println(sb);
	}
    
	static void concur(int iStart, int jStart, int size) {
		int sum = 0;
		for (int i = iStart; i < iStart+size; i++) {
			for (int j = jStart; j < jStart+size; j++) {
				sum += map[i][j];
			}
		}

		if(sum == size*size) { 
			sb.append(1);
			return; 
		} else if(sum == 0) {
			sb.append(0);
			return;
		} else { 
			sb.append('(');
			
			concur(iStart, jStart, size/2); 
			concur(iStart, jStart+(size/2), size/2);
			concur(iStart+(size/2), jStart, size/2); 
			concur(iStart+(size/2), jStart+(size/2), size/2); 
			
			sb.append(')'); 
		}
	}
}