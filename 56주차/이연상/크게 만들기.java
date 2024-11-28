// [BOJ] 크게 만들기

import java.io.*;
import java.util.*;

public class MakeBig {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String numbers = br.readLine().trim();
        Deque<Character> stack = new ArrayDeque<>();

        for (char number : numbers.toCharArray()) {
            while (!stack.isEmpty() && stack.peekLast() < number && k > 0) {
                stack.pollLast();
                k--;
            }
            stack.addLast(number);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stack.size() - k; i++) {
            result.append(stack.pollFirst());
        }

        System.out.println(result);
    }
}