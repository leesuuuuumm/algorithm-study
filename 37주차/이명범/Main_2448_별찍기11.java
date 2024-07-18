import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<StringBuilder> sbs = new ArrayList<>();
    static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int temp = sc.nextInt();

        K = (int) (Math.log(temp / 3) / Math.log(2));

        makeStar();

        recur(0);
    }

    private static void makeStar() {
        sbs.add(new StringBuilder().append("***** "));
        sbs.add(new StringBuilder().append(" * *  "));
        sbs.add(new StringBuilder().append("  *   "));
    }

    private static void recur(int k) {
        if (k == K) {
            for (int i = sbs.size() - 1; i >= 0; i--) {
                System.out.println(sbs.get(i));
            }
            return;
        }

        int length = sbs.size();
        int blankCount = 3 * (int) Math.pow(2, k);

        for (int i = 0; i < length; i++) {
            StringBuilder originBuilder = sbs.get(i);
            StringBuilder newBuilder = new StringBuilder();

            for (int j = 0; j < blankCount; j++) {
                newBuilder.append(" ");
            }

            newBuilder.append(sbs.get(i));

            for (int j = 0; j < blankCount; j++) {
                newBuilder.append(" ");
            }

            sbs.add(newBuilder);

            originBuilder.append(originBuilder);
        }

        recur(k + 1);
    }
}