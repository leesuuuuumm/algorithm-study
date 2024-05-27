import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = N - 1; i >= 0; i--) {
            if(i == N - 1) {
                result[i] = -1;
                continue;
            }

            if (A[i] == A[i + 1]) {
                result[i] = result[i + 1];
            } else {
                result[i] = i + 2;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}
