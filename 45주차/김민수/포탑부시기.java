import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 포탑부시기 {
    static int N,M,K;
    static int[][] map;
    static ArrayList<Turret> remainTur;
    public static int[] dx={0, 1, 0, -1};
    public static int[] dy={1, 0, -1, 0};
    static int [] eightDx={0, 1, 0, -1, 1, 1, -1, -1};
    static int[] eightDy={1, 0, -1, 0, 1, -1, -1, 1};

    public static boolean[][] isVisited;
    static boolean[][] way;
    static int min;
    static boolean canLaser;
    static int[][] backX;
    static int[][] backY;
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=parse(st);
        M=parse(st);
        K=parse(st);
        map=new int[N][M];
        backX=new int[N][M];
        backY=new int[N][M];
        remainTur=new ArrayList<>();
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=parse(st);
                if(map[i][j]>0){
                    remainTur.add(new Turret(map[i][j],0,i,j));
                }
            }
        }

        Collections.sort(remainTur);

        for(T=1;T<=K;T++){
            //가장 약한 공격자 선택
            if(remainTur.size()<=1)
                break;
            Turret weak=remainTur.get(0);
            //가장 강한 공격자 선정(공격당하는)
            Turret strong=remainTur.get(remainTur.size()-1);
            //공격
            attack(weak, strong);
            Collections.sort(remainTur);
        }
        System.out.println(remainTur.get(remainTur.size()-1).power);
    }

    public static void attack(Turret weak, Turret strong){
        map[weak.row][weak.col]+=(N+M);
        //공격하는 사람 : attackTime 현재 시간, power 증가
        weak.time=T;
        weak.power+=(N+M);
        //공격당하는 사람: power 감소
        strong.power=Math.max(strong.power-weak.power, 0);
        //레이저 공격읆 먼저 시도
        isVisited=new boolean[N][M];
        way=new boolean[N][M];
        isVisited[weak.row][weak.col]=true;
        canLaser=false;
        laser(weak.row, weak.col, strong);

        //공격당하는 포탑은 공격자의 공격력만큼 피해
        map[strong.row][strong.col]=strong.power;

        //레이저 가능하면
        if(canLaser){
            int cx=backX[strong.row][strong.col];
            int cy=backY[strong.row][strong.col];
            while(!(cx==weak.row&&cy==weak.col)){
                map[cx][cy]=Math.max(0, map[cx][cy]-weak.power/2);

                int nextCx=backX[cx][cy];
                int nextCy=backY[cx][cy];

                way[cx][cy]=true;

                cx=nextCx;
                cy=nextCy;
            }
        }else{
            //포탄 공격
            for(int i=0;i<8;i++){
                int[] pos=trans(strong.row+eightDx[i], strong.col+eightDy[i]);
                int nx=pos[0];
                int ny=pos[1];
                if(map[nx][ny]>0&&!(nx==weak.row&&ny==weak.col)){
                    way[nx][ny]=true;
                    map[nx][ny]=Math.max(0, map[nx][ny]-weak.power/2);
                }
            }
        }
        //정비
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if((i==weak.row&&j==weak.col)||(i==strong.row&&j==strong.col))
                    continue;
                if(!way[i][j]&&map[i][j]>0){
                    map[i][j]+=1;
                }
            }
        }

        //다시 추가
        for(Turret tur:remainTur){
            int x=tur.row;
            int y=tur.col;
            tur.power=map[x][y];
            if(x==weak.row&&y==weak.col)
                tur.time=T;
        }

        remainTur.removeIf(tur -> {
            return tur.power <= 0; // 조건에 맞는 요소 제거
        });

    }
    public static void laser(int r, int c, Turret strong) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currR = curr[0];
            int currC = curr[1];

            if (currR == strong.row && currC == strong.col) {
                canLaser = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int[] pos = trans(currR + dx[i], currC + dy[i]);
                if (map[pos[0]][pos[1]] > 0 && !isVisited[pos[0]][pos[1]]) {
                    isVisited[pos[0]][pos[1]] = true;
                    backX[pos[0]][pos[1]]=currR;
                    backY[pos[0]][pos[1]]=currC;
                    queue.offer(pos);
                }
            }
        }
    }


    public static int[] trans(int nx, int ny){
        return new int[]{(nx+N)%N, (ny+M)%M};
    }


    public static int parse(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }


    public static class Turret implements Comparable<Turret>{
        public int power;
        public int time;
        public int row;
        public int col;

        public Turret(int power, int time, int row, int col){
            this.power=power;
            this.time=time;
            this.row=row;
            this.col=col;
        }

        @Override
        public int compareTo(Turret o) {
            if(this.power==o.power){
                if(this.time==o.time){
                    if((this.row+this.col)==(o.row+o.col)){
                        return o.col-this.col;
                    }
                    return (o.row+o.col)-(this.row+this.col);
                }
                return o.time-this.time;
            }
            return (this.power-o.power);
        }
    }
}