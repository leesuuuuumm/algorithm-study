class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        System.out.println(arr2[0].length);
        int sum=0;
        for(int k=0;k<arr1.length;k++){
           for(int r=0;r<arr2[0].length;r++){
            for(int c=0;c<arr2.length;c++){
            sum+=(arr1[k][c]*arr2[c][r]);
        }
               answer[k][r] = sum;
               sum=0;
        } 
        }
        return answer;
    }
}
