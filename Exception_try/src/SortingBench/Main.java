package SortingBench;

import java.util.Arrays;
import Unit1.TextIO;

public class Main {

	public static void main(String [] args) {
		sortBenchMarking(1000);
		sortBenchMarking(10000);
		sortBenchMarking(15000);
	}
	
	private static void sortBenchMarking(int le) {
		final int length = le;

		int[] num_1 = new int [length];
		int[] num_2 = num_1;
		
		for (int i =0;i<num_1.length;i++) {
			num_1[i]=(int)(Integer.MAX_VALUE * Math.random());
		}
		
		Sort sort = new Sort();

		TextIO.putln();
		TextIO.putln("=====================length: "+length+"=====================");
		long startTime = System.currentTimeMillis();
		sort.insertionSort(num_1);
		long runTime = System.currentTimeMillis() - startTime;
		TextIO.putln("insertion sort takes "+runTime+" ms");

		startTime =System.currentTimeMillis();
		Arrays.sort(num_2);
		runTime = System.currentTimeMillis() - startTime;
		TextIO.putln("built in sort takes "+runTime+" ms");
		
		startTime =System.currentTimeMillis();
		sort.selectionSort(num_2);
		runTime = System.currentTimeMillis() - startTime;
		TextIO.putln("selection sort takes "+runTime+" ms");
		
		
		startTime =System.currentTimeMillis();
		sort.quicksort(num_2,0,num_2.length-1);
		runTime = System.currentTimeMillis() - startTime;
		TextIO.putln("quicksort sort takes "+runTime+" ms");
		
		
	}
}
