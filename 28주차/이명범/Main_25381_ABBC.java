import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        List<Character> chars = new ArrayList<>();
        Queue<Integer> aq = new LinkedList<>();
        Queue<Integer> bq = new LinkedList<>();
        boolean[] isRemoved = new boolean[str.length()];

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            chars.add(c);

            if (c == 'A') aq.add(chars.size() - 1);
            else if (c == 'B') bq.add(chars.size() - 1);
        }

        int count = 0;
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i) == 'B' && !isRemoved[i]) {
                if (aq.isEmpty()) break;

                int idx = getIndex(aq, isRemoved, i);
                if (idx == -1) break;

                isRemoved[i] = true;
                isRemoved[idx] = true;
                count++;
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i) == 'C' && !isRemoved[i]) {
                if (isRemoved[i]) continue;

                int idx = getIndex(bq, isRemoved, i);
                if (idx == -1) break;

                isRemoved[i] = true;
                isRemoved[idx] = true;

                count++;
            }
        }

        System.out.println(count);
    }

    private static int getIndex(Queue<Integer> q, boolean[] isRemoved, int cur) {
        while (!q.isEmpty()) {
            int idx = q.poll();
            if (isRemoved[idx]) continue;
            if (cur > idx) continue;
            return idx;
        }
        return -1;
    }
}