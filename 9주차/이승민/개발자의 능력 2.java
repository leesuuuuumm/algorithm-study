import java.util.*;

public class Main {
    static int[] dev;
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        dev = new int[6];
        for (int i = 0; i < 6; i++) {
            dev[i] = sc.nextInt();
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    for (int h = k + 1; h < 6; h++) {
                        if (k == i || k == j || h == i || h == j) continue;
                        ans = Math.min(ans, calcDiff(i, j, k, h));
                    }
                }
            }
        }

        System.out.print(ans);
    }

    public static int calcDiff(int i, int j, int k, int h) {
        int sum1 = dev[i] + dev[j];
        int sum2 = dev[k] + dev[h];
        int sum3 = 0;
        for (int a = 0; a < 6; a++) {
            sum3 += dev[a];
        }
        sum3 -= sum2;
        sum3 -= sum1;

        int max = Math.max(Math.max(sum1, sum2), sum3);
        int min = Math.min(Math.min(sum1, sum2), sum3);

        return Math.abs(max - min);
    }
}
