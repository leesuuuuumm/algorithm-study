import java.util.*;

public class Main {
    static ArrayDeque<Integer> nums;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); //테스트 케이스 개수

        sc.nextLine();
        for(int test_case = 0; test_case < T; test_case++){
            nums = new ArrayDeque<>(); //각 테케별 배열
            String p = sc.nextLine(); //명령어
            int n = sc.nextInt(); //배열 원소 개수
            sc.nextLine();
            String nn = sc.nextLine(); //배열 구성
            if(n == 0) {
                startCommand(p);
                continue;
            }

            StringTokenizer st = new StringTokenizer(nn, "[],");
            while(st.hasMoreTokens()){
                nums.add(Integer.parseInt(st.nextToken()));
            }

            startCommand(p);
        }
    }

    public static void startCommand(String p){
        //p는 명령어
        int c = p.length(); //명령어 개수

        int flag = 0; //odd is reverse

        for(int i = 0; i < c; i++){
            char now = p.charAt(i);
            if(now == 'R'){
                flag++;
            }
            if(now == 'D'){
                if(!nums.isEmpty()){
                    if(flag % 2 == 0) nums.pollFirst();
                    else nums.pollLast();
                }
                else {
                    System.out.println("error");
                    return;
                }
            }
        }

        if(nums.isEmpty()){
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(flag % 2 == 0){
            while(nums.size() > 1){
                sb.append(nums.pollFirst()+",");
            }
        }
        else{
            while(nums.size() > 1){
                sb.append(nums.pollLast()+",");
            }
        }
        sb.append(nums.poll()+"]");
        System.out.println(sb);
    }
}
