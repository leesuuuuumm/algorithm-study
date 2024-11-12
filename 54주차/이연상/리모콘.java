// [BOJ] 1107_리모컨

import java.util.*;

public class RemoteControl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int target = scanner.nextInt();
        int ans = Math.abs(100 - target);

        int M = scanner.nextInt();
        Set<String> broken = new HashSet<>();

        if (M > 0) {
            for (int i = 0; i < M; i++) {
                broken.add(scanner.next());
            }
        }

        for (int num = 0; num <= 1000000; num++) {
            String numStr = String.valueOf(num);
            boolean isBroken = false;

            for (char N : numStr.toCharArray()) {
                if (broken.contains(String.valueOf(N))) {
                    isBroken = true;
                    break;
                }
            }

            if (!isBroken) {
                ans = Math.min(ans, numStr.length() + Math.abs(num - target));
            }
        }

        System.out.println(ans);
    }
}