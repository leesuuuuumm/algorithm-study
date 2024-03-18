import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int answer = 0;

        //이동거리는 증가했다가 1로 감소해야함
        //최대 이동거리는 X와 Y 사이의 거리의 루트값에서 소수점을 제거한 것과 같다
        //max의 값은 두번씩 중복된다

        for(int test_case = 0; test_case < T; test_case++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = y - x;
            int max = (int)Math.sqrt(d);

            if(max == Math.sqrt(d)){ //max와 거리의 루트값이 같으면 max는 하나
                //하나이면 이동횟수 max*2-1
                answer = max * 2 -1;
            }
            else if(d <= max * max + max){
                //max의 제곱과 max의 제곱+max 사이의 값이면
                answer = max * 2;
            }
            else {
                answer = max * 2 + 1;
            }
            System.out.println(answer);
        }
    }
}
