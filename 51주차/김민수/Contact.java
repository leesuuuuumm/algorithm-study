import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] input = new String[N];
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
			if (check(input[i])) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

	public static boolean check(String str) {
		char[] ch = str.toCharArray();
		int idx = 0;
		boolean flag = false;
		while (idx < ch.length) {
			if (ch[idx] == '1') {
				int nextIdx = getLastIdx(idx, ch);
				if (nextIdx == -1)
					return false;
				else {
					flag = true;
					idx = nextIdx;
				}
			} else {
				if (idx + 1 < ch.length) {
					if (!(ch[idx] == '0' && ch[idx + 1] == '1')) {
						if (flag && idx - 2 >= 0 && idx + 1 < ch.length && ch[idx - 1] == '1' && ch[idx - 2] == '1'
							&& ch[idx] == '0'
							&& ch[idx + 1] == '0') {
							int nextIdx = getLastIdx(idx - 1, ch);
							if (nextIdx == -1)
								return false;
							else {
								idx = nextIdx;
							}
						} else
							return false;
					} else {
						flag = false;
						idx += 2;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static int getLastIdx(int firstIdx, char[] str) {
		if (firstIdx < str.length - 1) {
			int idx = firstIdx + 1;
			int zeroCount = 0;
			while (idx < str.length && str[idx] == '0') {
				zeroCount += 1;
				idx += 1;
			}
			if (idx == str.length )
				return -1;
			if (zeroCount >= 2) {
				while (idx < str.length && str[idx] == '1') {
					idx += 1;
				}
				return idx;
			} else {
				return -1;
			}

		} else {
			return -1;
		}
	}
}
