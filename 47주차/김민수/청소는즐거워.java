import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 청소는즐거워 {
    static int n;
    static int[][] map;
    static ArrayList<Integer> directions;
    //왼, 아래, 오른, 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    //0:왼 1: 아래 2:오른 3:
    //왼: prev 위 , 아래: 1 퍼센트 cur 위 아래 7퍼센트, 위위 아래아래 2퍼센트 cur왼
    static int outSide;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        outSide = 0;
        directions=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        makeDirections();
        simulation();
        System.out.println(outSide);
    }

    public static void simulation() {
        int prevX = n / 2;
        int prevY = n / 2;
        int count = 0;
        while (!(prevX == 0 && prevY == 0)) {
            int dir = directions.get(count);
            int curX = prevX + dx[dir];
            int curY = prevY + dy[dir];
            moveDust(dir,prevX,prevY);
            prevX=curX;
            prevY=curY;
            count+=1;
        }
    }

    public static void moveDust(int dir, int prevX, int prevY) {
        int curX = prevX + dx[dir];
        int curY = prevY + dy[dir];
        int total = map[curX][curY];
        map[curX][curY] = 0;
        int oppositeDir = (dir + 2) % 4;
        int dir1 = 4;
        int dir2 = 4;
        for (int i = 0; i < 4; i++) {
            if (i != dir & i != oppositeDir) {
                if (dir1 == 4) {
                    dir1 = i;
                } else {
                    dir2 = i;
                }
            }
        }
        double[] percent = new double[9];
        percent[0] = 0.01;
        percent[1] = 0.01;

        percent[2] = 0.07;
        percent[3] = 0.07;

        percent[4] = 0.02;
        percent[5] = 0.02;

        percent[6] = 0.1;
        percent[7] = 0.1;

        percent[8] = 0.05;

        ArrayList<Integer>[] directions = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            directions[i] = new ArrayList<>();
        }
        directions[0].add(dir1);
        directions[1].add(dir2);

        directions[2].add(dir);
        directions[2].add(dir1);
        directions[3].add(dir);
        directions[3].add(dir2);

        directions[4].add(dir);
        directions[4].add(dir1);
        directions[4].add(dir1);
        directions[5].add(dir);
        directions[5].add(dir2);
        directions[5].add(dir2);

        directions[6].add(dir);
        directions[6].add(dir);
        directions[6].add(dir1);
        directions[7].add(dir);
        directions[7].add(dir);
        directions[7].add(dir2);

        directions[8].add(dir);
        directions[8].add(dir);
        directions[8].add(dir);

        directions[9].add(dir);
        directions[9].add(dir);
        int sum=0;
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> dire = directions[i];
            int nx = prevX;
            int ny = prevY;
            for (int l = 0; l < dire.size(); l++) {
                nx += dx[dire.get(l)];
                ny += dy[dire.get(l)];
            }
            int tmp= (int) (total*percent[i]);
            if(isInRange(nx,ny)){
                map[nx][ny]+=tmp;
            }else{
                outSide+=tmp;
            }
            sum+=tmp;
        }
        ArrayList<Integer> aDir=directions[9];
        int last=total-sum;
        int nx = prevX;
        int ny = prevY;
        for (int l = 0; l < aDir.size(); l++) {
            nx += dx[aDir.get(l)];
            ny += dy[aDir.get(l)];
        }
        if(isInRange(nx,ny)){
            map[nx][ny]+=last;
        }else{
            outSide+=last;
        }
    }

    static boolean isInRange(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && ny < n && nx < n);
    }

    public static void makeDirections() {
        int count = 0;
        while (true) {
            if (count == n - 1) {
                for (int c = 0; c < count; c++) {
                    directions.add(0);
                }
                break;
            } else {
                count += 1;
                for (int i = 0; i < 2; i++) {
                    for (int c = 0; c < count; c++) {
                        directions.add(i);
                    }
                }
                count += 1;
                for (int i = 2; i < 4; i++) {
                    for (int c = 0; c < count; c++) {
                        directions.add(i);
                    }
                }
            }
        }
    }
}
