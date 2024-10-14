import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("");
		LinkedList<String> list = new LinkedList<>();
		
		for(int i=0;i<s.length;i++) {
			list.add(s[i]);
		}
		ListIterator<String> iter = list.listIterator(list.size());

		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			String M = st.nextToken();
			String a = "";
			
			if(M.equals("P")) {
				a = st.nextToken();
				iter.add(a);
							
			}else if(M.equals("L")) {
				if(iter.hasPrevious()) {
					iter.previous();
				}
			}else if(M.equals("B")) {
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
			}else {
				if(iter.hasNext()) {
					iter.next();
				}
			}

		}
		StringBuilder sb = new StringBuilder();
		for(String i: list) {
			sb.append(i);
		}
		System.out.println(sb);
		

	}

}
