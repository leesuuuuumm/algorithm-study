import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static boolean[] breakdown;
    static int min = Integer.MAX_VALUE;
    static int minChannel = 100;
    static String channel;
    static int[] val;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        breakdown = new boolean[10];
        channel = String.valueOf(N);
        val = new int[channel.length()];

        StringTokenizer st = null;
        if (M != 0) st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int bc = Integer.parseInt(st.nextToken());
            breakdown[bc] = true;
        }

        change();

        min = Math.min(min, getChangeCount(100));

        System.out.println(min);
    }

    private static void change() {
        for (int i = 0; i < 1_000_000; i++) {
            String str = String.valueOf(i);

            boolean isBreak = false;

            for (int j = 0; j < str.length(); j++) {
                if (breakdown[str.charAt(j) - '0']) {
                    isBreak = true;
                    break;
                }
            }

            if (!isBreak) {
                min = Math.min(min, getChangeCount(i) + str.length());
            }
        }
    }

    private static int getChangeCount(int cur) {
        return Math.abs(cur - N);
    }
}