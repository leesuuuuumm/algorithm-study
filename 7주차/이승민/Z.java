import java.io.*;
import java.util.*;

public class Main {
    /*
    0.5, 512
    1 ≤ N ≤ 15
    0 ≤ r, c < 2^N

    n이 k일 때의 결과를 이용해서 n이 k+1일 때의 결과를 알아낼 수 있다.
    f(1) = 1
     */

    public static void main(String[] args) throws Exception {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(func(n, r, c));
    }

    public static int func(int n, int r, int c) {
        if (n == 0) {
            return 0;
        }

        int half = (int) Math.pow(2, n - 1);

        // 1번 사각형
        if (c < half && r < half) {
            return func(n - 1, r, c);
        }
        // 2번 사각형
        if (c >= half && r < half) {
            return half * half + func(n - 1, r, c - half);
        }
        // 3번 사각형
        if (c < half && r >= half) {
            return 2 * half * half + func(n - 1, r - half , c);
        }
        // 4번 사각형
        return 3 * half * half + func(n - 1, r - half, c - half);
    }
}
