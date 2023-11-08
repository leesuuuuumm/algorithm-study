import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    양쪽 끝에서 투 포인터를 이용해서 사이를 좁혀나가면서 비교
    두 문자가 같지 않을 때 왼쪽 포인터의 다음 문자와 현재 오른쪽 포인터의 문자를 비교해서 같으면 왼쪽 포인터만 증가
    반대의 경우는 오른쪽 포인터만 감소

    같지 않을 때 불일치 카운트를 하나 증가시킨다.

    위의 경우는 왼쪽부터 건너 뛰었을 경우지만
    오른쪽부터 건너 뛰었을 경우에 결과가 달라질 수 있으므로 각각 수행해준다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String str = br.readLine();

            int l = 0;
            int r = str.length() - 1;
            int mismatch = 0; // 불일치 횟수, 0번이면 0, 1번이면 1, 2번 이상이면 2
            while (l < r) {
                char left = str.charAt(l);
                char right = str.charAt(r);

                if (left == right) {
                    l++;
                    r--;
                } else if (left != right) {
                    mismatch++;
                    l++;
                }
            }

            if (mismatch == 0) {
                sb.append(0 + "\n");
                continue;
            } else if (mismatch == 1) {
                sb.append(1 + "\n");
                continue;
            }

            l = 0;
            r = str.length() - 1;
            mismatch = 0; // 불일치 횟수, 0번이면 0, 1번이면 1, 2번 이상이면 2
            while (l < r) {
                char left = str.charAt(l);
                char right = str.charAt(r);

                if (left == right) {
                    l++;
                    r--;
                } else if (left != right) {
                    mismatch++;
                    r--;
                }
            }

            if (mismatch >= 2) {
                sb.append(2);
            } else if (mismatch == 1) {
                sb.append(1);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}