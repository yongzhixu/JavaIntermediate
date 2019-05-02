package Unit2;

import Unit1.TextIO;

public class Main {

//	public static void main(String []argStrings) {
//		int n = 100000;
////		System.out.printf("e: %d", Math.pow((1 + 1 / n),n));
////		System.out.printf("%.12f",Math.pow((1 + 1 / n),n));
////		
////		System.out.printf("\r%1.12g", Math.pow((1 + 1/100000),100000));
////		System.out.printf("\r%.12f ",Math.pow(2,10));
//
//		
//		char c = new char();
//		System.out.println(c);
//		
//
//		
//		
//	}

	static byte a;
	static short b;
	static int c;
	static long d;
	static float e;
	static double f;
	static char g;
	static boolean h;

	// String不是基本类型
	static String str1 = "";// 生成一个String类型的引用，而且分配内存空间来存放"";
	static String str2; // 只生成一个string类型的引用；不分配内存空间,默认为null

	public static void main(String[] args) {

		System.out.println(
				"byte的大小：" + Byte.SIZE + " byte的默认值：" + a + " byte的数据范围：" + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);
		System.out.println("short的大小：" + Short.SIZE + " short的默认值：" + b + " short的数据范围：" + Short.MIN_VALUE + "~"
				+ Short.MAX_VALUE);
		System.out.println("int的大小：" + Integer.SIZE + " int的默认值：" + c + " int的数据范围：" + Integer.MIN_VALUE + "~"
				+ Integer.MAX_VALUE);
		System.out.println(
				"long的大小：" + Long.SIZE + " long的默认值：" + d + " long的数据范围：" + Long.MIN_VALUE + "~" + Long.MAX_VALUE);
		System.out.println("float的大小：" + Float.SIZE + " float的默认值：" + e + " float的数据范围：" + Float.MIN_VALUE + "~"
				+ Float.MAX_VALUE);
		System.out.println("double的大小：" + Double.SIZE + " double的默认值：" + f + " double的数据范围：" + Double.MIN_VALUE + "~"
				+ Double.MAX_VALUE);
		System.out.println("char的大小：" + Character.SIZE + " char的默认值：" + g + " char的数据范围：" + Character.MIN_VALUE + "~"
				+ Character.MAX_VALUE);
		System.out.println("boolean的大小：" + Byte.SIZE + " boolean的默认值：" + h + " boolean的数据范围：" + Byte.MIN_VALUE + "~"
				+ Byte.MAX_VALUE);

		System.out.println("String字符串的默认值：" + str1 + "str的默认长度：" + str1.length());
		System.out.println("String字符串的默认值：" + str2);

	}

	static int nfactorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * nfactorial(n - 1);
		}
//		return n;
	}
}
