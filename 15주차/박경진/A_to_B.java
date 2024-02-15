import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int B = sc.nextInt(); //처음 주어진 수 
		int A = sc.nextInt(); //바뀐 수
		
		int count = 0; //연산 횟수
		
		while(A > B)
		{
			int end = A % 10; //일의 자리
			if(end % 2 != 0 && end != 1)
			{
				count = -1;
				break;
			}
      
			if(end == 1)
			{
				A = A / 10;
				count++;
			}
			else if(A % 2 == 0)
			{
				A = A / 2;
				count++;
			}
			
			if(A < B) {
				count = -1;
				break;
			}
			else continue;
			
		}
		
		if(count != -1) count++;
		
		System.out.println(count);
	}
	
}
