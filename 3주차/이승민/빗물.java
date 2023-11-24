import java.util.*;

public class Main {
    /*
    현재 인덱스를 기준으로 왼쪽과 오른쪽을 2개의 포인터로 탐색해서 각각 가장 높은 기둥의 높이를 구한다.(처음과 마지막 높이는 제외)
    현재 인덱스의 높이가 두 기둥보다 더 낮을 경우 구해진 두 기둥 중 더 낮은 쪽의 높이에서 현재 높이를 빼서 현재 인덱스의 물 양을 구한다.
     */
    static int h, w;
    static int[] height;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        h = sc.nextInt();
        w = sc.nextInt();
        height = new int[w];
        for (int i = 0; i < w; i++) {
            height[i] = sc.nextInt();
        }

        int water = 0;
        for (int i = 1; i < w - 1; i++) {
            int l = 0;
            int r = 0;

            for (int j = 0; j < i; j++) {
                l = Math.max(l, height[j]);
            }
            for (int k = i + 1; k < w; k++) {
                r = Math.max(r, height[k]);
            }

            if (height[i] < l && height[i] < r) {
                water += Math.min(l, r) - height[i];
            }
        }

        System.out.println(water);
    }
}