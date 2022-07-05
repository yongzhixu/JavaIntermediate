
public class EulersNumber {

	public static void main(String[] argStrings) {
//		System.out.printf("\r%f", nFactorial(0));
//		System.out.printf("\r%f", nFactorial(2));
//		System.out.printf("\r%f", nFactorial(16));
//		System.out.printf("\r%f", nFactorial(28));
		System.out.println("e to 100 decimal digits: ");
		System.out.println("2.71828182845904523536028747135266249775724709369995957\r\n" + 
				"49669676277240766303535475945713821785251664274...");
		
		long startime = System.currentTimeMillis();
		System.out.printf("\r%1.25f", nEuler(100000));
		System.out.printf("\rnEuler takes: %f ms", (double)System.currentTimeMillis()-startime);


		startime = System.currentTimeMillis();
		System.out.printf("\r%1.25f", eFactorial(50));
		System.out.printf("\reFactorial takes: %f ms \n", (double)System.currentTimeMillis()-startime);
		

		System.out.printf("\r%1.25f \n", Math.E);
		System.out.println(Math.E-eFactorial(50));

	}

	/**
	 * compute the factorial of an integer the return better be double or float
	 * 
	 * @param n
	 * @return
	 */
	static double nFactorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * nFactorial(n - 1);
		}
	}
	
	static double eFactorial(int n) {
		double e = 0;
		for (int i = 0; i < n; i++) {
			e += 1 / nFactorial(i);
		}
		return e;
	}

	/**
	 * compute the factorial of an integer the return better be double or float
	 * 
	 * @param n
	 * @return
	 */
	static double nEuler(int n) {
		return (double) Math.pow((1 + (double)1 / n), n);
	}
}
