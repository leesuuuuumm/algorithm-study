import java.util.Scanner;


public class Main {
    public static int N, white, blue;
    public static int[][] map;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        divideSquare(0, 0, N);

        System.out.println(white);
        System.out.println(blue);

    }

    public static void divideSquare(int r, int c, int s) {

        if(colorCheck(r, c, s)) {
            if(map[r][c] == 0) {
                white++;
            }
            else {
                blue++;
            }
            return;
        }

        int newSize = s / 2;

        divideSquare(r, c, newSize);
        divideSquare(r, c + newSize, newSize);
        divideSquare(r + newSize, c, newSize);
        divideSquare(r + newSize, c + newSize, newSize);
    }
    public static boolean colorCheck(int r, int c, int s) {
        int color = map[r][c];

        for(int i = r; i < r + s; i++) {
            for(int j = c; j < c + s; j++) {
                if(map[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
