import java.util.*;

class Solution {    
    int result = 0;
    
    public int solution(int n, String[] data) {
        permutation(0, 0, data);
        return result;
    }
    
    Map<String, Integer> loc = new HashMap<>();
    
    private void permutation(int cnt, int flag, String[] data) {
        if (cnt == 8) {
            boolean possible = true;
            for (String d : data) {
                if (!isSatisfied(d)) {
                    possible = false;
                    break;
                }
            }
            if (possible) result++;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if ((flag & 1 << i) != 0)  continue;
            loc.put(getName(i), cnt);
            permutation(cnt + 1, flag | 1 << i, data);
        }
    }

    private boolean isSatisfied(String data) {
        String from = String.valueOf(data.charAt(0));
        String to = String.valueOf(data.charAt(2));
        String com = String.valueOf(data.charAt(3));
        int value = (int) (data.charAt(4) - '0');
        
        int diff = getDiffValue(loc.get(from), loc.get(to));
        
        if (com.equals("=") && diff == value) return true;
        else if (com.equals("<") && diff < value) return true;
        else if (com.equals(">") && diff > value) return true;
        else return false;
    }
    
    private int getDiffValue(int a, int b) {
        return Math.abs(a - b) - 1;
    }
    
    private String getName(int id) {
        switch (id) {
            case 0:
                return "A";
            case 1:
                return "C";
            case 2:
                return "F";
            case 3:
                return "J";
            case 4:
                return "M";
            case 5:
                return "N";
            case 6:
                return "R";
            default:
                return "T";
        }
    }
}