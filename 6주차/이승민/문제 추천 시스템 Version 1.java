import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Problem implements Comparable<Problem> {
    int num;
    int rank;

    public Problem(int num, int rank) {
        this.num = num;
        this.rank = rank;
    }

    @Override
    public int compareTo(Problem o) {
        int res = rank - o.rank; // 난이도 비교
        if (rank == o.rank) { // 난이도가 같으면 번호 비교
            res = num - o.num;
        }

        return res;
    }
}

public class Main {
    /*
    O(NlgN)
     */
    // write your code here
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TreeSet<Problem> bst = new TreeSet<>(); // 문제 저장
        HashMap<Integer, Integer> hash = new HashMap<>(); // 난이도 저장

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int rank = Integer.parseInt(st.nextToken());

            bst.add(new Problem(num, rank));
            hash.put(num, rank);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "add":
                    int num = Integer.parseInt(st.nextToken());
                    int rank = Integer.parseInt(st.nextToken());

                    bst.add(new Problem(num, rank));
                    hash.put(num, rank);
                    break;
                case "solved":
                    num = Integer.parseInt(st.nextToken());
                    bst.remove(new Problem(num, hash.get(num)));
                    hash.remove(num);
                    break;
                case "recommend":
                    int oper = Integer.parseInt(st.nextToken());
                    if (oper == 1) {
                        sb.append(bst.last().num).append('\n');
                    } else {
                        sb.append(bst.first().num).append('\n');
                    }
                    break;
            }
        }

        System.out.println(sb);
    }
}