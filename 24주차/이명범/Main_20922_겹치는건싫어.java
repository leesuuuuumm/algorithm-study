import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;

        int[] db = new int[100001];
        Queue<Integer> queue = new LinkedList<>();

        for (int num : arr) {
            if (db[num] == K) {
                max = Math.max(max, queue.size());

                while (true) {
                    Integer cur = queue.poll();
                    db[cur]--;
                    if (cur == num) break;
                }
            }

            queue.offer(num);
            db[num]++;
        }

        max = Math.max(max, queue.size());

        System.out.println(max);
    }
}