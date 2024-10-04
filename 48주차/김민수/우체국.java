import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] info = new long[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long idx = Long.parseLong(st.nextToken());  // 마을 위치 X[i]
            long num = Long.parseLong(st.nextToken());  // 마을에 사는 사람 수 A[i]
            info[i][0] = idx;
            info[i][1] = num;
        }

        // 마을 위치를 기준으로 정렬
        Arrays.sort(info, (o1, o2) -> Long.compare(o1[0], o2[0]));

        // 총 인구 수의 절반 이상의 누적 인구가 되는 지점에서 우체국 위치 선정
        long totalPopulation = 0;
        for (int i = 0; i < n; i++) {
            totalPopulation += info[i][1];  // 전체 인구 합 계산
        }

        long cumulativePopulation = 0;
        for (int i = 0; i < n; i++) {
            cumulativePopulation += info[i][1];  // 인구 누적 합 계산
            // 누적 인구가 전체 인구의 절반 이상이 되는 순간
            if (cumulativePopulation >= (totalPopulation + 1) / 2) {
                System.out.println(info[i][0]);  // 해당 마을의 위치 출력
                break;
            }
        }
    }
}
