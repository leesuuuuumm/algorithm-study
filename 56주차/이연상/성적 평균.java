// [SOFT] 성적 평균

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st; 

    static int[] students;
    static int N, K;
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        students = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            System.out.println(String.format("%.2f", getMin(start, end)));
        }
    }

    static double getMin(int start, int end) {
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += students[i];
        }
        return (double) sum / (end - start +1);
    }
}