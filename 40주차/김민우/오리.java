
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] duckCnt = new int[s.length()];
        int max_ducks = 0; // 동시에 운 오리의 최대 수

        int ducks = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = "quack".indexOf(c);

            // 만약 'q'가 시작한다면, 오리가 등장했다는 것이니 오리의 수를 증가시킨다.
            if (index == 0) {
                duckCnt[index]++;
                ducks++;
                max_ducks = Math.max(ducks, max_ducks);
            } else {
                // 'u''a''c''k'인 경우
                if (duckCnt[index - 1] == 0) {
                    // 이전 문자가 없으면 잘못 된 패턴
                    System.out.println(-1);
                    return;
                }

                duckCnt[index-1]--; // 이전 오리는 사용되었으니 제거
                duckCnt[index]++;

                if (index == 4) {
                    ducks--;
                }

            }
        }

        // 모든 문자 처리 후 확인
        for (int i = 0; i < 4; i++) {
            // 'q','u','a','c' 중 하나라도 남아있으면 잘못된 패턴.
            // 모든 "quack"이 완성되었다면 이 문자들의 개수는 0이어야 한다.
            // 남아있다는 것은 불완전한 오리 소리가 있다는 뜻.
            if (duckCnt[i] != 0) {
                System.out.println(-1);
                return;
            }
        }

        // 결과 출력
        // max_ducks가 0이면 유효한 "quack" 패턴이 하나도 없었다는 뜻.
        // 그렇지 않으면 동시에 울었던 최대 오리의 수를 출력.
        System.out.println(max_ducks == 0 ? -1 : max_ducks);
    }

}
