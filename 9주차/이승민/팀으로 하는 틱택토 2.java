import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int[][] map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            String s = sc.next();
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        int team = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                boolean win = false;

                for (int l = 0; l < 3; l++) {
                    int cond1 = 0;
                    int cond2 = 0;
                    for (int k = 0; k < 3; k++) {
                        if (map[l][k] == i) cond1++;
                        if (map[l][k] == j) cond2++;                    
                    }
                    if (cond1 + cond2 == 3 && cond1 >= 1 && cond2 >= 1) {
                        win = true;
                    }
                }

                for (int l = 0; l < 3; l++) {
                    int cond1 = 0;
                    int cond2 = 0;
                    for (int k = 0; k < 3; k++) {
                        if (map[k][l] == i) cond1++;
                        if (map[k][l] == j) cond2++;
                    }
                    if (cond1 + cond2 == 3 && cond1 >= 1 && cond2 >= 1) {
                        win = true;
                    }
                }

                int cond1 = 0;
                int cond2 = 0;
                for (int l = 0; l < 3; l++) {
                    if (map[l][l] == i) cond1++;
                    if (map[l][l] == j) cond2++;
                }
                if (cond1 + cond2 == 3 && cond1 >= 1 && cond2 >= 1) {
                    win = true;
                }

                cond1 = 0;
                cond2 = 0;
                for (int l = 0; l < 3; l++) {
                    if (map[2 - l][l] == i) cond1++;
                    if (map[2 - l][l] == j) cond2++;
                }
                if (cond1 + cond2 == 3 && cond1 >= 1 && cond2 >= 1) {
                    win = true;
                }

                if (win) {
                    team++;
                }
            }
        }

        System.out.print(team);
    }
}
