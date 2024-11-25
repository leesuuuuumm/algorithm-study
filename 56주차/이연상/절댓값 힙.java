// [BOJ] 절댓값 힙

import java.io.*;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AbsoluteHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numbers = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int i = 0; i < numbers; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                heap.add(new int[]{Math.abs(num), num});
            } else {
                if (heap.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(heap.poll()[1]);
                }
            }
        }
    }
}