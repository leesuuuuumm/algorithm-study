import java.util.*;

class Main 
{
	static int[][] price;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //집 수
		price = new int[T + 1][3];
		
		for(int i = 0; i < T; i++) //가격 저장
		{
			for(int j = 0; j < 3; j++)
			{
				price[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(solution(T));
	}
    
	public static int solution(int T) 
	{
		
		int[][] dp = new int[T + 1][3];
		dp[0][0] = price[0][0];
		dp[0][1] = price[0][1];
		dp[0][2] = price[0][2];
		
		for(int i = 1; i <= T; i++)
		{
			dp[i][0] = price[i][0] + check(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = price[i][1] + check(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = price[i][2] + check(dp[i - 1][0], dp[i - 1][1]);
		}
		
		int min = 1000001;
		
		for(int i = 0; i < 3; i++)
		{
			if(min > dp[T][i]) min = dp[T][i];
		}
		
		return min;
    }
	
	public static int check(int a, int b)
	{
		if(a > b) return b;
		else return a;
	}
}
