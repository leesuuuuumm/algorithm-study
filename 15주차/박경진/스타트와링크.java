import java.util.Scanner;

public class Main {
    static boolean[] team;
    static int[][] value;
    static int N;
    static int min;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //사람 수
        value = new int[N][N]; //조합별 능력치
        team = new boolean[N]; //소속 팀 구분
        min = Integer.MAX_VALUE; //최소 차

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                value[i][j] = sc.nextInt();
            }
        }

        setTeam(0,0);
        System.out.println(min);
    }

    static void setTeam(int x, int mid){
        if(mid == N / 2){
            findMin();
            return;
        }

        for(int i = x; i < N; i++){
            team[i] = true;
            setTeam(i+1,mid+1);
            team[i] = false;
        }
    }

    static void findMin(){
        int start = 0; //start팀 능력치
        int link = 0; //link팀 능력치

        for(int w = 0; w < N-1; w++){
            for(int z = w+1; z < N; z++){
                if(team[w] && team[z]){
                    start += (value[w][z] + value[z][w]);
                }
                else if((!team[w]) && (!team[z])){
                    link += (value[w][z] + value[z][w]);
                }
            }
        }

        int minus = Math.abs(start - link);
        min = Math.min(min, minus);
    }
}
