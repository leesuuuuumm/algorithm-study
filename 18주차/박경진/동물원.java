import java.util.*;

public class Main {
    static int N;
    static int[][] cage;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //우리의 세로 크기
        int answer = 0;
        cage = new int[N][3]; //열이 0일 땐 비워져 있음, 1일 땐 첫 번째 칸, 2일 땐 두 번째 칸
        cage[0][0] = cage[0][1] = cage[0][2] = 1; //첫 번째 줄 설정

        answer = dp();

        System.out.println(answer);
    }

    static int dp(){

        for (int i = 1; i < N; i++){
            cage[i][0] = (cage[i-1][0] + cage[i-1][1] + cage[i-1][2]) % 9901;
            cage[i][1] = (cage[i-1][0] + cage[i-1][2]) % 9901; //맞닿는 가로, 세로에 배치 불가
            cage[i][2] = (cage[i-1][0] + cage[i-1][1]) % 9901; //맞닿는 가로, 세로에 배치 불가
        }

        return (cage[N-1][0] + cage[N-1][1] + cage[N-1][2]) % 9901;
    }
}
