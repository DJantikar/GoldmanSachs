import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TraderProfit {

    static int traderProfit(int k, int n, int[] A) {
        int[][] dp = new int[k+1][n];
        Arrays.fill(dp[0], 0);
        for(int row =1 ; row < k+1 ; row++)
        	dp[row][0]=0;
        for(int row = 1 ; row < k+1 ;row++){
        	for(int col=1 ; col < n ;col++){
        		int notTransacting = dp[row][col-1];
        		//if transacting
        		int maxOnTransacting =0;
        		for(int prevDay=0 ; prevDay < col ; prevDay++ ){
        			int onTransacting = (A[col] - A[prevDay]) + dp[row-1][prevDay];
        			maxOnTransacting = onTransacting > maxOnTransacting ? onTransacting : maxOnTransacting;
        		}
        	    dp[row][col] = maxOnTransacting > notTransacting ? maxOnTransacting : notTransacting;
        	}
        }
    	return dp[k][n-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int k = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            
            int result = traderProfit(k, n, arr);
            System.out.println(result);
        }
        in.close();
    }
}
