import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int[] way = new int[201];
 
            int N = Integer.parseInt(br.readLine());
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = (Integer.parseInt(st.nextToken()) + 1) / 2;
                int end = (Integer.parseInt(st.nextToken()) + 1) / 2;
 
                if (start < end) {
 
                    for (int j = start; j <= end; j++) {
                        way[j]++;
                    }
                } else {
                    for (int j = end; j <= start; j++) {
                        way[j]++;
 
                    }
                }
 
            }
            Arrays.sort(way);
            sb.append("#").append(t).append(" ").append(way[200]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
