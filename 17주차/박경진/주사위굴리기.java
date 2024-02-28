import java.util.*;

public class Main {
    static int N, M;
    static int x, y;
    static int K;
    static int[][] map;
    static Queue<Integer> command;
    static int[] dice;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //세로
        M = sc.nextInt(); //가로
        x = sc.nextInt(); //주사위 행
        y = sc.nextInt(); //주사위 열
        K = sc.nextInt(); //명령어 개수
        map = new int[N][M];
        command = new LinkedList<>(); //동: 1, 서: 2, 북: 3, 남:4
        dice = new int[6]; //북-위-남-바닥-서-동

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] =  sc.nextInt();
            }
        }

        for(int i = 0; i < K; i++){
            command.add(sc.nextInt());
        }

        roll();
    }

    static void roll(){
          while(!command.isEmpty()) {
              int c = command.poll(); //현재 명령어
              int nowx = 0;
              int nowy = 0;

              //명령어가 동쪽으로 이동이면, 주사위 동쪽을 바닥으로
              if (c == 1) {
                  nowx = x;
                  nowy = y + 1;
                  if (nowy > M - 1) { //맵을 벗어나면
                      continue;
                  }
                  else{ //주사위 굴리기
                      int e = dice[5], w = dice[4], b = dice[3], t = dice[1];
                      //동->바닥
                      dice[3] = e;
                      //바닥->서
                      dice[4] = b;
                      //서->위
                      dice[1] = w;
                      //위->동
                      dice[5] = t;
                  }
              }

              //명령어가 서쪽으로 이동이면, 주사위 서쪽을 바닥으로
              if (c == 2) {
                  nowx = x;
                  nowy = y - 1;
                  if (nowy < 0) { //맵을 벗어나면
                      continue;
                  }
                  else{ //주사위 굴리기
                      //북-위-남-바닥-서-동
                      int e = dice[5], w = dice[4], b = dice[3], t = dice[1];
                      //서->바닥
                      dice[3] = w;
                      //위->서
                      dice[4] = t;
                      //동->위
                      dice[1] = e;
                      //바닥->동
                      dice[5] = b;
                  }
              }

              //명령어가 북쪽으로 이동이면, 주사위 북쪽을 바닥으로
              if (c == 3) {
                  nowx = x - 1;
                  nowy = y;
                  if (nowx < 0) { //맵을 벗어나면
                      continue;
                  }
                  else{ //주사위 굴리기
                      //북-위-남-바닥-서-동
                      int n = dice[0], t = dice[1], s = dice[2], b = dice[3];
                      //북->바닥
                      dice[3] = n;
                      //남->위
                      dice[1] = s;
                      //바닥->남
                      dice[2] = b;
                      //위->북
                      dice[0] = t;
                  }
              }

              //명령어가 남쪽으로 이동이면, 주사위 남쪽을 바닥으로
              if (c == 4) {
                  nowx = x + 1;
                  nowy = y;
                  if (nowx > N - 1) { //맵을 벗어나면
                      continue;
                  }
                  else{ //주사위 굴리기
                      //북-위-남-바닥-서-동
                      int n = dice[0], t = dice[1], s = dice[2], b = dice[3];
                      //남->바닥
                      dice[3] = s;
                      //북->위
                      dice[1] = n;
                      //위->남
                      dice[2] = t;
                      //바닥->북
                      dice[0] = b;
                  }
              }

              if (map[nowx][nowy] == 0) { //맵의 숫자가 0이면
                  map[nowx][nowy] = dice[3]; //주사위의 숫자를 복사
              } else { //맵의 숫자가 0이 아니면
                  dice[3] = map[nowx][nowy]; //주사위 바닥에 맵의 숫자를 복사
                  map[nowx][nowy] = 0; //맵의 숫자를 0으로
              }

              //주사위 위치 업데이트
              x = nowx;
              y = nowy;

              System.out.println(dice[1]);
          }
    }
}
