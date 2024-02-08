import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node{
        int x;
        int y;
        int cnt;
        boolean wall;

        public Node(int x, int y, boolean wall, int cnt){
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //행
        M = sc.nextInt(); //열
        map = new int[N][M]; //맵
        visit = new boolean[N][M][2]; //방문여부, 벽을 부쉈는지 안 부쉈는지를 구분하기 위해 3차원으로

        sc.nextLine();

        for(int i = 0; i < N; i++){
            String s = sc.nextLine(); //한줄씩 받아와서
            for(int j = 0; j < M; j++){
                map[i][j] = Character.getNumericValue(s.charAt(j)); //벽의 유무 저장
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0,0,false,1));
        visit[0][0][0] = true;

        while(!q.isEmpty()){
            Node n = q.poll();

            if(n.x == N - 1 && n.y == M - 1){ //탐색종료
                return n.cnt;
            }

            for(int t = 0; t < 4; t++){
                int nx = n.x + dx[t];
                int ny = n.y + dy[t];

                if(nx < 0 || nx > N-1 || ny < 0 || ny > M-1){
                    continue;
                }

                if(n.wall){
                    if(map[nx][ny] == 0 && !visit[nx][ny][1]){
                        visit[nx][ny][1] = true; //방문처리
                        q.add(new Node(nx,ny,true,n.cnt+1));
                    }
                }
                else{
                    if(map[nx][ny] == 1){
                        visit[nx][ny][1] = true;
                        q.add(new Node(nx,ny,true,n.cnt+1));
                    }
                    else if(!visit[nx][ny][0]){
                        visit[nx][ny][0] = true;
                        q.add(new Node(nx,ny,false,n.cnt+1));
                    }
                }
            }
        }

        return -1;
    }
}
