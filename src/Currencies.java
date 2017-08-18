import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.DoubleStream;

public class Currencies {
	static long MODULO = (int) (7 + Math.pow(10, 9));

	static double currencies(int numberOfCurrencyTypes, int amount, int startType, int finalType,
			int numberOfTransactions, double[][] A) {
		double result = -1;
		// if(numberOfTransactions==1){
		// result= ( amount * A[startType][finalType] ) % MODULO;
		// }
		int intermediateStartType = startType;
		double intermediateAmount = amount;
		for (int transaction = 1; transaction <= numberOfTransactions; transaction++) {
			// DoubleStream d = DoubleStream.of(A[intermediateStartType]);
			double maxConversionRate = -1;
			int maxConversionRateIndex = -1;
			if (transaction == numberOfTransactions) {
				maxConversionRateIndex = finalType;
				maxConversionRate = A[intermediateStartType][maxConversionRateIndex];
			} else {
				for (int i = 0; i < numberOfCurrencyTypes; i++) {
					if (i == finalType)
						continue;
					if (maxConversionRate < A[intermediateStartType][i]) {
						maxConversionRate = A[intermediateStartType][i];
						maxConversionRateIndex = i;
					}
				}
			}
			intermediateAmount *= maxConversionRate;
			intermediateStartType = maxConversionRateIndex;
			// double
			// maxConversionRate=DoubleStream.of(A[intermediateStartType]).max().getAsDouble();

		}
		result = intermediateAmount % MODULO;
		return result;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int x = in.nextInt();
		int s = in.nextInt();
		int f = in.nextInt();
		int m = in.nextInt();
		double[][] A = new double[n][n];
		for (int A_i = 0; A_i < n; A_i++) {
			for (int A_j = 0; A_j < n; A_j++) {
				A[A_i][A_j] = in.nextDouble();
			}
		}
		long result = (long) currencies(n, x, s, f, m, A);
		System.out.println(result);
		in.close();
	}
}
