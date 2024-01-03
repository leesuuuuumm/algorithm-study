import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());

		int choco = 0;
		int i = 1;
		while (true) {
			if (K <= Math.pow(2, i)) {
				choco = (int) (Math.pow(2, i));
				break;
			}
			i++;
		}
		int d = choco;
		int div = 0;
		while (K > 0) {
			if (K >= d) {
				K -= d;
			} else {
				d /= 2;
				div++;

			}

		}
		System.out.println(choco + " " + div);

	}
}
