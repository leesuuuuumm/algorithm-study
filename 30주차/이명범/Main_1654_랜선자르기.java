import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, N, nums[];
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        nums = new int[K];

        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, nums[i]);
        }

        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 1;
        long r = max;

        while (l <= r) {
            long m = (l + r) / 2;

            if (check(m)) l = m + 1;
            else r = m - 1;
        }

        return r;
    }

    private static boolean check(long m) {
        int result = 0;

        for (int num : nums) {
            result += num / m;
        }

        return result >= N;
    }
}