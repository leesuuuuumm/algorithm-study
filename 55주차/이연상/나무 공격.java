// [SOFT] 나무 공격

import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int[][] map;
    static int[] attack;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        int res = 0;
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
                if(map[i][j] == 1) res++;
            }
        }
        
        for(int q = 0; q < 2; q++) {
            attack = new int[n];
            st = new StringTokenizer(br.readLine());
            int first = Integer.valueOf(st.nextToken()) - 1;
            int last = Integer.valueOf(st.nextToken()) - 1;
            for(int i = first; i <= last; i++) {
                attack[i] = 1;
            }

            for(int y = 0; y < m; y++) {
                for(int x = 0; x < n; x++) {
                    if(attack[x] == 1 && map[x][y] == 1) {
                        attack[x] = 0;
                        map[x][y] = 0;
                        res--;
                    }
                }
            }
        }

        System.out.println(res);
    }
}
