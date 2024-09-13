import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 꼬리잡기놀이 {
    static int n, teamCount, round;
    static ArrayList<int[]> headPos=new ArrayList<>();
    static int[][] map;
    //위, 왼, 아래, 오른
    static int[] dx={-1, 0, 1, 0};
    static int[] dy={0, -1, 0, 1};
    static boolean[][] visited;
    static int result=0;
    static int k=1;
    static ArrayList<int[]> newTail;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        teamCount=Integer.parseInt(st.nextToken());
        round=Integer.parseInt(st.nextToken());
        map=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    headPos.add(new int[]{i,j});
                }
            }
        }
        while(k<=round){
            visited=new boolean[n+1][n+1];
            move();
            throwBall();
            k+=1;
        }
        System.out.println(result);
    }
    public static void move(){
        // 먼저 각 팀은 머리사람을 따라서 한 칸 이동합니다.
        // 1은 4의 위치에
        // 2~3은 앞 위치로
        ArrayList<int[]> newHead=new ArrayList<>();
        newTail=new ArrayList<>();
        for(int[] head:headPos){
            int[] newPos=new int[2];
            visited[head[0]][head[1]]=true;
            for(int i=0;i<4;i++){
                int nx=head[0]+dx[i];
                int ny=head[1]+dy[i];
                if(isInRange(nx,ny)){
                    if(map[nx][ny]>=3){
                        newPos[0]=nx;
                        newPos[1]=ny;
                    }
                }
            }
            dfs(head, 1, true);
            map[newPos[0]][newPos[1]]=1;
            newHead.add(newPos);
        }
        headPos=new ArrayList<>();
        headPos.addAll(newHead);
    }
    public static void dfs(int[] curPos, int value, boolean isFirst){
        if(value==3){//꼬리사람이면
            map[curPos[0]][curPos[1]]=4;
            return;
        }
        for(int i=0;i<4;i++){
            int nx=curPos[0]+dx[i];
            int ny=curPos[1]+dy[i];
            if(isInRange(nx,ny)&&!visited[nx][ny]){
                if(isFirst){
                    if (map[nx][ny] == 2) {
                        map[curPos[0]][curPos[1]] = map[nx][ny];
                        visited[nx][ny]=true;
                        dfs(new int[]{nx, ny}, map[nx][ny], false);
                    }
                }else {
                    if (map[nx][ny] == 2 || map[nx][ny] == 3) {
                        map[curPos[0]][curPos[1]] = map[nx][ny];
                        visited[nx][ny]=true;
                        if (map[nx][ny] == 3) {
                            newTail.add(new int[]{curPos[0], curPos[1]});
                        }
                        dfs(new int[]{nx, ny}, map[nx][ny], false);
                    }
                }
            }
        }
    }
    public static void throwBall(){
        // 각 라운드마다 공이 정해진 선을 따라 던져집니다.
        RoundInfo roundInfo=calculateDir();
        int x=roundInfo.x;
        int y=roundInfo.y;
        int dir=roundInfo.dir;
        // 공이 던져지는 경우에 해당 선에 사람이 있으면 최초에 만나게 되는 사람만이 공을 얻게 되어 점수를 얻게 됩니다.
        boolean flag=true;
        while(!isPerson(map[x][y])){
            x+=dx[dir];
            y+=dy[dir];
            if(!isInRange(x,y)) {
                flag=false;
                break;
            }
        }
        // 아무도 공을 받지 못하는 경우에는 아무 점수도 획득하지 못합니다.
        // 점수는 해당 사람이 머리사람을 시작으로 팀 내에서 k번째 사람이라면 k의 제곱만큼 점수를 얻게 됩니다.
        int kx=0; int ky=0;
        if(flag){
            Queue<Person> que=new ArrayDeque<>();
            que.add(new Person(x,y,1, false));
            int count=1;
            boolean[][] visited=new boolean[n+1][n+1];
            visited[x][y]=true;
            boolean isLast=false;
            boolean check=false;
            if(map[x][y]==3)
                isLast=true;
            //3->2->1
            while(!que.isEmpty()){
                Person p=que.poll();
                if(map[p.x][p.y]==1){
                        kx = p.x;
                        ky = p.y;
                        count = p.step;
                        break;
                }
                for(int i=0;i<4;i++){
                    int nx=p.x+dx[i];
                    int ny=p.y+dy[i];
                    if(isInRange(nx,ny)&&!visited[nx][ny]){
                        if(map[nx][ny]>=1&&map[nx][ny]<=2){
                            if(isLast&&map[nx][ny]==1){
                                if(p.check){
                                    visited[nx][ny]=true;
                                    que.add(new Person(nx,ny,p.step+1,true));
                                }
                            }else{
                                if(map[nx][ny]==2){
                                    check=true;
                                }
                                visited[nx][ny]=true;
                                que.add(new Person(nx,ny,p.step+1,check));
                            }

                        }
                    }
                }
            }
            result+=(count)*(count);

            //공을 획득한 팀의 경우에는 머리사람과 꼬리사람이 바뀝니다. 즉 방향을 바꾸게 됩니다.
            que=new ArrayDeque<>();
            visited=new boolean[n+1][n+1];
            visited[kx][ky]=true;
            que.add(new Person(kx,ky,1, false));
            int idx;
            for(idx=0;idx<headPos.size();idx++){
                int[] head=headPos.get(idx);
                if(head[0]==kx&&head[1]==ky){
                    break;
                }
            }
            headPos.remove(idx);
            while(!que.isEmpty()){
                Person p=que.poll();
                if(map[p.x][p.y]==3){
                    map[p.x][p.y]=1;
                    headPos.add(new int[]{p.x,p.y});
                    break;
                }
                for(int i=0;i<4;i++){
                    int nx=p.x+dx[i];
                    int ny=p.y+dy[i];
                    if(isInRange(nx,ny)&&!visited[nx][ny]){
                        visited[nx][ny]=true;
                        if(map[nx][ny]>=2&&map[nx][ny]<=3){
                            que.add(new Person(nx,ny,p.step+1,false));
                        }
                    }
                }
            }
            map[kx][ky]=3;
        }


    }

    public static boolean isPerson(int val){
        return (val>=1&&val<=3);
    }
    public static class Person{
        public int x, y, step;
        public boolean check;
        public Person(int x, int y, int step, boolean check){
            this.x=x;
            this.y=y;
            this.step=step;
            this.check=check;
        }
    }
    public static RoundInfo calculateDir(){
        // 1~n, n+1~2n, 2n+1~3n, 3n+1~4n
        //위, 왼, 아래, 오른 0 1 2 3
        int dir=k%(4*n);
        if(dir==0)
            dir=4*n;
        if(dir>=1&&dir<=n){ //오른
            return new RoundInfo(dir, 1, 3);
        }else if(dir>=(n+1)&&dir<=(2*n)){//위
            return new RoundInfo(n,dir-n,0);
        }else if(dir>=(2*n+1)&&dir<=(3*n)){//왼
            return new RoundInfo((3*n+1)-dir, n, 1);
        }else if(dir>=(3*n+1)&&dir<=(4*n)){//아래
            return new RoundInfo(1, (4*n+1)-dir, 2);
        }
        return new RoundInfo(0,0,0);
    }
    public static class RoundInfo{
        public int x, y, dir;
        public RoundInfo(int x, int y, int dir){
            this.x=x;
            this.y=y;
            this.dir=dir;
        }
    }
    public static boolean isInRange(int nx, int ny){
        return (nx>=1&&ny>=1&&nx<=n&&ny<=n);
    }
}
