import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 루돌프의반란 {
    static int N, M, P, C, D; //N: 크기, M: 턴 수, P: 산타 수, C: 루돌프 힘, D: 산타 힘
    static int[] rudolf; //루돌프 위치 : [0]: r, [1]: c
    static Santa[] santa;
    static int[] moveRow = {-1, 0, 1, 0, -1, 1, -1, 1};
    static int[] moveCol = {0, 1, 0, -1, 1, 1, -1, -1};
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        rudolf = new int[2];
        rudolf[0] = Integer.parseInt(st.nextToken());
        rudolf[1] = Integer.parseInt(st.nextToken());
        santa = new Santa[P + 1];
        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            santa[idx] = new Santa(false, false, 0, 0, row, col);
        }
        K=1;
        while (K<M+1 && !isGameOver()) {
            moveRudolf();
            moveSanta();
            K++;
            for(int i=1;i<=P;i++){
                if(!santa[i].isOut)
                    santa[i].score+=1;
            }
        }
        for (int i = 1; i <= P; i++) {
            System.out.print(santa[i].score + " ");
        }

    }

    public static boolean isGameOver() {
        for (int i = 1; i <= P; i++) {
            if (!santa[i].isOut)
                return false;
        }
        return true;
    }

    public static void moveRudolf() {
        //거리가 가장 가까운 산타 찾기
        PriorityQueue<DisSanta> pq = new PriorityQueue<>();
        for (int i = 1; i <= P; i++) {
            if (!santa[i].isOut) {
                long dist = calculateDist(rudolf[0], rudolf[1], santa[i].row, santa[i].col);
                pq.add(new DisSanta(dist, santa[i].row, santa[i].col, i));
            }
        }
        DisSanta distSanta = pq.poll();
        long minDist = Long.MAX_VALUE;
        int dir = -1;

        //가까워지는 방향찾기
        for (int i = 0; i < 8; i++) {
            int nR = rudolf[0] + moveRow[i];
            int nC = rudolf[1] + moveCol[i];
            if (isInRange(nR, nC)) {
                long dist = calculateDist(nR, nC, distSanta.row, distSanta.col);
                if (dist < minDist) {
                    minDist = dist;
                    dir = i;
                }
            }
        }

        //이동하기
        rudolf[0] = rudolf[0] + moveRow[dir];
        rudolf[1] = rudolf[1] + moveCol[dir];

        //충돌하는지 확인
        if (minDist == 0) {
            //루돌프 힘 C 추가
            santa[distSanta.idx].score += C;
            //이동해온 방향 C만큼 뒤로 밀리기
            int sNewRow = santa[distSanta.idx].row + C * moveRow[dir];
            int sNewCol = santa[distSanta.idx].col + C * moveCol[dir];

            if (isInRange(sNewRow, sNewCol)) { //게임판 안이면
                //기절
                santa[distSanta.idx].isFaint = true;
                santa[distSanta.idx].faintNum = K + 1;

                //다른 산타와 부딪히는지
                int idx=getCrashedSantaIdx(sNewRow, sNewCol, distSanta.idx);

                //이동
                santa[distSanta.idx].row=sNewRow;
                santa[distSanta.idx].col=sNewCol;

                if (idx> 0) {
                    interact(sNewRow, sNewCol, idx, dir);
                }
            } else {//게임판 밖이면 탈락
                santa[distSanta.idx].isOut = true;
            }
        }
    }

    public static int getCrashedSantaIdx(int row, int col, int mySelf){
        for(int i=1;i<=P;i++){
            if(i==mySelf)
                continue;
            if(!santa[i].isOut){
               if(santa[i].row==row&&santa[i].col==col)
                   return i;
            }
        }
        return -1;
    }

    public static void moveSanta() {
        for(int i=1;i<=P;i++){
            if(santa[i].isOut||(santa[i].isFaint&&santa[i].faintNum>=K))
                continue;

            santa[i].isFaint=false;
            long minDist=calculateDist(santa[i].row, santa[i].col, rudolf[0], rudolf[1]);
            PriorityQueue<DisRudolf> pq=new PriorityQueue<>();
            for(int dir=0;dir<4;dir++){
                int sNewRow=santa[i].row+moveRow[dir];
                int sNewCol=santa[i].col+moveCol[dir];
                if(isInRange(sNewRow, sNewCol)&&getCrashedSantaIdx(sNewRow, sNewCol, i)<0){//산타는 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없다.
                    long dist=calculateDist(sNewRow, sNewCol, rudolf[0], rudolf[1]);
                    pq.add(new DisRudolf(dist, dir));
                }
            }
            if(!pq.isEmpty()){//움직일 수 있는 칸이 있다면
                DisRudolf disRudolf=pq.poll();
                if(minDist>disRudolf.dist){ // 만약 루돌프로부터 가까워질 수 있는 방법이 있다면
                    //산타가 이동
                    if(disRudolf.dist==0){
                        //충돌이 된다면
                        //점수 추가
                        santa[i].score+=D;

                        int oppositeDir=getOppositeDir(disRudolf.dir);

                        //이동해온 방향 D만큼 뒤로 밀리기
                        int sNewRow=rudolf[0];
                        int sNewCol=rudolf[1];

                        int crashedRow = sNewRow + D * moveRow[oppositeDir];
                        int crashedCol = sNewCol + D * moveCol[oppositeDir];

                        if (isInRange(crashedRow, crashedCol)) { //게임판 안이면
                            //기절
                            santa[i].isFaint = true;
                            santa[i].faintNum = K + 1;

                            //다른 산타와 부딪히는지
                            int idx=getCrashedSantaIdx(crashedRow, crashedCol, i);

                            //이동
                            santa[i].row=crashedRow;
                            santa[i].col=crashedCol;
                            if (idx > 0) {
                                interact(crashedRow, crashedCol, idx, oppositeDir);
                            }
                        } else {//게임판 밖이면 탈락
                            santa[i].isOut = true;
                        }

                    }else{
                        //산타 이동
                        int sNewRow=santa[i].row+moveRow[disRudolf.dir];
                        int sNewCol=santa[i].col+moveCol[disRudolf.dir];

                        santa[i].row=sNewRow;
                        santa[i].col=sNewCol;
                    }
                }
            }

        }
    }
    public static int getOppositeDir(int dir){
        if(dir==0){ //상
            return 2; //하
        }else if(dir==1){ //우
            return 3; //좌
        }else if(dir==2){ //하
            return 0;
        }else{ //좌
            return 1; //우
        }
    }

    public static void interact(int row, int col, int crashedSantaIdx, int dir) {
        int nR=row+moveRow[dir];
        int nC=col+moveCol[dir];
        if(isInRange(nR,nC)){
            int idx=getCrashedSantaIdx(nR, nC, crashedSantaIdx);
            santa[crashedSantaIdx].row=nR;
            santa[crashedSantaIdx].col=nC;
            if(idx>0){
                interact(nR, nC, idx, dir);
            }
        }else{
            santa[crashedSantaIdx].isOut=true;
        }
    }

    public static boolean isInRange(int row, int col) {
        return row >= 1 && col >= 1 && row <= N && col <= N;
    }

    public static class DisRudolf implements Comparable<DisRudolf>{
        public long dist;
        public int dir;

        public DisRudolf(long dist, int dir){
            this.dist=dist;
            this.dir=dir;
        }

        @Override
        public int compareTo(DisRudolf o) {
            if(this.dist==o.dist){
                return this.dir-o.dir;
            }
            return (int) (this.dist-o.dist);
        }
    }

    public static class DisSanta implements Comparable<DisSanta> {
        public long dist;
        public int row;
        public int col;
        public int idx;

        public DisSanta(long dist, int row, int col, int idx) {
            this.dist = dist;
            this.row = row;
            this.col = col;
            this.idx = idx;
        }

        @Override
        public int compareTo(DisSanta o) {
            if (this.dist == o.dist) {
                if (this.row == o.row) {
                    return o.col - this.col; //3. c좌표 큰 순
                }
                return o.row - this.row; //2. r좌표 큰 순
            }
            return (int) (this.dist - o.dist);//1. 거리가 가장 가까운
        }
    }

    public static long calculateDist(int r1, int c1, int r2, int c2) {
        return (long) (r1 - r2) * (r1 - r2) + (long) (c1 - c2) * (c1 - c2);
    }

    public static class Santa {
        public boolean isOut;
        public boolean isFaint;
        public int faintNum;
        public int score;
        public int row;
        public int col;

        public Santa(boolean isOut, boolean isFaint, int faintNum, int score, int row, int col) {
            this.isOut = isOut;
            this.isFaint = isFaint;
            this.faintNum = faintNum;
            this.score = score;
            this.row = row;
            this.col = col;
        }
    }
}
