import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        int idx = 0;
        long count = 0;

        while (idx < N) {
            int cur = arr[idx];

            if (stack.isEmpty()) {
                stack.push(cur);
                map.put(cur, 0);
                idx++;
                continue;
            }

            if (stack.peek() >= cur) {
                stack.push(cur);

                int backCnt = map.getOrDefault(cur, 0) + 1;

                map.put(cur, backCnt);
                count += backCnt;
                idx++;

                continue;
            }

            while (!stack.isEmpty() && stack.peek() < cur) {
                Integer num = stack.pop();
                map.remove(num);
                count++;
            }
        }

        System.out.println(count);
    }
    
    private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}