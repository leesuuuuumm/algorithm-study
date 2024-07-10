import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static String[][] map;
	static TreeSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];

		set = new TreeSet<>();
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().split("");
		}
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c].equals("#")) {
					if (sb.length() > 1) {
						set.add(sb.toString());
					}
					sb.setLength(0);
				} else {
					sb.append(map[r][c]);
				}
			}
			if (sb.length() > 1) {
				set.add(sb.toString());
			}
			sb.setLength(0);
		}

		sb.setLength(0);

		for (int c = 0; c < C; c++) {
			for (int r = 0; r < R; r++) {
				if (map[r][c].equals("#")) {
					if (sb.length() > 1) {
						set.add(sb.toString());
					}
					sb.setLength(0);
				} else {
					sb.append(map[r][c]);
				}
			}
			if (sb.length() > 1) {
				set.add(sb.toString());
			}
			sb.setLength(0);
		}

		System.out.println(set.first());

	}
}
