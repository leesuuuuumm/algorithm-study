package solve;

import java.util.*;
import java.io.*;

public class 카드섞기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int[] mix=new int[N];
		int[] result=new int[N];
		for(int i=0;i<N;i++) {
			result[i]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			mix[i]=Integer.parseInt(st.nextToken());
		}
		
		boolean flag=false;
		int answer=0;
		int[] cur=new int[N];
		
		for(int i=0;i<N;i++) {
			cur[i]=(i%3);
		}
		while(!flag) {
			if(isEqualResult(cur, result, N))
				break;
			int[] tmp=new int[N];
			answer+=1;
			for(int i=0;i<N;i++) {
				tmp[i]=cur[mix[i]];
			}
			cur=tmp;
			if(isEqualFirst(cur, N)) {
				answer=-1;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	public static boolean isEqualFirst(int[] result, int N) {
		
		int[] first=new int[N];
		
		for(int i=0;i<N;i++) {
			first[i]=(i%3);
		}
		
		for(int i=0;i<N;i++) {
			if(result[i]!=first[i])
				return false;
		}
		return true;
	}
	
	public static boolean isEqualResult(int[] cur, int[] result, int N) {
		for(int i=0;i<N;i++) {
			if(result[i]!=cur[i])
				return false;
		}
		return true;
	}
}
