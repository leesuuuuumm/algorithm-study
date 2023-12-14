class Solution {
    static int min;
    public int solution(String s) {
      min = Integer.MAX_VALUE;
		if (s.length() == 1) {
			return 1;
		}
		for (int k = 1; k <= s.length() / 2; k++) {
			int temp = s.length() / k;
			StringBuilder lsb = new StringBuilder();
			String[] sr = new String[temp];
			for (int i = 0; i < temp; i++) {
				sr[i] = s.substring(i * k, i * k + k);
//				System.out.print(sr[i] + " ");
			}
			if ((s.length() % k != 0)) {
				for (int i = k * (s.length() / k); i < s.length(); i++) {
					lsb.append(s.charAt(i));
				}

			}
			int cnt = 1;
			int cntResult = 0;

			StringBuilder sb = new StringBuilder();

			sb.append(sr[0]);
			for (int i = 1; i < sr.length; i++) {
				if (sr[i - 1].equals(sr[i])) {
					cnt++;

					if (i == sr.length - 1 && cnt >= 2) {
						cntResult += (int) (Math.log10(cnt) + 1);
					} else if (cnt == sr.length) {
						cntResult += (int) (Math.log10(cnt) + 1);
					}

				} else {
					sb.append(sr[i]);
					if (cnt >= 2) {
						cntResult += (int) (Math.log10(cnt) + 1);
						cnt = 1;
					}

				}

			}
			min = Math.min(min, cntResult + sb.length() + lsb.length());
		}
		return min;

    }
}
