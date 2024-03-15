import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 입차시간 map
        Map<String, Integer> inMinuteMap = new HashMap<>();
        // 누적시간 map
        Map<String, Integer> parkingMinuteMap = new HashMap<>();

        for (String record : records) {
            String[] split = record.split(" ");

            String time = split[0];
            String carNumber = split[1];
            String status = split[2];

            // 만약 입차라면 recordMap에 넣기
            if (status.equals("IN")) {
                inMinuteMap.put(carNumber, parseMinute(time));
            }
            // 만약 출차라면
            else if (status.equals("OUT")) {
                if (!parkingMinuteMap.containsKey(carNumber)) {
                    parkingMinuteMap.put(carNumber, parseMinute(time) - inMinuteMap.remove(carNumber));
                } else {
                    parkingMinuteMap.put(carNumber, parkingMinuteMap.get(carNumber) + parseMinute(time) - inMinuteMap.remove(carNumber));
                }
            }
        }

        // 오늘 출차하지 않은 차량 시간 정산
        Set<String> inCarNumber = inMinuteMap.keySet();
        for (String carNumber : inCarNumber) {
            if (!parkingMinuteMap.containsKey(carNumber)) {
                parkingMinuteMap.put(carNumber, parseMinute("23:59") - inMinuteMap.get(carNumber));
            } else {
                parkingMinuteMap.put(carNumber, parkingMinuteMap.get(carNumber) + parseMinute("23:59") - inMinuteMap.get(carNumber));
            }

        }

        // 오늘 주차한 차량 번호
        List<String> parkingCarNumber = parkingMinuteMap.keySet()
                .stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();

        // 정산하기
        for (String car : parkingCarNumber) {
            int parkingMinute = parkingMinuteMap.get(car);

            // 주차 시간이 기본 시간 이하라면
            if (parkingMinute <= fees[0]) {
                answer.add(fees[1]);
            }
            // 그렇지 않으면
            else {
                // 기본 시간만큼 뺀 값에서 단위 시간 당 금액을 계산해야함
                int unitMinute;

                if ((parkingMinute - fees[0]) % fees[2] == 0) {
                    unitMinute = (parkingMinute - fees[0]) / fees[2];
                } else {
                    unitMinute = (parkingMinute - fees[0]) / fees[2] + 1;
                }

                // 기본 요금에 단위 시간 당 요금 추가
                answer.add(unitMinute * fees[3] + fees[1]);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    private static int parseMinute(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }

}