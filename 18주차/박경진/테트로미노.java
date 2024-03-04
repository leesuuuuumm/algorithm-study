import java.util.*;

public class Main {
    static int N, M;
    static int answer;
    static boolean[][] visit;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //세로
        M = sc.nextInt(); //가로
        board = new int[N][M]; //종이에 쓰여진 숫자
        visit = new boolean[N][M]; // 최대값 저장
        answer = 0; //정답

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                board[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visit[i][j] = true;
                dp(i, j, board[i][j], 1);
                visit[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    static void dp(int x, int y, int sum, int cnt){
        if(cnt == 4){ //4개의 칸을 모두 선택했으면
            answer = Math.max(answer, sum); //현재합과 현재 최대값 비교
            //System.out.println(sum);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx > N-1 || nx < 0 || ny > M-1 || ny < 0){
                continue;
            }

            if(!visit[nx][ny]){
                if(cnt == 2){ //ㅗ모양 블럭을 고려
                    visit[nx][ny] = true;
                    dp(x,y,sum+board[nx][ny], cnt+1); //현재 위치에서 다시 탐색
                    visit[nx][ny] = false;
                }

                visit[nx][ny] = true;
                dp(nx,ny,sum+board[nx][ny], cnt+1);
                visit[nx][ny] = false;
            }
        }
    }
}
