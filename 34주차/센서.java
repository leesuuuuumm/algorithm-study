// [BOJ] 센서

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt(); 
		if(k >= n) {
			System.out.println(0);
			return;
		}
		int sum = 0;
		
		int[] sensorArr = new int[n];
		for(int i = 0; i < n; i++)
			sensorArr[i] = sc.nextInt();
		
		Arrays.sort(sensorArr);
		
		Integer[] diffArr = new Integer[n-1];
		for(int i = 0; i < n-1; i++)
			diffArr[i] = sensorArr[i+1] - sensorArr[i];
		
		Arrays.sort(diffArr, Collections.reverseOrder());
		for(int i = k-1; i < n-1; i++) {
			sum += diffArr[i];
		}
		System.out.println(sum);
	}
}
