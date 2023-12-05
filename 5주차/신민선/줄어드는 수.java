/*
백준 1174
날짜 2023.12.05
*/
import java.util.*;

public class Beakjoon_1174 {
	static int[] num= {9,8,7,6,5,4,3,2,1,0};
	static ArrayList<Long> arr=new ArrayList<>();
	static int n;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		dfs(0,0);
		Collections.sort(arr);

		if(n>1023)
			System.out.println(-1);
		else
			System.out.println(arr.get(n-1));
	}
	
	static void dfs(long sum, int index) {
		if(!arr.contains(sum))
			arr.add(sum);
		
		if(index>=10)
			return;
		
		dfs(sum*10+num[index],index+1);
		dfs(sum,index+1);
	}
}
