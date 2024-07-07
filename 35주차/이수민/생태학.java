import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> map = new TreeMap<>();
		String s;
		int N = 0;

		while ((s = br.readLine()) != null) {
			map.put(s, map.getOrDefault(s, 0) + 1);
			N++;
		}

		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet()) {
			double d = (double) map.get(key) / N * (double) 100;
			sb.append(key + " " + String.format("%.4f", d) + "\n");
		}
		System.out.println(sb.toString());
	}
}
