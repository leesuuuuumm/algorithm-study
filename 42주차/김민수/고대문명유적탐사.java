import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 고대문명유적탐사 {
    static int[][] map;
    static int value;
    static int[] dx={-1,0,1,0};
    static int[] dy={0,-1,0,1};
    static boolean[][] vis=new boolean[5][5];

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int k=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        map=new int[5][5];
        Queue<Integer> wall=new ArrayDeque<>();

        for(int i=0;i<5;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            wall.add(Integer.parseInt(st.nextToken()));
        }
        for(int i=0;i<k;i++){
            value=0;
            searchMaxVal(wall);
            if(value==0)
                return;
            else System.out.print(value+" ");
        }

    }
    public static void searchMaxVal(Queue<Integer> wall){//탐사진행 -> 가장 max value가 되는 유물 제거
        //90, 180, 270도 회전 && 중심점 이동하면서 계산
        ArrayList<ArrayList<int[]>> relicList=new ArrayList<>();//유물
        int idx=0;
        PriorityQueue<RotateNode> pq=new PriorityQueue<>();
        for(int i=1;i<=3;i++){ //중심점 좌표
            for(int j=1;j<=3;j++){
                for(int k=90;k<=270;k+=90){
                    int[][] rotateMap=rotate(map, k, i, j);
                    ArrayList<int[]> value=calculate(rotateMap);
                    if(value.size()>0) {
                        pq.add(new RotateNode(value.size(), k, i, j, idx));
                    }
                    relicList.add(value);
                    idx+=1;
                }
            }
        }
        if(pq.isEmpty()){
            value=0;
            return;
        }
        RotateNode node=pq.poll();
        ArrayList<int[]> relic=relicList.get(node.idx);
        relic.sort(new RelicComparator());
        value+=relic.size();
        map= rotate(map,node.angle,node.row,node.col);

        // 유물 대체
        for(int[] r:relic){
            if(wall.isEmpty())
                break;
            map[r[0]][r[1]]=wall.poll();
        }

        //유물 연쇄 획득
        ArrayList<int[]> newRelic=calculate(map);
        newRelic.sort(new RelicComparator());

        while(!newRelic.isEmpty()){
            for(int[] r:newRelic){
                if(wall.isEmpty()){
                    value=0;
                    return;
                }
                map[r[0]][r[1]]=wall.poll();
            }
            value+=newRelic.size();
            newRelic=calculate(map);
            newRelic.sort(new RelicComparator());
        }
    }
    public static int[][] rotate(int[][] map, int angle, int cR, int cC) {
        int[][] result = copyMap(map);
        int startR = cR - 1;
        int endR = cR + 1;
        int startC = cC - 1;
        int endC = cC + 1;

        for (int i = startR; i <= endR; i++) {
            for (int j = startC; j <= endC; j++) {
                int oR=i-startR;
                int oC=j-startC;

                int rR=0;
                int rC=0;

                if(angle==90){
                    rR=oC;
                    rC=3-oR-1;
                }else if(angle==180){
                    rR=3-oR-1;
                    rC=3-oC-1;
                }else if(angle==270){
                    rR=3-oC-1;
                    rC=oR;
                }
                result[rR+startR][rC+startC]=map[i][j];
            }
        }
        return result;
    }

    public static ArrayList<int[]> calculate(int[][] map){//유물 위치 idx 반환
        ArrayList<int[]> result=new ArrayList<>();
        vis=new boolean[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(!vis[i][j]){
                    ArrayList<int[]> lis=bfs(map, i,j);
                    if(!lis.isEmpty()){
                        result.addAll(lis);
                    }
                }
            }
        }
        return result;
    }
    public static ArrayList<int[]> bfs(int[][] map, int startR, int startC){
        ArrayList<int[]> result=new ArrayList<>();
        int num=map[startR][startC];
        Queue<bfsNode> que=new ArrayDeque<>();
        que.add(new bfsNode(startR, startC, 1, num));
        boolean[][] visited=new boolean[5][5];
        visited[startR][startC]=true;
        int cnt=1;
        while(!que.isEmpty()){
            bfsNode pos=que.poll();
            for(int i=0;i<4;i++){
                int nR=pos.row+dx[i];
                int nC=pos.col+dy[i];

                if(nR<0||nC<0||nR>=5||nC>=5||visited[nR][nC])
                    continue;
                if(map[nR][nC]==pos.val){
                    que.add(new bfsNode(nR,nC,pos.count+1,pos.val));
                    visited[nR][nC]=true;
                    cnt++;
                }
            }
        }
        if(cnt>=3){
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(visited[i][j]){
                        vis[i][j]=true;
                        result.add(new int[]{i,j});
                    }
                }
            }
            return result;
        }else{
           return new ArrayList<>();
        }
    }

    public static class bfsNode{
        public int row, col, count, val;

        public bfsNode(int row, int col, int count, int val){
            this.row=row;
            this.col=col;
            this.count=count;
            this.val=val;
        }
    }

    public static int[][] copyMap(int[][] map){
        int[][] temp=new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                temp[i][j]=map[i][j];
            }
        }
        return temp;
    }

    static class RelicComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            // 열 번호가 작은 순으로 정렬
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else {
                // 행 번호가 큰 순으로 정렬
                return o2[0] - o1[0];
            }
        }
    }
    public static class RotateNode implements Comparable<RotateNode>{
        public int value;
        public int angle;
        public int row;
        public int col;
        public int idx;
        public RotateNode(int value, int angle, int row, int col, int idx){
            this.value=value;
            this.angle=angle;
            this.row=row;
            this.col=col;
            this.idx=idx;
        }

        @Override
        public int compareTo(RotateNode o){
            if (this.value == o.value) {
                if (this.angle == o.angle) {
                    if (this.col == o.col) {
                        return this.row - o.row; // 4. 행을 기준으로 오름차순
                    }

                    return this.col - o.col; // 3. 열을 기준으로 오름차순
                }

                return this.angle - o.angle; // 2. 각도 기준으로 오름차순
            }

            return o.value - this.value; // 1. 점수 기준으로 내림차순
        }
    }
}
