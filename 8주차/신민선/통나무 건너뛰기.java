/*
백준 11497
날짜 2023.12.26
*/
import java.io.*;
import java.util.*;
public class Beakjoon_11497 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		for(int test_case=0;test_case<t;test_case++) {
			int N=sc.nextInt();
			int[] wood=new int[N];
			for(int i=0;i<N;i++) {
				wood[i]=sc.nextInt();
			}
			Arrays.sort(wood);//오름차순 정렬
			int min=wood[1]-wood[0];
			for(int i=3;i<N;i+=2) {
				min=Math.max(min,wood[i]-wood[i-2]);
			}
			for(int i=2;i<N;i+=2) {
				min=Math.max(min,wood[i]-wood[i-2]);
			}
			min=Math.max(min,wood[N-1]-wood[N-2]);
			System.out.println(min);
		}
	}

}
