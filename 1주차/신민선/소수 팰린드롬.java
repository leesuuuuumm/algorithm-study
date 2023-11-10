/*
알고리즘 코딩테스트 자바 7장
백준 1747
날짜 2023.11.10
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1747 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int size=1003001;
		int[] check=new int[size+1];
		for(int i=0;i<=size;i++) {
			check[i]=i;
		}
		check[1]=0;
		for(int i=2;i<=Math.sqrt(size);i++) {
			if(check[i]==0)
				continue;
			for(int j=i+i;j<=size;j=j+i) {
				check[j]=0;
			}
		}
		int index=N;
		while(true) {
			if(check[index]!=0) {
				if(palindrome(index)) {
					System.out.println(index);
					break;
				}
			}
			index++;
		}
		
	}
	static boolean palindrome(int target) {
		char[] temp = String.valueOf(target).toCharArray();//target을 string으로 바꾸고 그걸 charArray로 바꿔서 char 배열에 저장.
		int start=0;
		int finish=temp.length-1;
		while(start<=finish) {
			if(temp[start]!=temp[finish]) {
				return false;
			}
			start++;
			finish--;
		}
		return true;
	}
}
