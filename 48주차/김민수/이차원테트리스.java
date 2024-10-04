import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이차원테트리스 {
    static int k;
    static boolean[][][] map;
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        map = new boolean[2][6][4];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            simulation(type, r, c);
        }

        int num = 0;
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j <= 3; j++) {
                for (int k = 0; k <= 1; k++) {
                    if (map[k][i][j])
                        num += 1;
                }
            }
        }
        System.out.println(score);
        System.out.println(num);
    }

    public static void simulation(int type, int r, int c) {
        moveBlockToYellow(type, r, c);
        if (type == 1) {
            moveBlockToRed(1, c, 3 - r);
        } else if (type == 2) {
            moveBlockToRed(3, c, 3 - r);
        } else {
            moveBlockToRed(2, c, 3 - (r + 1));
        }

    }


    public static void moveBlockToYellow(int type, int r, int c) {
        if (type == 1) {
            int posR = getR_type1(c, 0);
            map[0][posR][c] = true;
        } else if (type == 2) {
            int posR = getR_type2(r, c, 0);
            map[0][posR][c] = map[0][posR][c + 1] = true;
        } else {
            int posR = getR_type1(c, 0);
            map[0][posR][c] = map[0][posR - 1][c] = true;
        }
        checkGetPoint(0);
        int num = doRemove(0);
        if (num > 0) {
            remove(num, 0);
        }
    }

    public static int getR_type1(int c, int idx) {
        for (int i = 0; i <= 5; i++) {
            if (map[idx][i][c]) {
                return i - 1;
            }
        }
        return 5;

    }

    public static int getR_type2(int r, int c, int idx) {

        for (int i = 0; i <= 5; i++) {
            if (map[idx][i][c] || map[idx][i][c + 1]) {
                return i - 1;
            }
        }
        return 5;
    }


    public static void checkGetPoint(int idx) {
        boolean flag = true;
        for (int i = 2; i <=5; i++) {
            flag = true;
            for (int j = 0; j <= 3; j++) {
                if (!map[idx][i][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                score += 1;
                for (int r = i; r >=2; r--) {
                    for (int c = 0; c <= 3; c++) {
                        map[idx][r][c] = map[idx][r - 1][c];
                        map[idx][r-1][c]=false;
                    }
                }
            }
        }
    }

    public static int doRemove(int idx) {
        int rowNum = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 3; j++) {
                if (map[idx][i][j]) {
                    rowNum += 1;
                    break;
                }
            }
        }
        return rowNum;
    }

    public static void remove(int countR, int idx) {

        for (int i = 5 - countR; i >= 0; i--) {
            for (int c = 0; c <= 3; c++) {
                map[idx][i + countR][c] = map[idx][i][c];
            }
        }
        for(int r=0;r<=1;r++) {
            for (int c = 0; c <= 3; c++) {
                map[idx][r][c]=false;
            }
        }
    }



    public static void moveBlockToRed(int type, int r, int c) {

        if (type == 1) {
            int posR = getR_type1(c, 1);
            map[1][posR][c] = true;
        } else if (type == 2) {
            int posR = getR_type2(r, c, 1);
            map[1][posR][c] = map[1][posR][c + 1] = true;
        } else {
            int posR = getR_type1(c, 1);
            map[1][posR][c] = map[1][posR - 1][c] = true;
        }
        checkGetPoint(1);
        int num = doRemove(1);
        if (num > 0) {
            remove(num, 1);
        }
    }
}
