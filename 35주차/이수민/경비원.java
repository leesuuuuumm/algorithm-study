import java.io.*;
import java.util.*;

public class Main {

	static int R, C, L;
	static int min, storeCnt;
	static int map[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		L = 2 * (R + C);
		storeCnt = Integer.parseInt(br.readLine());
		map = new int[storeCnt][2];

		for (int i = 0; i < storeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());

		dol(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), L);
		System.out.println(min);
	}

	private static void dol(int sl, int sr, int l) {
		for (int i = 0; i < L; i++) {
			cnt++;

			switch (sl) {
			case 1:
				sr++;
				for (int j = 0; j < map.length; j++) {
					if (map[j][0] == sl && map[j][1] == sr) {
						min += Math.min(l - cnt, cnt);
					}
				}
				if (sr == C) {
					sr = 0;
					sl = 4;
				}

				break;
			case 2:
				sr--;
				for (int j = 0; j < map.length; j++) {
					if (map[j][0] == sl && map[j][1] == sr) {
						min += Math.min(cnt, l - cnt);
					}
				}
				if (sr == 0) {
					sr = R;
					sl = 3;
				}
				break;
			case 3:

				sr--;
				for (int j = 0; j < map.length; j++) {
					if (map[j][0] == sl && map[j][1] == sr) {
						min += Math.min(l - cnt, cnt);
					}
				}
				if (sr == 0) {
					sr = 0;
					sl = 1;
				}
				break;
			case 4:
				sr++;
				for (int j = 0; j < map.length; j++) {
					if (map[j][0] == sl && map[j][1] == sr) {
						min += Math.min(l - cnt, cnt);
					}
				}
				if (sr == R) {
					sr = C;
					sl = 2;
				}
				break;
			}
		}

	}
}
