import java.io.*;
import java.util.*;

public class Main {
    /*
    1초, 256MB
    R 뒤집기, D 버리기
    함수 조합 가능
    time O(N + P * N)

    optimal
    줄일만한 건 R 부분?
    뒤집을 필요가 없다. deque니까 읽는 방향만 바꿔주면 끝
     */
    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int j = 0; j < t; j++) {
            String inst = br.readLine();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            Stack<Integer> stack = new Stack<>();

            // deque에 원소 입력
            for (int i = 0; i < n; i++) {
                deque.offerLast(Integer.parseInt(st.nextToken()));
            }

            boolean error = false; // false 에러 없음, true 에러 있음
            boolean reverse = false; // false 정방향, true 역방향
            // 명령어 읽고 처리
            for (int k = 0; k < inst.length(); k++) {
                char ins = inst.charAt(k);

                switch (ins) {
                    case 'R':
                        reverse = !reverse;
                        break;

                    case 'D':
                        if (!deque.isEmpty()) {
                            if (!reverse) { // 정방향
                                deque.pollFirst();
                            } else { // 역방향
                                deque.pollLast();
                            }
                        } else {
                            error = true;
                        }
                        break;
                }

                if (error) {
                    break;
                }
            }

            if (error) {
                bw.write("error\n");
                continue;
            }

            bw.write("[");

            if (!deque.isEmpty()) {
                if (!reverse) { // 정방향
                    bw.write(deque.pollFirst() + "");
                    while (!deque.isEmpty()) {
                        bw.write("," + deque.pollFirst());
                    }
                } else { // 역방향
                    bw.write(deque.pollLast() + "");
                    while (!deque.isEmpty()) {
                        bw.write("," + deque.pollLast());
                    }
                }
            }

            bw.write("]\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
