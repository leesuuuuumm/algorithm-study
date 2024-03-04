import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String[] array = s.split("\\-");

        String[] num = array[0].split("\\+");

        int sum = 0;

        for(int i = 0; i < num.length; i++)
        {
            sum += Integer.parseInt(num[i]);
        }

        for(int i = 1; i < array.length; i++)
        {
            num = array[i].split("\\+");
            for(int j = 0; j < num.length; j++)
            {
                sum -= Integer.parseInt(num[j]);
            }
        }

        System.out.println(sum);

    }

}
