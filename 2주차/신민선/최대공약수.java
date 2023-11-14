/*
알고리즘 코딩테스트 자바 7장
Beakjoon_1850
날짜 2023.11.14
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1850 {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		long A=Long.parseLong(st.nextToken());
		long B=Long.parseLong(st.nextToken());
		long a=Math.max(A,B);
		long b=Math.min(A,B);
		
		int result=(int) GCD(a,b);
		for(int i=0;i<result;i++) {
			bw.write(1);
		}
		bw.flush();
		
	}
	static long GCD(long a,long b) {
		if(b==0)
			return a;
		return GCD(b,a%b);
	}

}
