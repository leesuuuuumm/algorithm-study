import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static boolean[] visit;
    static int N,K;
    static int minute;

    public static class Pair{
        int x;
        int check;
        Pair(int x, int check){
            this.x = x;
            this.check = check;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //수빈이의 위치
        K = sc.nextInt(); //동생의 위치
        visit = new boolean[100001];
        minute = Integer.MAX_VALUE;

        bfs(N);

        System.out.println(minute);
    }

    static void bfs(int x){
        Queue<Pair> q = new LinkedList<>();
        if(x == K) {
            minute = 0;
            return;
        }
        else q.add(new Pair(x,0));

        while(!q.isEmpty()){
            Pair nq = q.poll();
            int now = nq.x;
            int check = nq.check;
            visit[now] = true;

            if(now == K){
                minute = Math.min(minute, check);
                continue;
            }

            int down = now - 1;
            if(down >= 0 && down <= 100000 && visit[down] == false) {
                q.add(new Pair(down,check + 1));
            }

            int up = now + 1;
            if(up >= 0 && up <= 100000 && visit[up] == false) {
                q.add(new Pair(up, check + 1));
            }

            int jump = now * 2;
            if(jump >= 0 && jump <= 100000 && visit[jump] == false) {
                q.add(new Pair(jump,check));
            }
        }
    }
}
