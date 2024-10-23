import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class N으로만들기 {
	static Set<String> set;
	static char[] ch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		ch=input.toCharArray();
		set=new HashSet<>();
		for(int i=0;i<ch.length;i++){
			dfs(i,i,""+ch[i],""+ch[i]);
		}
		System.out.println(set.size());
	}
	public static void dfs(int left, int right, String str, String path){
		if(left==0&&right==ch.length-1){
			set.add(path);
			return;
		}
		if(left-1>=0){
			dfs(left-1,right,ch[left-1]+str,path+" "+ch[left-1]+str);
		}
		if(right+1<=ch.length-1){
			dfs(left, right+1, str+ch[right+1], path+" "+str+ch[right+1]);
		}
	}
}
