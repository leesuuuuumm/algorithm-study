import java.util.*;
class Solution {

    class Table implements Comparable<Table>{
        int col;
        int pk;
        int[] val = new int[len];

        public Table(int col, int pk,  int[] val){
            this.col = col;
            this.pk = pk;
            this.val = val;
        }

        @Override
        public int compareTo(Table o){
            if(this.col == o.col){
                return Integer.compare(o.pk, this.pk);
            }
            return Integer.compare(this.col,o.col);
        }
    }
    int colm;
    int len;
    public int solution(int[][] data, int col, int row_begin, int row_end) {

       PriorityQueue<Table> pq = new PriorityQueue<>();
       len = data[0].length;
        //1. 정렬하기 (테이블 )
        // -> [i][col] 기준으로 오름 차순 정렬
        // -> 같다면 [i][0] 기준으로 내림차순 정렬
        for(int i=0;i<data.length;i++){
                pq.offer(new Table(data[i][col-1],data[i][0],data[i]));
        }

        //2. i= row_begin ~ row_end 까지 j = 0~ data[0].lengh[i][j] % i 나눈값 저장
        int cnt = 0;

        int[] XORArr = new int[row_end - row_begin + 1];
        int k = 0;
        while(!pq.isEmpty()){
            cnt++;
            if(cnt > row_end) break;
            Table t = pq.poll();       
            if(cnt<row_begin) continue;

            int sum = 0;
            for(int j=0;j<t.val.length;j++){
                sum += t.val[j] % cnt;
            }
            XORArr[k++] = sum;
        }

        //3. 누적된 값 하나씩 for문 돌면서 XOR 해주기 (Bitwise)
        int answer = XORArr[0];
        for(int i=1;i<XORArr.length;i++){
            answer ^= XORArr[i];
        }

         return answer;
    }
}
