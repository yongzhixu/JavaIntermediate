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

	// String���ǻ�������
	static String str1 = "";// ����һ��String���͵����ã����ҷ����ڴ�ռ������"";
	static String str2; // ֻ����һ��string���͵����ã��������ڴ�ռ�,Ĭ��Ϊnull

	public static void main(String[] args) {

		System.out.println(
				"byte�Ĵ�С��" + Byte.SIZE + " byte��Ĭ��ֵ��" + a + " byte�����ݷ�Χ��" + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);
		System.out.println("short�Ĵ�С��" + Short.SIZE + " short��Ĭ��ֵ��" + b + " short�����ݷ�Χ��" + Short.MIN_VALUE + "~"
				+ Short.MAX_VALUE);
		System.out.println("int�Ĵ�С��" + Integer.SIZE + " int��Ĭ��ֵ��" + c + " int�����ݷ�Χ��" + Integer.MIN_VALUE + "~"
				+ Integer.MAX_VALUE);
		System.out.println(
				"long�Ĵ�С��" + Long.SIZE + " long��Ĭ��ֵ��" + d + " long�����ݷ�Χ��" + Long.MIN_VALUE + "~" + Long.MAX_VALUE);
		System.out.println("float�Ĵ�С��" + Float.SIZE + " float��Ĭ��ֵ��" + e + " float�����ݷ�Χ��" + Float.MIN_VALUE + "~"
				+ Float.MAX_VALUE);
		System.out.println("double�Ĵ�С��" + Double.SIZE + " double��Ĭ��ֵ��" + f + " double�����ݷ�Χ��" + Double.MIN_VALUE + "~"
				+ Double.MAX_VALUE);
		System.out.println("char�Ĵ�С��" + Character.SIZE + " char��Ĭ��ֵ��" + g + " char�����ݷ�Χ��" + Character.MIN_VALUE + "~"
				+ Character.MAX_VALUE);
		System.out.println("boolean�Ĵ�С��" + Byte.SIZE + " boolean��Ĭ��ֵ��" + h + " boolean�����ݷ�Χ��" + Byte.MIN_VALUE + "~"
				+ Byte.MAX_VALUE);

		System.out.println("String�ַ�����Ĭ��ֵ��" + str1 + "str��Ĭ�ϳ��ȣ�" + str1.length());
		System.out.println("String�ַ�����Ĭ��ֵ��" + str2);

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
