import java.util.*;

public class Main {
    static int N, K, L;
    static int[][] board;
    static Queue<Pair> command;
    static ArrayList<Loc> snake;
    static boolean[][] visit;

    static class Pair{
        int x;
        char c;
        Pair(int x, char c){
            this.x = x;
            this.c = c;
        }
    }

    static class Loc{
        int x;
        int y;
        int d;
        Loc(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //보드의 크기
        K = sc.nextInt(); //사과의 개수
        board = new int[N][N]; //보드
        visit = new boolean[N][N]; //뱀이 있는지
        command = new LinkedList<>(); //명령어 저장
        snake = new ArrayList<>(); //뱀의 머리와 꼬리
        snake.add(new Loc(0,0, 2)); //처음 위치와 바라보고 있는 방향

        for(int i = 0; i < K; i++){
            board[sc.nextInt() - 1][sc.nextInt()-1] = 1; //사과가 있음
        }

        L = sc.nextInt(); //명령어의 개수
        sc.nextLine();
        for(int i = 0; i < L; i++){
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s, " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            command.add(new Pair(X,C)); //X초 후 C 방향으로 회전
        }

        int answer = startGame();
        System.out.println(answer);
    }

    static int startGame(){
        int time = 1; //게임 시작 후 몇 시간이 경과했는지

        while(!command.isEmpty()){ //명령이 없을 때까지, 뱀이 닿았거나 벽에 부딪히면 종료
            Pair com = command.poll(); //명령어 꺼내기
            int X = com.x; //게임 시작 후 몇 초 후 명령어 실행
            char C = com.c; //L이면 왼쪽 D이면 오른쪽
            //System.out.println("command is " + X + " " + C);

            for(; time <= X; time++){
                Loc now = snake.get(0); //뱀의 머리
                int x = now.x; //뱀의 행
                int y = now.y; //뱀의 열
                int d = now.d; //뱀이 바라보고 있는 방향
                //System.out.println("time is " + time);
                int nextx = 0;
                int nexty = 0;
                if(d == 1){ //뱀의 머리가 북쪽을 바라보면
                    nextx = x - 1; //위로 한칸
                    nexty = y;
                }
                else if(d == 2){ //뱀의 머리가 동쪽을 바라보면
                    nextx = x;
                    nexty = y + 1; //오른쪽으로 한 칸
                }
                else if(d == 3){ //뱀의 머리가 남쪽을 바라보면
                    nextx = x + 1; //아래로 한칸
                    nexty = y;
                }
                else if(d == 4){ //뱀의 머리가 서쪽을 바라보면
                    nextx = x;
                    nexty = y - 1; //왼쪽으로 한 칸
                }
                //System.out.println("now is " + x +" " + y + " next is " + nextx + " " + nexty);
                //System.out.println("snake size is " + snake.size() +"visit is " + visit[nextx][nexty]);

                //다음에 갈 칸이 맵을 벗어나면
                if(nextx > N-1 || nextx < 0 || nexty > N-1 || nexty < 0){
                    return time;
                }
                //뱀이 있는 칸이면
                if(visit[nextx][nexty]){
                    return time;
                }

                //맵을 벗어나지도 뱀이 있는 칸도 아니면
                //사과가 있는 칸이면
                if(board[nextx][nexty] == 1){
                    board[nextx][nexty] = 0; //사과를 먹고
                    snake.add(0, new Loc(nextx,nexty,d)); //뱀의 머리를 옮김
                    visit[nextx][nexty] = true; //이동한 칸도 뱀이 위치
                }
                //사과가 없는 칸이면
                else{
                    Loc tail = snake.get(snake.size()-1); //꼬리의 위치
                    int tailx = tail.x;
                    int taily = tail.y;
                    snake.remove(snake.size()-1); //꼬리를 치우고
                    snake.add(0,new Loc(nextx,nexty,d)); //뱀의 머리를 옮김
                    visit[tailx][taily] = false; //꼬리가 있던 칸을 비우고
                    visit[nextx][nexty] = true; //이동한 칸에 머리를 위치
                }
            }

            int nextx = 0, nexty = 0, nextd = 0;
            if(C == 'D'){ //오른쪽이면
                Loc head = snake.get(0);
                int headx = head.x;
                int heady = head.y;
                int headd = head.d;

                if(headd == 1){ //뱀이 북쪽을 바라보고 있으면
                    nextx = headx;
                    nexty = heady + 1; //오른쪽으로 이동
                    nextd = 2;
                }
                else if(headd == 2){ //뱀이 동쪽을 바라보고 있으면
                    nextx = headx + 1; //아래로 이동
                    nexty = heady;
                    nextd = 3;
                }
                else if(headd == 3){ //뱀이 남쪽을 바라보고 있으면
                    nextx = headx;
                    nexty = heady - 1; //왼쪽으로 이동
                    nextd = 4;
                }
                else if(headd == 4){ //뱀이 서쪽을 바라보고 있으면
                    nextx = headx - 1; //위로 이동
                    nexty = heady;
                    nextd = 1;
                }
            }
            else if(C == 'L'){ //왼쪽이면
                Loc head = snake.get(0);
                int headx = head.x;
                int heady = head.y;
                int headd = head.d;


                if(headd == 1){ //뱀이 북쪽을 바라보고 있으면
                    nextx = headx;
                    nexty = heady - 1; //왼쪽으로 이동
                    nextd = 4;
                }
                else if(headd == 2){ //뱀이 동쪽을 바라보고 있으면
                    nextx = headx - 1; //위로 이동
                    nexty = heady;
                    nextd = 1;
                }
                else if(headd == 3){ //뱀이 남쪽을 바라보고 있으면
                    nextx = headx;
                    nexty = heady + 1; //오른쪽으로 이동
                    nextd = 2;
                }
                else if(headd == 4){ //뱀이 서쪽을 바라보고 있으면
                    nextx = headx + 1; //아래로 이동
                    nexty = heady;
                    nextd = 3;
                }
            }

            if(nextx < 0 || nextx > N-1 || nexty < 0 || nexty > N-1){
                return time;
            }
            else if(visit[nextx][nexty]){
                return time;
            }

            //맵을 벗어나지도 뱀이 있는 칸도 아니면
            //사과가 있는 칸이면
            if(board[nextx][nexty] == 1){
                board[nextx][nexty] = 0; //사과를 먹고
                snake.add(0, new Loc(nextx,nexty,nextd)); //뱀의 머리를 옮김
                visit[nextx][nexty] = true; //이동한 칸도 뱀이 위치
            }
            //사과가 없는 칸이면
            else{
                Loc tail = snake.get(snake.size()-1); //꼬리의 위치
                int tailx = tail.x;
                int taily = tail.y;
                snake.remove(snake.size()-1); //꼬리를 치우고
                snake.add(0,new Loc(nextx,nexty,nextd)); //뱀의 머리를 옮김
                visit[tailx][taily] = false; //꼬리가 있던 칸을 비우고
                visit[nextx][nexty] = true; //이동한 칸에 머리를 위치
            }
            time++;
        }

        while(true) {
            Loc now = snake.get(0); //뱀의 머리
            int x = now.x; //뱀의 행
            int y = now.y; //뱀의 열
            int d = now.d; //뱀이 바라보고 있는 방향
            //System.out.println("time is " + time);
            int nextx = 0;
            int nexty = 0;
            if (d == 1) { //뱀의 머리가 북쪽을 바라보면
                nextx = x - 1; //위로 한칸
                nexty = y;
            } else if (d == 2) { //뱀의 머리가 동쪽을 바라보면
                nextx = x;
                nexty = y + 1; //오른쪽으로 한 칸
            } else if (d == 3) { //뱀의 머리가 남쪽을 바라보면
                nextx = x + 1; //아래로 한칸
                nexty = y;
            } else if (d == 4) { //뱀의 머리가 서쪽을 바라보면
                nextx = x;
                nexty = y - 1; //왼쪽으로 한 칸
            }
            //System.out.println("now is " + x + " " + y + " next is " + nextx + " " + nexty);

            //다음에 갈 칸이 맵을 벗어나면
            if (nextx > N - 1 || nextx < 0 || nexty > N - 1 || nexty < 0) {
                return time;
            }
            //뱀이 있는 칸이면
            if (visit[nextx][nexty]) {
                return time;
            }

            //맵을 벗어나지도 뱀이 있는 칸도 아니면
            //사과가 있는 칸이면
            if(board[nextx][nexty] == 1){
                board[nextx][nexty] = 0; //사과를 먹고
                snake.add(0, new Loc(nextx,nexty,d)); //뱀의 머리를 옮김
                visit[nextx][nexty] = true; //이동한 칸도 뱀이 위치
            }
            //사과가 없는 칸이면
            else{
                Loc tail = snake.get(snake.size()-1); //꼬리의 위치
                int tailx = tail.x;
                int taily = tail.y;
                snake.remove(snake.size()-1); //꼬리를 치우고
                snake.add(0,new Loc(nextx,nexty,d)); //뱀의 머리를 옮김
                visit[tailx][taily] = false; //꼬리가 있던 칸을 비우고
                visit[nextx][nexty] = true; //이동한 칸에 머리를 위치
            }
            time++;
        }
    }
}
