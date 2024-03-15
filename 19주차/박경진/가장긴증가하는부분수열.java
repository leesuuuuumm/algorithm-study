import java.util.*;

public class Main {
    static int[] memo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> A = new ArrayList<>();


        for(int i = 0; i < N; i++){
            int x = sc.nextInt();
            A.add(x);
        }

        memo = new int[A.size()];

        for(int i = 0; i < A.size(); i++)
            findMax(i,A);

        int max = 0;
        for (int i = 0; i < A.size(); i++){
            if(max < memo[i]) max = memo[i];
        }
        System.out.println(max);
    }

    public static int findMax(int start, ArrayList<Integer> A){
        if(memo[start] != 0) return memo[start];

        memo[start] = 1;
        for(int i = start-1; i >= 0; i--){
            if(A.get(i) < A.get(start)){
                memo[start] = Math.max(memo[start], findMax(i,A)+1);
            }
        }
        return memo[start];
    }
}
