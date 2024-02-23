import java.util.*;

public class Main {
    static int L, C;
    static boolean[] choose;
    static char[] character;
    static char[] keys;
    static ArrayList<String> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt(); //암호에 사용된 문자 수
        C = sc.nextInt(); //주어질 문자 수
        character = new char[C]; //주어진 문자 저장
        choose = new boolean[C]; //어떤 문자가 암호 조합에 사용됐는지
        keys = new char[L]; //암호 조합에 사용된 문자
        answer = new ArrayList<>(); //정답 암호 저장

        sc.nextLine(); //버퍼 제거

        String s = sc.nextLine(); //주어진 문자들
        for(int i = 0; i < C; i++){
            character[i] = s.charAt(i*2);
        }

        Arrays.sort(character); //주어진 문자들을 정렬
        makeKey(0, 0); //암호 조합
    }

    static void makeKey(int x, int cnt){
        if(cnt == L){ //암호 조합 끝
            if(check()){
                for (int i = 0; i < L; i++){
                    System.out.print(keys[i]);
                }
                System.out.println();
            }
            return;
        }

        for(int i = x; i < C; i++){
            keys[cnt] = character[i];
            makeKey(i+1, cnt+1);
        }
    }

    static boolean check(){
        int a = 0; //자음
        int b = 0; //모음

        for(int i = 0; i < L; i++){
            if(keys[i] == 'a' || keys[i] == 'e' || keys[i] == 'i' || keys[i] == 'o' || keys[i] == 'u'){
                b++;
            }
            else a++;
        }

        if(a >= 2 && b >= 1){
            return true;
        }
        else
            return false;
    }
}
