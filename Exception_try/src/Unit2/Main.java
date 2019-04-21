package Unit2;

import Unit1.TextIO;

public class Main {

	public static void main(String []argStrings) {
		int n = 100000;
//		System.out.printf("e: %d", Math.pow((1 + 1 / n),n));
		System.out.printf("%.12f",Math.pow((1 + 1 / n),n));
		
		System.out.printf("\r%1.12g", Math.pow((1 + 1/100000),100000));
		System.out.printf("\r%.12f ",Math.pow(2,10));


		
		
	}
	
	static int nfactorial(int n) {
		if (n==0) {
			return 1;
		}else {
			return n*nfactorial(n-1);
		}
//		return n;
	}
}
