import java.io.*;
import java.util.*;

public class Main {
    /*

    */
    static int[][] video;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        video = new int[n][n];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                video[i][j] = tmp.charAt(j) - '0';
            }
        }

        func(n, 0, 0);
        System.out.println(sb);
    }

    public static boolean check(int n, int r, int c) { // 모두 같은 숫자로 되어 있는지 확인
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (video[r][c] != video[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void func(int n, int r, int c) {
        if (check(n, r, c)) { // 모두 같은 숫자거나 더 이상 나눌 수 없으면 재귀 종료
            switch (video[r][c]) {
                case 0:
                    sb.append(0);
                    break;
                case 1:
                    sb.append(1);
                    break;
            }
            return;
        }

        int half = n / 2;
        sb.append("(");
        func(half, r, c);
        func(half, r, c + half);
        func(half, r + half, c);
        func(half, r + half, c + half);
        sb.append(")");
    }
}