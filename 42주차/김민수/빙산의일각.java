import java.io.*;
import java.util.*;

public class 빙산의일각 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        TreeSet<Integer> heights = new TreeSet<>();
        Map<Integer, Integer> heightToIndex = new HashMap<>();

        int[] icebergs = new int[n + 2];
        List<List<Integer>> indexArr = new ArrayList<>(); //각 높이에 해당하는 빙산의 인덱스
        boolean[] visited = new boolean[n + 2];

        for (int i = 1; i <= n; i++) {
            icebergs[i] = Integer.parseInt(br.readLine());
            heights.add(icebergs[i]);
        }

        int idx = 0;
        for (int height : heights) {
            heightToIndex.put(height, idx++);
        }

        for (int i = 0; i < heights.size(); i++) {
            indexArr.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            indexArr.get(heightToIndex.get(icebergs[i])).add(i);
        }

        int result = 0;

        for (int i = heights.size() - 1; i >= 0; i--) {
            for (int curIdx : indexArr.get(i)) {
                if (!visited[curIdx - 1] && !visited[curIdx + 1]) {
                    result++;
                } else if (visited[curIdx - 1] && visited[curIdx + 1]) {
                    result--;
                }
                visited[curIdx] = true;
            }
            answer = Math.max(answer, result);
        }

        System.out.println(answer);
    }
}
