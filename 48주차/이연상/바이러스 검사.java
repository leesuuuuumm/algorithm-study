// [CDT] 바이러스 검사

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] customers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        customers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int leader = Integer.parseInt(st.nextToken());
        int member = Integer.parseInt(st.nextToken());

        long answer = 0;
        for(int i = 0; i < n; i++) {
            answer += 1;
            customers[i] -= leader;

            if(customers[i] > 0) {
                answer += customers[i] / member;
                if(customers[i] % member != 0) answer += 1;
            }
        }

        System.out.println(answer);
    }
}