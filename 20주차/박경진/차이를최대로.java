import java.util.*;

public class Main {
    static int N;
    static int answer;
    static int[] nums, temp;
    static boolean[] visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        answer = 0;
        nums = new int[N];
        temp = new int[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++)
            nums[i] = sc.nextInt();

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int cnt){
        if(cnt == N){
            int s = 0;
            for(int i = 0; i < N-1; i++){
                s += Math.abs(temp[i] - temp[i+1]);
            }
            answer = Math.max(answer, s);
            return;
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                temp[cnt] = nums[i];
                visited[i] = true;
                dfs(cnt+1);
                visited[i] = false;
            }
        }
    }
}
