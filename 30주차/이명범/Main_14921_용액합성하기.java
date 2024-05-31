import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());;
        }
        int min = Integer.MAX_VALUE;
        int res = 0;

        int l = 0;
        int r = N - 1;

        while (l < r) {
            int num = nums[l] + nums[r];
            if (min > Math.abs(num)) {
                min = Math.abs(num);
                res = num;
            }
            if (num < 0) l++;
            else if (num > 0) r--;
            else break;
        }
        System.out.println(res);
    }
}