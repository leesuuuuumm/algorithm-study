import java.util.*;
import java.io.*;

public class 구간나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] con = new long[n+1][m+1];
        long[][] notcon = new long[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(con[i], Long.MIN_VALUE);
            Arrays.fill(notcon[i], Long.MIN_VALUE);
            con[i][0] = 0;
            notcon[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            for (int j = 1; j <= Math.min(m, (i+1)/2); j++) {
                notcon[i][j] = Math.max(con[i-1][j], notcon[i-1][j]);
                con[i][j] = Math.max(con[i-1][j], notcon[i-1][j-1]) + num;
            }
        }

        System.out.println(Math.max(con[n][m], notcon[n][m]));
    }
}