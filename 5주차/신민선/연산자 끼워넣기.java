import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int[] operator = new int[4]; // 0 + , 1 -, 2 *, 3 /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        n = Integer.parseInt(s);
        arr = new int[n];
        int[] result = new int[n];
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        s = br.readLine();
        st = new StringTokenizer(s, " ");

        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }


    static void dfs(int d, int value) {
        if (d == n) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }
        for (int i = 0; i < 4; i++) {

            if (operator[i] > 0) {
                operator[i]--;

                switch (i) {
                    case 0:
                         dfs(d + 1, value + arr[d]); 
                        break;
                    case 1: 
                        dfs(d + 1, value - arr[d]);
                        break;
                    case 2:
                         dfs(d + 1, value * arr[d]);
                        break;
                    case 3:
                         dfs(d + 1, value / arr[d]);
                        break;
                }
                operator[i]++;
            }
        }
    }
}
