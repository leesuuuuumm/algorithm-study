class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int a = arrayA[0];
        int b = arrayB[0];
        for(int i = 1; i < arrayA.length; i++) {
            if(arrayA[i] % a == 0) 
                continue;
            
            int max = Math.max(arrayA[i] , a);
            int min = Math.min(arrayA[i], a);
            a = gcd(max, min);
         
        }
        for(int i = 1; i < arrayB.length; i++) {
            if(arrayB[i] % b == 0) continue;
            int max = Math.max(arrayB[i] , b);
            int min = Math.min(arrayB[i], b);
            b = gcd(max, min);
        }
        for(int i : arrayB) {
            if(i % a == 0) {
                a = 0;
                break;
            }
        }
        for(int i : arrayA) {
            if(i % b == 0) {
                b = 0;
                break;
            }
        }
        
        return  Math.max(a,b);
    }
    
    private int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
}
