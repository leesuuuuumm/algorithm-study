import java.io.*;
import java.util.*;

public class Main {

	static final String[] ppap = {"P","P", "A","P"};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("");
		String ans = "NP";

		Stack<String> stack = new Stack<>();

		e:for (int i = 0; i < str.length; i++) {
			stack.push(str[i]);
			if(stack.size()<4) continue;
			String[] tmp = new String[4];
			for(int j=3;j>=0;j--) {
				tmp[j] = stack.pop();
			}
			for(int j=0;j<4;j++) {
				if(!ppap[j].equals(tmp[j])) {
					for(int k=0;k<4;k++) {
						stack.add(tmp[k]);
					}
					continue e;
				}
			}
			stack.push("P");
	
		}
		
		if(stack.size() == 1 && stack.pop().equals("P")) {
			ans = "PPAP";
		}
		else if(stack.size() == 4){
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			if(sb.toString().equals("PAPP")) {
				ans = "PPAP";
			}
		}
		System.out.println(ans);


	}
}
