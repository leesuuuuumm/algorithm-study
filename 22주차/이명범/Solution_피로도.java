class Solution {
    static int result = 0;
    
    public int solution(int k, int[][] dungeons) {
        recur(k, dungeons, 0, 0);
        return result;
    }
    
    private void recur(int k, int[][] dungeons, int flag, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            if ((flag & 1 << i) != 0)
                continue;
            if (k < dungeons[i][0])
                continue;
            
            recur(k - dungeons[i][1], dungeons, flag | 1 << i, count + 1);
        }
        result = Math.max(result, count);
    }
}