import java.util.function.ObjDoubleConsumer;

public class CalculatingPi {

	public static void main(String[] argStrings) {
		new CalculatingPi();
	}

	public CalculatingPi() {
		this.leibnizeFormulaForPi();
		System.out.printf("polygon pi: %1.20f \r\n", this.circleOfPolygons2(120));
	}

//	Leibniz formula for ¦Ð
//	https://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80
	private double leibnizeFormulaForPi() {

		final long n = 100000;
		double pi = 0;
		for (int i = 1; i < n; i++) {
			pi += 4 * (double) ((1 / (Math.pow(2, i) - 1)) * Math.pow(-1, i - 1));
		}
		System.out.printf("pi: %1.20f \r\n", pi);
		return pi;
	}

	private double piWithPolygons() {
		double pi = 0;

		return pi;
	}

	
	private void circleOfPolygons(double sn, int n) {
		double snext = 0;
		double radis = 1;
		double a = Math.sqrt(Math.pow(radis, 2) - Math.pow(sn / 2, 2));
		double b = radis - a;
		snext = Math.sqrt(Math.pow(b, 2) + Math.pow(sn / 2, 2));
		n--;
		if (n > 0) {
			circleOfPolygons(snext, n);
		} else {
			System.out.printf("circleOfPolygons pi: %.25f \r\n",  snext);
		}
	}

	/**
	 * using polygon (Archimedes' method) to approximate pi
	 * 
	 * start out as polygon with 6 sides
	 * and the double down
	 * @param n
	 * @return
	 */
	private double circleOfPolygons2(int n) {
		double radis = 1;
		double numSides = Math.pow(2, n) * 6;
		double sn = 1;
		while (n > 0) {
			double a = Math.sqrt(Math.pow(radis, 2) - Math.pow(sn / 2, 2));
			a = radis - a;
			sn = Math.sqrt(Math.pow(a, 2) + Math.pow(sn / 2, 2));
			n--;
		}
		return sn * numSides / 2;
	}

}
