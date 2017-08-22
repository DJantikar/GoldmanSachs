import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Stock{
	int stockValue ;
    int stockCount;
	public int getStockValue() {
		return stockValue;
	}
	public int getStockCount() {
		return stockCount;
	}
	public Stock(int stockValue, int stockCount) {
		//super();
		this.stockValue = stockValue;
		this.stockCount = stockCount;
	}	
}

public class BuyMaxStocks {
    static long buyMaximumProducts(int n, long amount, int[] a) {
    	PriorityQueue<Stock> pq = new PriorityQueue<>(new Comparator<Stock>() {
			@Override
			public int compare(Stock s1, Stock s2) {	
				
				 int diff = s1.getStockValue() - s2.getStockValue();
				 //return diff == 0 ? 0 : (diff > 0 ? 1 : -1) ;
				 return diff;
			}
		});
    	
    	for(int i=0;i<n;i++){
    		pq.add(new Stock(a[i],i+1));
    	}
        
    	long maxStocks=0;
    	while(!pq.isEmpty() && amount>0){
    		Stock s = pq.poll();
    		if(s.stockCount * s.stockValue <= amount){
    			maxStocks+=s.stockCount;
    			amount-=s.stockCount * s.stockValue;
    		}
    		else{
    			long stockCountToUse = amount/s.stockValue;
    			maxStocks+= stockCountToUse;
    			amount-=stockCountToUse*s.stockValue;
    		}
    	}
    	return maxStocks;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        long k = in.nextInt();
        long result = buyMaximumProducts(n, k, arr);
        System.out.println(result);
        in.close();
    }
}
