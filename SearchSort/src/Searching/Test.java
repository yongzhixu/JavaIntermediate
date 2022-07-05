package Searching;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.xml.ws.AsyncHandler;

public class Test {

	public static void main(String[] args) {


		
        List<Sample> list = new ArrayList();
        Sample sample1 = new Sample(1);
        Sample sample2 = new Sample(2);
        list.add(sample1);
        Boolean contains;
        contains = list.contains(sample2);
        System.out.println("Contains: " + contains);
	}

	public static void switchMethod(String input) {
		switch (input) {
		case "apple":
			System.out.println("sauce");
			break;
		case "orange":
			System.out.println("julius");
		case "banana":
			System.out.println("split");
			break;
		case "pear":
			System.out.println("juice");
		default:
			System.out.println(input);

		}


	}
	
	
	public static class Sample {

	    private int number;
	    private String color;

	    public Sample(int number) {
	        this.number = number;
	        this.color = "blue";
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Sample other = (Sample) obj;
	        if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
	            return false;
	        }
	        return true;
	    }
	}

	public class Cell {
		public Cell() {
		}

		public char content; // The character in this cell.
		public Cell next; // Pointer to the cell to the right of this one.
		public Cell prev; // Pointer to the cell to the left of this one.
	}
	
	Cell currentCell = new Cell();

	public void moveRight() {
		if (currentCell.next == null) {
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.next = null;
			newCell.prev = currentCell;
			currentCell.next = newCell;
			currentCell = currentCell.next;
		}
	}

	public static void sort(double[] list) {
		sort(list, list.length - 1);
	}

	public static void sort(double[] list, int high) {
		if (high > 1) {
			// Find the largest number and its index
			int indexOfMax = 0;
			double max = list[0];
			for (int i = 1; i <= high; i++) {
				if (list[i] > max) {
					max = list[i];
					indexOfMax = i;
				}
			}

			// Swap the largest with the last number in the list
			list[indexOfMax] = list[high];
			list[high] = max;

			// Sort the remaining list
			sort(list, high - 1);
		}
	}

	public static void xMethod(int length) {
//	          while (length > 1) {
//	               System.out.print((length - 1) + " ");
//	               xMethod(length - 1);
//	          }
//	          
		if (length > 1) {
			System.out.print((length - 1) + " ");
			xMethod(length - 1);
		}
	}
}
