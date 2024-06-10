// [BOJ] 쿼드 트리

import java.io.*;
import java.util.*;

public class Main {
    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken()); 
        map = new int[N][N]; 

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0'; 
            }
        }

        rec(0, 0, N); 

        System.out.println(sb); 
    }

    static void rec(int n, int m, int length) {
        int check = map[n][m];

        Outer:
        for (int i = n; i < n + length; i++) { 
            Inner:
            for (int j = m; j < m + length; j++) { 
                if (map[i][j] != check) { 
                    check = -1; 
                    break Outer; 
                }
            }
        }

        if (check == -1) { 
            sb.append("(");
            length = length / 2;
            rec(n, m, length);
            rec(n, m + length, length);
            rec(n + length, m, length);
            rec(n + length, m + length, length);
            sb.append(")");
        } else { 
            sb.append(check);
        }
    }
}