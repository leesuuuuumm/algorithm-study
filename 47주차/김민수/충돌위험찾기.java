import java.util.*;

class 충돌위험찾기 {

    static int[][][] map;
    static int maxTime = 0;

    public int solution(int[][] points, int[][] routes) {
        // maxTime 값을 충분히 크게 설정
        int answer = 0;
        int maxR = 0;
        int maxC = 0;

        // 포인트의 최대 좌표 값 계산
        for (int[] p : points) {
            maxR = Math.max(maxR, p[0]);
            maxC = Math.max(maxC, p[1]);
        }
        map=new int[30001][101][101];

        // 모든 로봇의 경로를 따라가면서 충돌 가능성을 확인
        for (int[] route : routes) {
            int time = 0;  // 각 로봇마다 출발 시간은 0초부터 시작
            map[0][points[route[0]-1][0]][points[route[0]-1][1]]+=1;
            for (int i = 0; i < route.length - 1; i++) {
                int startIdx = route[i] - 1;
                int endIdx = route[i + 1] - 1;
                // 경로의 첫 지점은 기록하지 않음
                time = makeRoute(points[startIdx], points[endIdx], time);
            }
            maxTime = Math.max(time, maxTime); // 최대 시간 계산
        }

        // 충돌이 발생한 좌표를 계산
        for (int time = 0; time <= maxTime; time++) {
            for (int i = 1; i <= maxR; i++) {
                for (int j = 1; j <= maxC; j++) {
                    if (map[time][i][j] > 1) {
                        answer += 1;
                    }
                }
            }
        }

        return answer;
    }

    // 경로를 따라가며 충돌 여부를 기록하는 함수
    public static int makeRoute(int[] start, int[] end, int startTime) {
        int time = startTime;
        int startX = start[0], startY = start[1];
        int endX = end[0], endY = end[1];

        // r 좌표를 먼저 이동
        while (startX != endX) {
            time++;
            if (startX < endX) {
                startX++;
            } else {
                startX--;
            }
            map[time][startX][startY] += 1;
        }

        // c 좌표 이동
        while (startY != endY) {
            time++;
            if (startY < endY) {
                startY++;
            } else {
                startY--;
            }
            map[time][startX][startY] += 1;

        }


        return time; // 최종 시간 반환
    }
}
