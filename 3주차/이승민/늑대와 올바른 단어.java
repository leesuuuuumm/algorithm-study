import java.util.*;

public class Main {
    /*

     */

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        int[] vis = new int[26];
        boolean flag = true;
        boolean start = true;
        // 어떤 문자가 나오던 일단 vis 방문횟수 증가
        // 이전 문자가 w, 현재 문자가 o인지만 확인, 만약 현재 문자가 o가 아니라면 false
        // 이전 문자가 o, 현재 문자가 l이면 w와 o의 방문횟수를 비교, 만약 방문횟수가 다르거나 현재 문자가 l이 아니라면 false
        // 이전 문자가 l, 현재 문자가 f이면 o와 l의 방문횟수를 비교, 만약 방문횟수가 다르거나 현재 문자가 f가 아니라면 false
        // 이전 문자가 f였고 현재 문자가 w일시 l와 f의 방문횟수를 비교, 만약 w가 아니거나 방문횟수가 다르면 false, 같으면 vis 초기화 이후 방문횟수 증가
        // 올바른 단어가 연속으로 등장할 때 i == 0일 때와 동일하게 처리하기 위해 start flag 사용
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (start) {
                if (ch == 'w') {
                    vis[ch - 'a']++;
                    start = false;
                } else {
                    flag = false;
                    break;
                }
            } else if (str.charAt(i - 1) == 'w') {
                if (ch == 'w' || ch == 'o') {
                    vis[ch - 'a']++;
                } else {
                    flag = false;
                    break;
                }
            } else if (str.charAt(i - 1) == 'o') {
                if (ch == 'o') {
                    vis[ch - 'a']++;
                } else if (ch == 'l') {
                    vis[ch - 'a']++;
                    if (vis['w' - 'a'] != vis['o' - 'a']) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            } else if (str.charAt(i - 1) == 'l') {
                if (ch == 'l') {
                    vis[ch - 'a']++;
                } else if (ch == 'f') {
                    vis[ch - 'a']++;
                    if (vis['o' - 'a'] != vis['l' - 'a']) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            } else {
                if (ch == 'f') {
                    vis[ch - 'a']++;
                } else if (ch == 'w') {
                    if (vis['l' - 'a'] == vis['f' - 'a']) {
                        Arrays.fill(vis, 0);
                        start = true;
                        i--;
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (str.charAt(str.length() - 1) != 'f' || vis ['l' - 'a'] != vis['f' - 'a']) {
            flag = false;
        }

        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}