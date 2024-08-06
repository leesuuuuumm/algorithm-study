import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String input = br.readLine();
            int k = Integer.parseInt(br.readLine());
            int[] result = solve(input.toCharArray(), k);
            if (result[0] == -1 || result[1] == -1)
                System.out.println(-1);
            else System.out.println(result[0] + " " + result[1]);
        }
    }

    public static int[] solve(char[] input, int k) {
        ArrayList<Integer>[] count = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            count[i] = new ArrayList<>();
        }
        for (int i = 0; i < input.length; i++) {
            int idx = input[i] - 'a';
            count[idx].add(i);
        }

        int maxSize = 0;
        for (int i = 0; i < 26; i++) {
            Collections.sort(count[i]);
            maxSize = Math.max(maxSize, count[i].size());
        }

        if (maxSize < k) {
            return new int[]{-1, -1};
        }

        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < 26; i++) {
            if (count[i].size() >= k) {
                for(int j=0;j<=count[i].size()-k;j++){
                    minLen=Math.min(count[i].get(j+k-1)-count[i].get(j)+1, minLen);
                    maxLen=Math.max(count[i].get(j+k-1)-count[i].get(j)+1, maxLen);
                }
            }
        }
        return new int[]{minLen,maxLen};
    }
}
