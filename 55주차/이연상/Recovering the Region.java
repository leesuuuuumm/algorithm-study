// [SOFT] Recovering the Region

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());

        for(int i = 1; i < n + 1; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
    }
}