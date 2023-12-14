/*
백준 18870
날짜 2023.12.13
*/
import java.io.*;
import java.util.*;
public class Beakjoon_18870 {
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuilder sb = new StringBuilder();
	        HashMap<Integer, Integer> map = new HashMap<>();

	        int n = Integer.parseInt(br.readLine());
	        int[] originArr = new int[n];
	        int[] sortedArr = new int[n];

	        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	        for (int i = 0; i < n; i++) {
	            originArr[i] = sortedArr[i] = Integer.parseInt(st.nextToken());
	        }
	        Arrays.sort(sortedArr);
	        int index = 0;
	        for (int x : sortedArr) {
	            if (!map.containsKey(x)) {
	                map.put(x, index);
	                index++;
	            }
	        }
	        for (int x : originArr) {
	            sb.append(map.get(x) + " ");
	        }
	        System.out.println(sb);
	   }
}
