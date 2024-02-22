import java.util.*;

public class Main {

    static int n, m;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Pair> chicken = new ArrayList<>();
    static ArrayList<Pair> home = new ArrayList<>();
    static int[][] map;
    static boolean[] choose;

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        map = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = scan.nextInt();
                if(map[i][j] == 1)
                    home.add(new Pair(i, j));
                else if(map[i][j] == 2)
                    chicken.add(new Pair(i, j));
            }
        }

        choose = new boolean[chicken.size()];

        dfs(0, 0);
        System.out.println(min);
    }

    public static void dfs(int cnt, int now) {
        if(cnt == m) {
            int sum = 0;
            for(int i = 0; i < home.size(); i++) {
                int distance = Integer.MAX_VALUE;
                for(int j = 0; j < chicken.size(); j++) {
                    if(choose[j] == true) {
                        int temp = Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y);
                        distance = Math.min(distance, temp);
                    }
                }
                sum += distance;
            }
            min = Math.min(sum, min);
            return;
        }

        for(int i = now; i < chicken.size(); i++) {
            if(choose[i] == false) {
                choose[i] = true;
                dfs(cnt + 1, i + 1);
                choose[i] = false;
            }
        }
    }
}
