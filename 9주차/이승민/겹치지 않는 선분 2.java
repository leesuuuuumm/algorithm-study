import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x1 = new int[100];
        int[] x2 = new int[100];
        for (int i = 0; i < n; i++) {
            x1[i] = sc.nextInt();
            x2[i] = sc.nextInt();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if ((x1[i] >= x1[j] && x2[i] <= x2[j]) || (x1[j] >= x1[i] && x2[j] <= x2[i])) {
                    flag = true;
                    break;
                }
            }

            if (!flag) ans++;
        }

        System.out.print(ans);
    }
}
