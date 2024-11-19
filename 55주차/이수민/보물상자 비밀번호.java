import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int T;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            ArrayList<Character> list = new ArrayList<>();
            HashSet<Long> set = new HashSet<>();
 
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i));
            }
            int num = N / 4;
            int len = list.size() - 1;
 
            for (int i = 0; i < N / 4; i++) {
                for (int j = 0; j < list.size(); j += N / 4) {
                    long l = 0;
                    for (int k = j; k < j + (N / 4); k++) {
                        int a = list.get(k) - '0';
                        if (list.get(k) == 'A' || list.get(k) == 'B' || list.get(k) == 'C' || list.get(k) == 'D'
                                || list.get(k) == 'E' || list.get(k) == 'F') {
                            a = list.get(k) - 55;
                        }
                        l += (long) Math.pow(16, num - 1 - (k % num)) * a;
 
                    }
                    set.add(l);
 
                }
                if (i < N - 4 - 1) {
                    list.add(0, list.remove(len));
                }
            }
            ArrayList<Long> answer = new ArrayList<>();
 
            for (Long i : set) {
                answer.add(i);
            }
            Collections.sort(answer);
 
            System.out.println("#" + t +" "+ answer.get(answer.size() - K));
 
        }
    }
}
