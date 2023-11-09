/*
알고리즘 코딩테스트 자바 6장
백준 1931
날짜 2023.11.09
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] s=br.readLine().split("-");
		int result=0;
		for(int i=0;i<s.length;i++) {
			int tmp=sum(s[i]);
			if(i==0) {
				result+=tmp;
			}
			else {
				result-=tmp;
			}
		}
		System.out.println(result);
	}
	static int sum(String s) {
		String[] st=s.split("[+]");
		int result=0;
		for(int i=0;i<st.length;i++) {
			result+=Integer.parseInt(st[i]);
		}
		return result;
	}

}
