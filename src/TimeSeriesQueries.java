import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TimeSeriesQueries {
	
	static int maxTime=Integer.MIN_VALUE;
	static int maxPrice=Integer.MIN_VALUE;

	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] t = new int[n];
        
        TreeMap<Integer,Integer> timeMap = new TreeMap<>();
        for(int t_i = 0; t_i < n; t_i++){
            t[t_i] = in.nextInt();
            maxTime = t[t_i] > maxTime ? t[t_i] : maxTime;
            
        }
        int[] p = new int[n];
        for(int p_i = 0; p_i < n; p_i++){
            p[p_i] = in.nextInt();
            maxPrice = t[p_i] > maxPrice ? t[p_i] : maxPrice;
            
        }
        for(int i=0;i<n;i++){
        	timeMap.put(t[i], p[i]);
        }
        for(int a0 = 0; a0 < q; a0++){
            int _type = in.nextInt();
            int v = in.nextInt();
            int result = findResult(_type,v,timeMap);
            System.out.println(result);
        }
        in.close();
    }

	private static int findResult(int _type, int v, TreeMap<Integer, Integer> timeMap) {
		int res = -1;
		if(_type==1){
			if(maxPrice<v)
				return res;
			PriorityQueue<Entry<Integer,Integer>> pq =  new PriorityQueue<>( new Comparator<Entry<Integer,Integer>>() {

				@Override
				public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {					
					return e1.getKey() - e2.getKey();
				}
			});
			pq.addAll(timeMap.entrySet().stream().filter(e -> e.getValue()>=v)
										.collect(Collectors.toList()));
			res=pq.poll().getKey();			
		}
		if(_type==2){
			if(maxTime<v)
				return res;
			PriorityQueue<Entry<Integer,Integer>> pq =  new PriorityQueue<>( new Comparator<Entry<Integer,Integer>>() {

				@Override
				public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {					
					return e2.getValue() - e1.getValue();
				}
			});
			pq.addAll(timeMap.entrySet().stream().filter(e -> e.getKey()>=v)
										.collect(Collectors.toList()));
			res=pq.poll().getValue();			
		}
		return res;
	}
}
