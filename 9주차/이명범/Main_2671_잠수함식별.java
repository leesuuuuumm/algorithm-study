package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2671_잠수함식별 {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(br.readLine().matches("^(100+1+|01)+") ? "SUBMARINE" : "NOISE");
	}
}
