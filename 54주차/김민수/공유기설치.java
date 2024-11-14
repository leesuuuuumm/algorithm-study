package solve;

import java.util.*;
import java.io.*;


public class 공유기설치 {
	
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		map=new int[N];
		for(int i=0;i<N;i++) {
			map[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);
		
		int lo=1;
		int hi=map[N-1]-map[0]+1;
		while(lo<hi) {
			int mid=(lo+hi)/2;
			
			if(canInstall(mid)<C) {
				hi=mid;
			}else {
				lo=mid+1;
			}
			
		}
		System.out.println(lo-1);
		
	}
	

	
	public static int canInstall(int distance) {
		int count=1;
		int lastLocate=map[0];
		
		for(int i=1;i<map.length;i++) {
			int locate=map[i];
			
			if(locate-lastLocate>=distance) {
				count++;
				lastLocate=locate;
			}
		}
		return count;
	}
}
