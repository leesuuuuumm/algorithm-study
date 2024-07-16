// [BOJ] 전쟁-전투

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1,1,0,0}; 
    static int[] dc = {0,0,-1,1}; 

    static int re_w = 0;
    static int re_b = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        int m = Integer.parseInt(st.nextToken()); 

        String[][] war = new String[m][n];

        for (int i = 0; i < m; i++) {
            String string = br.readLine();
            for (int j = 0; j < n; j++) {
                war[i][j] = String.valueOf(string.charAt(j));
            }
        }

        boolean[][] visited = new boolean[m][n];

        int w = 0; 
        int b = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c] && war[r][c].equals("W")) {
                    dfs("W",r,c,war,visited);
                    w = w + (int) Math.pow(re_w, 2);
                    re_w = 0;
                } else if (!visited[r][c] && war[r][c].equals("B")) {
                    dfs("B",r,c,war,visited);
                    b = b + (int) Math.pow(re_b, 2);
                    re_b = 0;
                }
            }
        }

        System.out.println(w + " " + b);
    }

    public static void dfs(String start,int r,int c , String[][] war, boolean[][] visited) {
        visited[r][c] = true;
        if (start.equals("W")) re_w++;
        else re_b++;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < war.length && nc >= 0 && nc < war[0].length && !visited[nr][nc] && war[nr][nc].equals(start)) {
                dfs(start, nr, nc, war, visited);
            }
        }
    }
}