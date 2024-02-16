import java.util.*;

public class Main {
    static int[] circle;
    static ArrayList<Integer>[] wheels;

    public static void main(String[] args) {
        wheels = new ArrayList[4]; //4개의 톱니바퀴를 저장
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 4; i++){ //톱니바퀴 모양 저장
            String s = sc.nextLine();
            wheels[i] = new ArrayList<>();
            for(int j = 0; j < 8; j++){
                wheels[i].add(Character.getNumericValue(s.charAt(j)));
            }
        }

        int x = sc.nextInt(); //몇번의 회전이 있는지
        int[] c = new int[x*2]; //회전 케이스 저장
        for(int i = 0; i < x; i++){
            c[i*2] = sc.nextInt();
            c[i*2+1] = sc.nextInt();
        }

        for(int test_case = 0; test_case < x; test_case++){
            int wheel = c[test_case*2];
            int way = c[test_case*2+1]; //-1이면 반시계, 1이면 시계, 0이면 아직, -2이면 회전 불필요
            circle = new int[4]; //4개의 톱니바퀴의 회전 여부 저장
            check(wheel-1, way); //톱니바퀴들을 어느 방향으로 회전시킬지 저장
            rotate();
        }
        System.out.println(result());
    }

    static void check(int wheel, int way){
        circle[wheel] = way;
        for(int i = 0; i < 4; i++){
            if(circle[i] == 0) break;
            else if(i == 3) return;
        }

        if(wheel == 0 && circle[wheel+1] == 0){
            if(wheels[wheel+1].get(6) != wheels[wheel].get(2)){
                check(wheel+1, (-1) * circle[wheel]);
            }
            else{
                check(wheel+1, -2);
            }
        }
        if((wheel == 1 || wheel == 2)){
            if(circle[wheel+1] == 0) {
                if (wheels[wheel + 1].get(6) != wheels[wheel].get(2)) {
                    check(wheel + 1, (-1) * circle[wheel]);
                } else {
                    check(wheel + 1, -2);
                }
            }
        }
        if((wheel == 1 || wheel == 2)){
            if( circle[wheel-1] == 0) {
                if (wheels[wheel - 1].get(2) != wheels[wheel].get(6)) {
                    check(wheel - 1, (-1) * circle[wheel]);
                } else {
                    check(wheel - 1, -2);
                }
            }
        }
        if(wheel == 3){
            if(circle[wheel-1] == 0) {
                if (wheels[wheel - 1].get(2) != wheels[wheel].get(6)) {
                    check(wheel - 1, (-1) * circle[wheel]);
                } else {
                    check(wheel - 1, -2);
                }
            }
        }
    }

    static void rotate(){
        for(int i = 0; i < 4; i++){
            int t = 0; //빼낸 값 임시저장

            if(circle[i] == -1){ //반시계 방향 회전
                t = wheels[i].get(0);
                wheels[i].remove(0);
                wheels[i].add(t);
            }
            else if(circle[i] == 1){
                t = wheels[i].get(7);
                wheels[i].remove(7);
                wheels[i].add(0,t);
            }
        }
    }

    static int result(){
        int sum = 0;

        //System.out.println(circle[0] + " " + circle[1] + " " + circle[2] + " " + circle[3]);

        for(int i = 0; i < 4; i++){
            if(wheels[i].get(0) == 1){
                if(i == 0) sum++;
                if(i == 1) sum += 2;
                if(i == 2) sum += 4;
                if(i == 3) sum += 8;
            }
        }
        return sum;
    }
}
