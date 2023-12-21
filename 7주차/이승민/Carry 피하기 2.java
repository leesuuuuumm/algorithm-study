import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int ans = Integer.MIN_VALUE;
        // 서로 다른 숫자 3개를 고른다.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // System.out.println(i + " " + j + " " + k + " " + isCarry(arr[i], arr[j]) + " " + isCarry(arr[i] + arr[j], arr[k]));
                    if (!isCarry(arr[i], arr[j])) {
                        if (!isCarry(arr[i] + arr[j], arr[k])) {
                            ans = Math.max(ans, arr[i] + arr[j] + arr[k]);
                        }
                    }
                }
            }
        }

        if (ans == Integer.MIN_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(ans);
        }
    }

    // 숫자끼리 더할 때 각 수의 자리수를 구해서 더해보고 10이상이면 carry 발생으로 간주
    public static boolean isCarry(int a, int b) {
        List<Integer> list = new ArrayList<>();
        while (a > 0) {
            list.add(a % 10);
            a /= 10;
        }


        List<Integer> list2 = new ArrayList<>();
        while (b > 0) {
            list2.add(b % 10);
            b /= 10;
        }

        for (int i = 0; i < Math.min(list.size(), list2.size()); i++) {
            if (list.get(i) + list2.get(i) >= 10) {
                return true;
            }
        }
        return false;
    }
}
