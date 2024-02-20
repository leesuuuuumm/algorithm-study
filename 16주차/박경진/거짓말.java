import java.util.*;

public class Main {
    static boolean[] truth;
    static ArrayList<Integer>[] party;
    static int[] relation;
    static int N, M;
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //사람의 수
        relation = new int[N]; //사람들끼리의 친분관계 저장
        M = sc.nextInt(); //파티의 수
        party = new ArrayList[M]; //파티마다 누가 참석하는지
        int X = sc.nextInt(); //진실을 아는 사람 수
        cnt = 0; //거짓말을 할 수 있는 파티 수

        for(int i = 0; i < N; i++){
            relation[i] = i; //일단 스스로를 친분 상태로 저장
        }

        truth = new boolean[N];
        for(int i = 0; i < X; i++){
            truth[sc.nextInt()-1] = true;
        }

        for(int i = 0; i < M; i++){
            party[i] = new ArrayList<>();
            int t = sc.nextInt(); //파티에 참석하는 사람 수
            int f = sc.nextInt()-1; //파티에 첫 사람
            party[i].add(f);
            for(int j = 1; j < t; j++){
                int p = sc.nextInt() - 1;
                party[i].add(p); //파티에 참석하는 사람의 인덱스 저장
                union(party[i].get(j-1),party[i].get(j));
            }
        }
        boolean[] visited = new boolean[N];

        for(int i = 0; i < N; i++){
            if(truth[i] && !visited[i]){
                int r = find(i);

                for(int j = 0; j < N; j++){
                    if(find(j) == r){
                        truth[j] = true;
                        visited[j] = true;
                    }
                }
            }
        }

        boolean[] doLie = new boolean[M];
        for(int i = 0; i < M; i++){
            doLie[i] = true;

        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(truth[j]){
                    if(party[i].contains(j)){
                        doLie[i] = false;
                    }
                }
            }
        }

        cnt = 0;
        for(int i = 0; i < M; i++){
            if(doLie[i]) cnt++;
        }

        System.out.println(cnt);
    }
    static void union(int x, int y){ //같은 파티에 참석한 사람들 묶기
        int A = find(x);
        int B = find(y);

        if(A != B){
            relation[B] = A;
        }
    }

    static int find(int x){
        if(relation[x] == x){ //내가 집단의 대표자
            return x;
        }
        else{
            relation[x] = find(relation[x]);
            return relation[x];
        }
    }
}
