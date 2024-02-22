import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //사람 수
        int[] list = new int[N+1]; //각 사람마다 앞에 키 큰 사람이 몇 명 있는지

        for(int i = 1; i < N+1; i++){
            list[i] = sc.nextInt();
        }

        ArrayList<Integer> ans = new ArrayList<>(); //사람들 줄세우기

        for(int i = N; i > 0; i--){ //키 큰 사람부터 탐색시작
            ans.add(list[i], i);
        }

        for(int i = 0; i < N; i++){
            System.out.print(ans.get(i) + " ");
        }
    }
}
