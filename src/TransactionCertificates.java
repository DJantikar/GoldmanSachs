import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class TransactionCertificates {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<Long,String> h = new HashMap<>();        
        int n = in.nextInt();
        int k = in.nextInt();
        int p = in.nextInt();
        long m = in.nextLong();
        in.close();
        int[] nums = IntStream.range(1, k+1).toArray();
        Set<String> res = new HashSet<String>();
        getAllKLengthRec(nums,"",nums.length,n,res);
        //System.out.println(res.size());
        //System.out.println(res);
        checkIfCertificatesExist(h, p, m, res);
    }

	private static boolean checkIfCertificatesExist(HashMap<Long, String> h, int p, long m, Set<String> res) {
		for(String s : res){
        	long val = calculateSigma(s,p,m);
        	if(h.containsKey(val)){
//        		System.out.println(h.get(val));
//        		System.out.println(s);
//        		System.out.println(val);
        		for(char c : h.get(val).toCharArray())
        			System.out.print(c+" ");
        		System.out.println();
        		for(char c : s.toCharArray())
        			System.out.print(c+" ");
        		return true;
        	}     
        	h.put(val, s);
        }
        return false;
	}

    public static long calculateSigma(String s, int p, long m) {
    	long res=0;
    	int n = s.length();
		for(int i=0;i<s.length();i++){
			int dgt=Integer.valueOf(s.substring(i, i+1));
			long pVal = (long) Math.pow(p, n-1-i);
			res += dgt*pVal;
		}
		res = res % m;
		return res;
	}

	static void getAllKLengthRec(int set[], String prefix, int n, int postionsAvailable,Set<String> res) {
        
        // Base case: k is 0, print prefix
        if (postionsAvailable == 0) {
           // System.out.println(prefix);
            res.add(prefix);
            return;
        }
 
        // One by one add all characters from set and recursively 
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {
             
            // Next character of input added
            String newPrefix = prefix + set[i]; 
             
            // k is decreased, because we have added a new character
            getAllKLengthRec(set, newPrefix, n, postionsAvailable - 1,res); 
        }
    }
}
/*
 * [1,3] - 11 12 13 21 22 23 31 32 33 
 */
