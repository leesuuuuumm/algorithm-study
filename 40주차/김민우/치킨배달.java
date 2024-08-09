import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달 {

    static int N, M, min = Integer.MAX_VALUE;
    static ArrayList<Node> houses = new ArrayList<>();
    static ArrayList<Node> chickens = new ArrayList<>();

    static int[] tgt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tgt = new int[M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if(tmp == 1) houses.add(new Node(i, j));
                else if(tmp == 2) chickens.add(new Node(i, j));
            }
        }

        // 치킨 집 comb
        comb(0, 0);

        System.out.println(min);
    }

    static void comb(int tgtIdx, int srcIdx) {
        if(tgtIdx == M) {

            // 최소 치킨거리 합
            int sum = 0;

            for(int i=0; i<houses.size(); i++) {
                int cDis = Integer.MAX_VALUE;

                for(int j=0; j<M; j++) {
                    int tmp = calDis(i, tgt[j]);
                    cDis = Math.min(cDis, tmp);
                }

                sum += cDis;
            }

            min = Math.min(min, sum);
            return;
        }

        for(int i=srcIdx; i<chickens.size(); i++) {
            tgt[tgtIdx] = i;
            comb(tgtIdx+1, i+1);
        }
    }

    // 치킨거리
    static int calDis(int houseIdx, int chickenIdx) {
        Node h = houses.get(houseIdx);
        Node c = chickens.get(chickenIdx);

        return Math.abs(h.x-c.x) + Math.abs(h.y-c.y);
    }

    static class Node {
        int x, y;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}