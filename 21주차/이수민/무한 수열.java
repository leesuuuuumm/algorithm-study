import java.io.*;
import java.util.*;

public class Main {

	static HashMap<Long, Long> map;
	static long P, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());

		map = new HashMap<>();
		

		System.out.println(topdown(N));

	}

	private static long topdown(long n) {

		if (n == 0)
			return 1;

		if (n == 1)
			return 2;

		if (map.containsKey(n))
			return map.get(n);
		long result = topdown(n / P) + topdown(n / Q);
		
		map.put(n,result);

		return result;

	}
}
