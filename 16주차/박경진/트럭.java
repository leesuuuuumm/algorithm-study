import java.util.*;

public class Main {
    static int n, w, L;
    static ArrayList<Integer>  weight;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //트럭의 수
        w = sc.nextInt(); //다리의 길이
        L = sc.nextInt(); //다리의 최대 하중
        weight = new ArrayList<>();

        for(int i = 0; i < n; i++){
            weight.add(sc.nextInt());
        }

        int answer = 0;
        int sum = 0;

        Queue<Integer> q = new LinkedList<Integer>();
        for(int i =0; i<w ; i++) {
            q.add(0);
        }
        while(!q.isEmpty()) {
            answer++;
            sum -=q.poll();
            if(!weight.isEmpty()) {
                if(weight.get(0) + sum<=L) {
                    sum += weight.get(0);
                    q.offer(weight.remove(0));
                }else {
                    q.offer(0);
                }
            }
        }
        System.out.println(answer);
    }
}
