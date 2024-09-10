import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 실행시간 {
    static int N, M, K;
    static ArrayList<Integer>[] graph;
    static int[] timeTable;
    static int startIdx, endIdx;
    static int minTotal = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        timeTable = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            timeTable[i] = Integer.parseInt(st.nextToken());
        }

        int[] inDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            inDegree[end]++;
        }

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                startIdx = i;
                break;
            }
        }

        endIdx = getEndNode(inDegree);

        if (K == 0) {
            System.out.println(calculateTotal(timeTable));
        } else {
            generateCombinations(new boolean[N + 1], 1, 0);
            System.out.println(minTotal);
        }
    }

    static int getEndNode(int[] inDegree) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIdx);
        int lastNode = startIdx;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            lastNode = current;

            for (int next : graph[current]) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return lastNode;
    }

    static void generateCombinations(boolean[] selected, int index, int count) {
        if (count == K) {
            int[] modifiedTimeTable = timeTable.clone();
            for (int i = 1; i <= N; i++) {
                if (selected[i]) {
                    modifiedTimeTable[i] = 0;
                }
            }
            minTotal = Math.min(minTotal, calculateTotal(modifiedTimeTable));
            return;
        }

        for (int i = index; i <= N; i++) {
            if (i != startIdx && i != endIdx) {
                selected[i] = true;
                generateCombinations(selected, i + 1, count + 1);
                selected[i] = false;
            }
        }
    }

    static int calculateTotal(int[] times) {
        int[] maxTime = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int next : graph[i]) {
                inDegree[next]++;
            }
        }

        queue.offer(startIdx);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                maxTime[next] = Math.max(maxTime[next], maxTime[current] + times[current]);
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return maxTime[endIdx] + times[endIdx];
    }
}