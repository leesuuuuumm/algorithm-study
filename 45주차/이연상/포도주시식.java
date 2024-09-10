// [BOJ] 포도주 시식

import java.util.*;
import java.io.*;

public class testtest {
    static int N, wine[];
    static Integer dp[];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        process();
    }


    private static void process() {

        dp[0] = 0;
        dp[1] = wine[1];

        if(N>=2){
            dp[2] = wine[1] + wine[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(Math.max(dp[i-1], dp[i-2]+ wine[i]), dp[i-3] + wine[i-1] + wine[i]);
        }

        System.out.println(dp[N]);

    }


    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        wine = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
    }
}