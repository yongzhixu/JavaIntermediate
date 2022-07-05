import java.awt.font.TextMeasurer;
import java.rmi.server.RMIClassLoader;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetInt {

	TreeSet<Integer> A;

	public TreeSetInt(int N) {
		A = new TreeSet<Integer>();
		for (int i = 0; i < N; i++) {
			A.add((int) (Math.random() * 10));
		}
	}

	public TreeSetInt() {
		A = new TreeSet<Integer>();
	}

	void addAll(TreeSet<Integer> B) {
		for (Integer integerB : B) {
			A.add(integerB);
		}
	}

	void removeAll(TreeSet<Integer> B) {
		TreeSet<Integer> tempSet = new TreeSet<Integer>();
		tempSet = (TreeSet)A.clone();
		Iterator<Integer> value = A.iterator();
		while (value.hasNext()) { 
			Integer integerA =(Integer) value.next();
			for (Integer integerB : B) {
				if (integerA.equals(integerB)) {
					tempSet.remove(integerA);
					break;
				}

			}
        } 
		A = tempSet;
	}

	void retainAll(TreeSet<Integer> B) {
		TreeSet<Integer> tempSet = new TreeSet<Integer>();
		tempSet = (TreeSet)A.clone();
		for (Integer integerA : A) {
			boolean rm = true;
			for (Integer integerB : B) {
				if (integerA == integerB) {
					rm = false;
					break;
				}

			}
			if (rm) {
				tempSet.remove(integerA);
			}
		}
		A = tempSet;		
	}

	void print() {
		System.out.print(A);
		System.out.print("\r\n");
	}
}
