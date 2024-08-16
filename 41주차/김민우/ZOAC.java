package 김민우;

import java.io.*;
import java.util.*;

public class ZOAC {

    static class Data implements Comparable<Data> {
        char ch;
        int idx;
        public Data(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }

        @Override
        public int compareTo(Data o) {
            if (this.ch == o.ch) {
                return this.idx - o.idx;
            }
            return this.ch - o.ch;
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            Data data = new Data(str.charAt(i), i);
            list.add(data);
        }

        Collections.sort(list); // 정렬
        func(str, list);

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    private static void func(String str, ArrayList<Data> list) {
        boolean[] check = new boolean[str.length()];
        int startIdx = -1;

        while (!isCompleted(check)) { // 모든 문자가 check되었으면 종료
            // 리스트 전체 탐색하여 문자 출력
            for (Data data: list) {
                if (!check[data.idx] && data.idx > startIdx) {
                    startIdx = data.idx;
                    check[data.idx] = true;
                    print(check, str);
                }
            }

            // update startIdx
            // 마지막 위치부터 앞으로 가면서 문자가 체크되어있는지 연속적으로 확인한 후, 체크하지 않은 문자가 있을 시 break
            int i = list.size() - 1;
            for (; i >= 0; i--) {
                if (!check[i]) break;
            }

            // 체크하지 않은 문자가 있는 구간이 있을 때 그 문자에 앞에 있는 문자 중 가장 가까운 체크된 문자의 위치를 startIdx로 지정
            boolean flag = false;
            for (;i >= 0; i--) {
                if (check[i]) {
                    flag = true;
                    startIdx = i;
                    break;
                }
            }
            if (!flag) startIdx = -1; // 앞에 체크된 문자가 없으면 startIdx를 -1로 초기화
        }
    }

    private static boolean isCompleted(boolean[] check) {
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                return false;
            }
        }
        return true;
    }

    private static void print(boolean[] check, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (check[i]) {
                sb.append(str.charAt(i));
            }
        }
        sb.append("\n");
    }
}
