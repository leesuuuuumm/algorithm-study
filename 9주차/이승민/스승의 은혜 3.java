import java.util.*;

class Student implements Comparable<Student> {
        int price;
        int trans;

        Student(int price, int trans) {
            this.price = price;
            this.trans = trans;
        }

        @Override
        public int compareTo(Student s) {
            return (this.price / 2 + this.trans) - (s.price / 2 + s.trans);
        }
    }

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        Student[] s = new Student[n];
        for (int i = 0; i < n; i++) {
            s[i] = new Student(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(s);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int budget = b;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    budget -= s[j].price / 2;
                    budget -= s[j].trans;
                } else {
                    budget = budget - s[j].price - s[j].trans;
                }
                 
                if (budget >= 0) cnt++;
                else break;
            }
            ans = Math.max(ans, cnt);
        }

        System.out.print(ans);
    }
}
