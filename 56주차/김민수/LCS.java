import java.util.*;
import java.io.*;
public class LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char[] str1=br.readLine().toCharArray();
		char[] str2=br.readLine().toCharArray();

		int str1_size=str1.length;
		int str2_size=str2.length;

		int[][] lcs=new int[str1_size+1][str2_size+1];

		for(int i=1;i<=str1_size;i++){
			for(int j=1;j<=str2_size;j++){
				if(str1[i-1]==str2[j-1]){
					lcs[i][j]=lcs[i-1][j-1]+1;
				}else{
					lcs[i][j]=Math.max(lcs[i-1][j],lcs[i][j-1]);
				}
			}
		}
		System.out.println(lcs[str1_size][str2_size]);
	}

}
