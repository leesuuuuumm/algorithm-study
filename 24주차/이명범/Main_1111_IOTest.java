import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println("A");
        } else if (N == 2) {
            if (nums[1] - nums[0] == 0) System.out.println(nums[0]);
            else System.out.println("A");
        } else {
            int a = 0;
            if (nums[0] != nums[1]) {
                a = (nums[2] - nums[1]) / (nums[1] - nums[0]);
            }
            int b = nums[1] - a * nums[0];

            if (isPossible(a, b)) System.out.println(a * nums[N - 1] + b);
            else System.out.println("B");
        }
    }

    private static boolean isPossible(int a, int b) {
        for (int i = 1; i < N; i++) {
            if (nums[i] != a * nums[i - 1] + b) return false;
        }
        return true;
    }
}