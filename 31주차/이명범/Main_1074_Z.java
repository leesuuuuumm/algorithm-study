import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int position;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		find(N, r, c);
		
		System.out.println(position);
	}

	static void find(int N, int r, int c) {
		// 큰 블럭의 가로 세로 길이
		int size = 1 << N;
		// 큰 블럭을 4개로 나눈 작은 블럭 넓이
		int block = size * size / 4;

		if (N == 0) {
			return;
		}

		// 1사분면
		if (r < size / 2 && c < size / 2) {
			find(N - 1, r, c);
			
		// 2사분면
		} else if (r < size / 2 && c >= size / 2) {
			find(N - 1, r, c - size / 2);
			position += block * 1;
			
		// 3사분면
		} else if (r >= size / 2 && c < size / 2) {
			find(N - 1, r - size / 2, c);
			position += block * 2;
			
		// 4사분면
		} else if (r >= size / 2 && c >= size / 2) {
			find(N - 1, r - size / 2, c - size / 2);
			position += block * 3;
		}
	}
}