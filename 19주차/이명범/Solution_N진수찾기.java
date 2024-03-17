public class Solution {

    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();

        int order = 0;

        label:
        for (int i = 0; true; i++) {
            String numberFormat = getNumberFormat(n, i);

            for (int j = 0; j < numberFormat.length(); j++) {
                if (order++ % m == p - 1) {
                    sb.append(numberFormat.charAt(j));
                }

                if (sb.length() >= t) {
                    break label;
                }
            }
        }

        return sb.toString();
    }

    private String getNumberFormat(int n, int num) {
        StringBuilder sb = new StringBuilder();

        if (num == 0) {
            return "0";
        }

        while (num != 0) {
            int mod = num % n;

            if (mod >= 10) {
                sb.append((char) ('A' + mod - 10));
            } else {
                sb.append(num % n);
            }

            num /= n;
        }

        return sb.reverse().toString();
    }
}