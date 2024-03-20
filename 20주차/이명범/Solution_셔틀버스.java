import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 버스 시간 정보를 담을 큐
        Queue<String> queue = new LinkedList<>();
        // 정답
        String answer = "";

        // 시간 정보 (초기 값은 09:00에 시작하므로 H는 9, M은 0으로 둔다.)
        int H = 9;
        int M = 0;

        for (int i = 0; i < n; i++) {
            // H + (n * t) / 60는 i번째 버스가 오는 시각, M + (n * t) % 60)는 i번째 버스가 오는 분 이다.
            queue.offer(String.format("%02d:%02d", H + (t * i) / 60, M + (t * i) % 60));
        }
        // 오름차순으로 정렬
        Arrays.sort(timetable);

        // 몇 번쨰 크루인지
        int crewIdx = 0;

        while (!queue.isEmpty()) {
            // 현재 버스에 탄 크루의 수
            int count = 0;
            // 처음 온 버스
            String curBus = queue.poll();


            // 현재 버스에 크루가 타는 로직
            while (crewIdx < timetable.length) {
                // 현재 크루가 온 시간이 버스와 같거나 버스보다 먼저 왔다면 카운트와 crewIdx를 1 증가시킨다. 만약 이 사람이 버스를 타지 못한다면 다음 사람도 당연히 타지 못하므로 break;
                if (timetable[crewIdx].compareTo(curBus) <= 0) {
                    count++;
                    crewIdx++;
                } else break;
                // 만약 count가 m이면 break
                if (count == m) break;
            }

            // 현재 버스가 떠나고 다음 버스가 남아있으면 continue;
            if (!queue.isEmpty()) continue;

            // 현재 버스가 마지막 버스일 때 현재 버스의 자리가 남아있다면 현재 버스가 온 시간에 맞춰 타면 됨.
            if (count < m) answer = curBus;

            // 현재 버스가 마지막 버스일 때 현재 버스의 자리가 없다면 현재 버스를 마지막에 탄 사람보다 1분 일찍 오면 됨.
            if (count == m) {
                // 마지막에 탄 사람이 온 시각 및 분
                int hour = Integer.parseInt(timetable[crewIdx - 1].substring(0, 2));
                int min = Integer.parseInt(timetable[crewIdx - 1].substring(3, 5));

                if (min == 0) {
                    hour--;
                    min = 59;
                } else {
                    min--;
                }

                answer = String.format("%02d:%02d", hour, min);
            }
        }


        return answer;
    }
}