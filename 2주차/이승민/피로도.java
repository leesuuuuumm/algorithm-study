
2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        12
        13
        14
        15
        16
        17
        18
        19
        20
        21
        22
        23
        24
        25
        26
        27
        28
        29
        30
        31
        32
        33
        34
        35
        36
        37
        38
        39
        40
        41
        42
        43
        44
        45
        46
        47
        48
        49
        50
        51
        52
        53
import java.util.*;

class Solution {
    static int curPirodo; // 현재 피로도
    static int max; // 정답
    static int totalVis; // 현재 방문한 던전 개수
    static int trueVis; // 현재 탐험한 던전 개수
    static int[][] dun;
    static boolean[] isUsed;

    public int solution(int k, int[][] dungeons) {
        curPirodo = k;
        max = 0;
        totalVis = 0;
        trueVis = 0;
        dun = dungeons;
        isUsed = new boolean[8];

        func();

        return max;
    }

    public static void func() {
        if (totalVis == 8) {
            return;
        }

        for (int i = 0; i < dun.length; i++) {
            if (!isUsed[i]) {
                boolean flag = false;
                isUsed[i] = true;
                totalVis++;
                if (dun[i][0] <= curPirodo && curPirodo - dun[i][1] >= 0) {
                    flag = true;
                    curPirodo -= dun[i][1];
                    trueVis++;
                    max = Math.max(max, trueVis);
                }

                func();

                isUsed[i] = false;
                totalVis--;
                if (flag) {
                    curPirodo += dun[i][1];
                    trueVis--;
                }
            }
        }
    }
}