import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    /*
    음수, 0 -> 오름차순 정렬
    양수 -> 내림차순 정렬
    음수는 음수끼리 최대한 곱하고(음수끼리 곱할 수 없다면 0과 곱한다)
    양수도 양수끼리 최대한 곱한다(1과 곱하는 건 피한다)

    O(NlogN)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> plus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp <= 0) {
                minus.add(tmp);
            } else {
                plus.add(tmp);
            }
        }

        Collections.sort(minus);
        Collections.sort(plus, Collections.reverseOrder());

        int ans = 0;
        int sum = 0;
        for (int i = 0; i < minus.size(); i++) { // 음수, 0
            if (i + 1 < minus.size()) {
                sum += minus.get(i) * minus.get(i + 1);
                i++;
            } else {
                sum += minus.get(i);
            }
        }
        ans += sum;

        sum = 0;
        for (int i = 0; i < plus.size(); i++) { // 양수
            if (i + 1 < plus.size() && plus.get(i) != 1 && plus.get(i + 1) != 1) {
                sum += plus.get(i) * plus.get(i + 1);
                i++;
            } else {
                sum += plus.get(i);
            }
        }
        ans += sum;

        System.out.println(ans);
    }
}