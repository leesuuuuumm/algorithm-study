import java.util.*;

class Info1 {
    int p, m, t;

    Info1(int p, int m, int t) {
        this.p = p;
        this.m = m;
        this.t = t;
    }
}

class Info2 {
    int p, t;

    Info2(int p, int t) {
        this.p = p;
        this.t = t;
    }
}

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 사람
        int m = sc.nextInt(); // 치즈
        int d = sc.nextInt();
        int s = sc.nextInt();
        Info1[] info1 = new Info1[1000];
        Info2[] info2 = new Info2[50];
        for (int i = 0; i < d; i++) {
            info1[i] = new Info1(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        for (int i = 0; i < s; i++) { 
            info2[i] = new Info2(sc.nextInt(), sc.nextInt());
        }

        int ans = 0;
        // i번째 치즈가 상했다고 가정하고, time 배열에 각 사람이 언제 치즈를 먹었는지 기록
        for (int i = 1; i <= m; i++) {
            int[] time = new int[n + 1];
            for (int j = 0; j < d; j++) {
                if (info1[j].m != i) continue;
                
                int person = info1[j].p;
                if (time[person] == 0)
                    time[person] = info1[j].t;
                else if (time[person] > info1[j].t)
                    time[person] = info1[j].t;
            }

            boolean possible = true; // i번째 치즈가 실제로 상했을 가능성이 있으면 true
            for (int j = 0; j < s; j++) {
                int person = info2[j].p;
                if (time[person] == 0)
                    possible = false;
                if (time[person] >= info2[j].t)
                    possible = false;
            }

            // 약 개수 확인
            int cnt = 0;
            if (possible) {
                for (int j = 1; j <= n; j++) {
                    if (time[j] != 0)
                        cnt++;
                }
            }

            ans = Math.max(ans, cnt);
        }

        System.out.print(ans);
    }
}
