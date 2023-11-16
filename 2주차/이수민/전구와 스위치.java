import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] beLightfirstOff = new int[N];
		int[] beLightfirstON = new int[N];
		int[] afLight = new int[N];
		String[] st = br.readLine().split("");

		for (int i = 0; i < N; i++) {
			beLightfirstOff[i] = Integer.parseInt(st[i]);

			beLightfirstON[i] = beLightfirstOff[i];
		}

		st = br.readLine().split("");
		for (int j = 0; j < N; j++) {

			afLight[j] = Integer.parseInt(st[j]);
		}

		beLightfirstON[0] = 1 - beLightfirstON[0];
		beLightfirstON[1] = 1 - beLightfirstON[1];

		int on = 1;
		int off = 0;

		for (int i = 1; i < N; i++) {
			if (afLight[i - 1] != beLightfirstON[i - 1]) {
				beLightfirstON[i] = 1 - beLightfirstON[i];
				beLightfirstON[i - 1] = 1 - beLightfirstON[i - 1];
				if (i < N - 1) {
					beLightfirstON[i + 1] = 1 - beLightfirstON[i + 1];
				}
				on++;
			}

			if (afLight[i - 1] != beLightfirstOff[i - 1]) {
				beLightfirstOff[i] = 1 - beLightfirstOff[i];
				beLightfirstOff[i - 1] = 1 - beLightfirstOff[i - 1];
				if (i < N - 1) {
					beLightfirstOff[i + 1] = 1 - beLightfirstOff[i + 1];
				}
				off++;
			}

		}
		int min = Integer.MAX_VALUE;
		if (beLightfirstON[N - 1] != afLight[N - 1]) {
			on = Integer.MAX_VALUE;
		}
		if (beLightfirstOff[N - 1] != afLight[N - 1]) {
			off = Integer.MAX_VALUE;
		}

		if (on == Integer.MAX_VALUE && off == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(on,off));
		}
	}
}
