import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, String> in = new HashMap<>();
        TreeMap<Integer, Integer> time = new TreeMap<>();
        for (String r : records) {
            String[] tmp = r.split(" ");
            int num = Integer.parseInt(tmp[1]);
            if (tmp[2].equals("IN")) {
                in.put(num, tmp[0]);
            } else {
                // 입차, 출차 시간을 바탕으로 누적 시간을 구한다.
                String[] st = in.get(num).split(":");
                String[] en = tmp[0].split(":");
                time.put(num, time.getOrDefault(num, 0) + (Integer.parseInt(en[0]) * 60 + Integer.parseInt(en[1])) - (Integer.parseInt(st[0]) * 60 + Integer.parseInt(st[1])));
                in.remove(num);
            }
        }
        // 출차 내역이 없는 차들의 누적시간을 마저 구한다.
        for (Integer key : in.keySet()) {
            String[] st = in.get(key).split(":");
            time.put(key, time.getOrDefault(key, 0) + (23 * 60 + 59) - (Integer.parseInt(st[0]) * 60 + Integer.parseInt(st[1])));
        }

        int[] ans = new int[time.size()];
        int idx = 0;
        while (!time.isEmpty()) {
            // 주차요금을 계산한 뒤 정답 배열에 저장한다.
            int diff = time.get(time.firstKey());
            if (diff <= fees[0]) {
                ans[idx] = fees[1];
                time.remove(time.firstKey());
                idx++;
                continue;
            }
            ans[idx] = fees[1] + (int) Math.ceil((diff - fees[0]) / (double) fees[2]) * fees[3];
            time.remove(time.firstKey());
            idx++;
        }

        return ans;
    }
}