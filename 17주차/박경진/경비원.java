import java.util.*;

public class Main {
    static int N, M;
    static int X, Y;

    static class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt(); //가로 길이
        N = sc.nextInt(); //세로 길이
        int S = sc.nextInt(); //상점의 개수
        Queue<Pair> store = new LinkedList<>(); //상점 방향 및 위치 저장
        int ans = 0;

        for(int i = 0; i < S; i++){
            store.add(new Pair(sc.nextInt(), sc.nextInt()));
        }

        X = sc.nextInt(); //동근이의 방향
        Y = sc.nextInt(); //동근이의 위치

        while (!store.isEmpty()){
            Pair p = store.poll();
            ans += findRoute(p.x, p.y);
        }

        System.out.println(ans);
    }

    static int findRoute(int sx, int sy){
        int answer = 0;

        //동근이와 가게가 같은 방향에 있음, 4가지
        if(sx - X == 0){
            answer += Math.abs(Y-sy);
            return answer;
        }
        else if(Math.abs(sx - X) == 1 && (Math.max(sx, X) != 3)){ //북과 남, 서와 동의 조합일 때, 4가지
            if(Math.max(sx, X) == 2) { //북과 남의 조합일 때
                //세로와 가로 연산 중 더 작은 값을 더함
                answer = N + Math.min(Math.abs(sy + Y), Math.abs(M * 2 - sy - Y));
            }
            else{ //동과 서의 조합일 때
                //가로와 세로 연산 중 더 작은 값을 더함
                answer = M + Math.min(Math.abs(sy+Y), Math.abs(N*2-sy-Y));
            }
            return answer;
        }

        //남과 서, 남과 동, 북과 동, 북과 서의 조합일 때, 8가지
        //남과 서의 조합일 때
        if(Math.abs(sx-X) == 1){
            int southy = 0, westy = 0;

            if(sx == 2){ //가게가 남쪽일 때
                southy = sy;
                westy = Y;
            }
            else { //동근이가 남쪽일 때
                southy = Y;
                westy = sy;
            }

            answer = (southy + (N-westy));
            return answer;
        }

        //남과 동의 조합일 때
        if(Math.min(sx, X) == 2){
            int southy = 0, easty = 0;

            if(sx == 2){ //가게가 남쪽일 때
                southy = sy;
                easty = Y;
            }
            else { //동근이가 남쪽일 때
                southy = Y;
                easty = sy;
            }

            answer = ((M-southy) + (N-easty));
            return answer;
        }

        //북과 동의 조합일 때
        if(Math.abs(sx-X) == 3){
            int northy = 0, easty = 0;

            if(sx == 1){ //가게가 북쪽일 때
                northy = sy;
                easty = Y;
            }
            else { //동근이가 북쪽일 때
                northy = Y;
                easty = sy;
            }

            answer = ((M-northy) + easty);
            return answer;
        }

        //북과 서의 조합일 때
        if(Math.abs(sx-X) == 2){
            int northy = 0, westy = 0;

            if(sx == 1){ //가게가 북쪽일 때
                northy = sy;
                westy = Y;
            }
            else { //동근이가 북쪽일 때
                northy = Y;
                westy = sy;
            }

            answer = (northy + westy);
            return answer;
        }

        return answer;
    }
}
