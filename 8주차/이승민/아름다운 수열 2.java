import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        Arrays.sort(b);

        int ans = 0;
        for (int i = 0; i <= n - m; i++) {
            int[] tmp = new int[m];
            for (int j = i; j < i + m; j++) {
                tmp[j - i] = a[j];
            }
            Arrays.sort(tmp);
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (tmp[j] != b[j]) {
                    flag = false;
                }
            }
            if (flag) ans++;
        }

        System.out.print(ans);
    }
}
