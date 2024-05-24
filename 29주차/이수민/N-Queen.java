import java.io.*;
public class Main {

	static int N;
	static int[] chess;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chess = new int[N];
		NQueen(0);
		System.out.println(cnt);
	}

	private static void NQueen(int depth) {
		if (N == depth) {
			cnt++;
			return;
		}

		for (int i = 0; i < chess.length; i++) {
			chess[depth] = i; 
			if (check(depth)) { 
				NQueen(depth + 1);

			}
		}
	}

	private static boolean check(int depth) {
		for (int i = 0; i < depth; i++) { 
			if (chess[depth] == chess[i] || Math.abs(depth - i) == Math.abs(chess[depth] - chess[i]))
				return false;
		}
		return true;
	}

}
