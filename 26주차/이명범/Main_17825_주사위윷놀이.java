public class Main {
    static class Knight {
        Node cur = head;
        int score = 0;

        public boolean move(int count) {
            Node temp;
            if (cur.blue != null) {
                temp = cur.moveBlue(count);
            } else {
                temp = cur.moveRed(count);
            }
            for (Knight knight : knights) {
                if (knight == this) continue;
                if (temp != tail && knight.cur == temp) return false;
            }
            cur = temp;
            score += cur.score;
            return true;
        }
    }

    static class Node {
        int score;

        Node red = null;
        Node blue = null;

        public Node(int score) {
            this.score = score;
        }

        public void addRed(Node node) {
            if (red == null) {
                red = node;
                return;
            }
            red.addRed(node);
        }

        public void addBlue(Node node) {
            blue = node;
        }

        public Node moveRed(int count) {
            if (count == 0) return this;
            if (this == tail) return this;
            return red.moveRed(count - 1);
        }

        public Node moveBlue(int count) {
            return blue.moveRed(count - 1);
        }
    }

    static Node head, blue1, blue2, blue3, center, end, tail;
    static Knight[] knights = new Knight[4];
    static int[] counts = new int[10];
    static int result = 0;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < 4; i++) {
            knights[i] = new Knight();
        }
        for (int i = 0; i < 10; i++) {
            counts[i] = read();
        }
        dfs(0);
        System.out.println(result);
    }

    private static void dfs(int cnt) {
        if (cnt == 10) {
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += knights[i].score;
            }
            result = Math.max(result, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (knights[i].cur == tail) continue;
            Node prev = knights[i].cur;
            if (!knights[i].move(counts[cnt])) continue;
            dfs(cnt + 1);
            knights[i].score -= knights[i].cur.score;
            knights[i].cur = prev;
        }
    }

    private static void init() {
        head = new Node(0);
        blue1 = new Node(10);
        blue2 = new Node(20);
        blue3 = new Node(30);
        center = new Node(25);
        end = new Node(40);
        tail = new Node(0);

        for (int i = 1; i <= 4; i++) {
            head.addRed(new Node(2 * i));
            blue1.addRed(new Node(10 + 2 * i));
            blue2.addRed(new Node(20 + 2 * i));
            blue3.addRed(new Node(30 + 2 * i));
            if (i >= 3) continue;
            center.addRed(new Node(25 + 5 * i));
        }
        Node temp1 = new Node(13);
        temp1.addRed(new Node(16));
        temp1.addRed(new Node(19));
        temp1.addRed(center);
        blue1.addBlue(temp1);
        Node temp2 = new Node(22);
        temp2.addRed(new Node(24));
        temp2.addRed(center);
        blue2.addBlue(temp2);
        Node temp3 = new Node(28);
        temp3.addRed(new Node(27));
        temp3.addRed(new Node(26));
        temp3.addRed(center);
        blue3.addBlue(temp3);

        head.addRed(blue1);
        blue1.addRed(blue2);
        blue2.addRed(blue3);
        blue3.addRed(end);
        center.addRed(end);
        end.addRed(tail);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}