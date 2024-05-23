import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();

        int[] arr = new int[6];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (map.containsKey(dir)) {
                map.remove(dir);
            } else {
                map.put(dir, dist);
            }

            arr[i] = dist;
        }

        Set<Integer> key = map.keySet();

        int[] big = new int[2];
        int cnt = 0;

        for (Integer k : key) {
            big[cnt++] = map.get(k);
        }

        int bigBox = big[0] * big[1];
        int smallBox = 1;

        if (arr[0] + arr[4] == big[0] || arr[0] + arr[4] == big[1]) smallBox *= arr[5];
        if (arr[1] + arr[5] == big[0] || arr[1] + arr[5] == big[1]) smallBox *= arr[0];
        for (int i = 1; i < 5; i++) {
            if (arr[i - 1] + arr[i + 1] == big[0] || arr[i - 1] + arr[i + 1] == big[1]) smallBox *= arr[i];
        }

        System.out.println(K * (bigBox - smallBox));
    }
}