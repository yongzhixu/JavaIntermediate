import java.util.TreeSet;

public class SetCalculator {

	public static void main(String[] aStrings) {

		TreeSetInt A = new TreeSetInt(5);
		TreeSet<Integer> B = new TreeSet<Integer>();
//		for (int i = 0; i < 5; i++) {
//			B.add((int) (Math.random() * 10));
//		}

//		System.out.println(B);
		try {
			B = expressionValue();
		} catch (ParseError e) {
			// TODO: handle exception
		}
		System.out.println(B);
		A.print();
		A.retainAll(B);
		A.print();
	}

	/**
	 * Read an expression from the current line of input and return its value.
	 * 
	 * @throws ParseError if the input contains a syntax error
	 */
	private static TreeSet<Integer> expressionValue() throws ParseError {
		TreeSet<Integer> B = new TreeSet<Integer>();
		TextIO.skipBlanks();
		if (TextIO.peek() == '[') {
			// The expression must be of the form
			// "(" <expression> <operator> <expression> ")"
			// Read all these items, perform the operation, and
			// return the result.
			TextIO.getAnyChar(); // Read the "["
			TextIO.skipBlanks();
			while (!TextIO.eof()) {
				TextIO.skipBlanks();
				getNext(B);
			}
			TextIO.getAnyChar(); // Read the ")"
		} else { // No other character can legally start an expression.
			throw new ParseError("Encountered unexpected character, \"" + TextIO.peek() + "\" in input.");
		}
		return B;
	} // end expressionValue()

	private static void getNext(TreeSet<Integer> B) throws ParseError {
		// TODO Auto-generated method stub
		TextIO.skipBlanks();
		if (TextIO.peek() != ',') {
			if (Character.isDigit(TextIO.peek())) {
				// The next item in input is a number, so the expression
				// must consist of just that number. Read and return
				// the number.
				B.add(TextIO.getInt());
			}
		}else if (TextIO.peek() == ']') {
			 TextIO.putln("reach the end of set!");
		}else {
			throw new ParseError("Encountered unexpected character, \"" + TextIO.peek() + "\" in input.");
		}
	}
	
	/**
	 * An object of type ParseError represents a syntax error found in the user's
	 * input.
	 */
	private static class ParseError extends Exception {
		ParseError(String message) {
			super(message);
		}
	} // end nested class ParseError

	static void addAll(int[] A) {

	}

	static void printArr(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(" ");
			System.out.print(A[i]);
		}

		System.out.print("\r\n");
	}
}
