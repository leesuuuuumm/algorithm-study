package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1780_종이의개수 {
    static int[][] paper;
    static int[] result = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(N, 0, 0);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    private static void dfs(int N, int r, int c) {
        for (int i = r; i < r + N; i++) {
            for (int j = c; j < c + N; j++) {
                if (paper[r][c] != paper[i][j]) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            dfs(N / 3, r + N / 3 * k, c + N / 3 * l);
                        }
                    }
                    return;
                }
            }
        }
        result[paper[r][c] + 1]++;
    }
}
