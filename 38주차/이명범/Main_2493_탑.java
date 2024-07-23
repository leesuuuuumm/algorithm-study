import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] top = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            top[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int[] result = new int[N + 1];

        for (int i = N; i > 0; i--) {
            if (stack.isEmpty()) {
                stack.push(new int[]{top[i], i});
                continue;
            }

            while (!stack.isEmpty() && stack.peek()[0] <= top[i]) {
                int[] pop = stack.pop();
                result[pop[1]] = i;
            }

            stack.push(new int[]{top[i], i});
        }

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}