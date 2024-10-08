// [CDT] 조삼모사

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    
    static int n;
    static int[][] map;
    static boolean[] visit;
    static int answer = 987654321;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n];
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int idx, int count) {
        if(count == n / 2) {
            calDiff();
            return;
        }

        for(int i = idx; i < n; i++) {
            if(visit[i]) {
                continue;
            }
            visit[i] = true;
            dfs(i + 1, count + 1);
            visit[i] = false;
        }
    }

    static void calDiff() {
        int morning = 0, evening = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visit[i] && visit[j]) {
                    morning += map[i][j];
                }
                if(!visit[i] && !visit[j]) {
                    evening += map[i][j];
                }
            }
        }
        int diff = Math.abs(morning - evening);
        answer = Math.min(answer, diff);

    }
}