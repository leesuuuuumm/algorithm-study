
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 생태학 {
    //좌표 정보 관련 클래스
    static class Pos implements Comparable<Pos> {
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }

        //좌표 y기준 오름차순 정렬, 동일할 때에는 x기준 오름차순 정렬
        @Override
        public int compareTo(Pos p) {
            if(p.y == this.y){
                return this.x - p.x;
            }
            return this.y - p.y;
        }

    }
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Pos> posList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        boolean[] duplicationY = new boolean[10001];

        //입력값 저장
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            posList.add(new Pos(x, y));
        }

        //좌표 정렬 진행
        Collections.sort(posList);
        //중복되지 않은 y좌표 구하기
        for(Pos cur : posList){
            if(!duplicationY[cur.y]){
                duplicationY[cur.y] = true;
                yList.add(cur.y);
            }
        }

        int len = yList.size();
        int result = Integer.MAX_VALUE;
        int halfN = N/2;
        List<Integer> cur = new ArrayList<>();
        //y좌표의 조합을 통해 모든 직사각형 탐색
        for(int i=0;i<len;i++){
            int idx = 0;
            for(int j=i;j<len;j++){
                int endY = yList.get(j);
                //해당 y좌표 조합에서 포함된 x좌표들 구하기
                while(idx < N && posList.get(idx).y <= endY){
                    if(yList.get(i) <= posList.get(idx).y){
                        cur.add(posList.get(idx).x);
                    }
                    idx++;
                }
                //x좌표의 개수가 N/2개가 이하일 때 : N/2개를 포함시킬 수 없음
                if(cur.size() < halfN){
                    continue;
                }
                //x좌표 오름차순 정렬
                Collections.sort(cur);
                //직사각형 최소 넓이 비교하기
                for(int k=0;k<=cur.size()-halfN;k++){
                    result = Math.min(result, (cur.get(k+halfN-1) - cur.get(k) + 2) * (endY - yList.get(i) + 2));
                }
            }
            cur.clear();
        }
        //최소 직사각형의 넓이 BufferedWriter 저장
        bw.write(String.valueOf(result));
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
}