import java.util.Scanner;

public class Main {
    /*
        4번 조건을 만족하려면 3번 조건도 만족해야 한다는 점을 인지하고 시간복잡도를 줄이는 방법을 찾는 게 관건
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            String W = sc.next();
            int K = sc.nextInt();

            int[] cnt = new int[26];
            for (int i = 0; i < W.length(); i++) {
                cnt[W.charAt(i) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int l = 0; l < W.length(); l++) {
                if (cnt[W.charAt(l) - 'a'] < K) {
                    continue;
                }

                char ch = W.charAt(l);
                int count = 0;
                for (int r = l; r < W.length(); r++) {
                    if (ch != W.charAt(r)) {
                        continue;
                    } else {
                        count++;
                    }

                    if (count == K) {
                        min = Math.min(min, r - l + 1);
                        max = Math.max(max, r - l + 1);
                        break;
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}