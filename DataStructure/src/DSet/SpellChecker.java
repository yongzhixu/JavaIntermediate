package DSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;

public class SpellChecker {

	private static HashSet<String> dictionaryHashSet = new HashSet<String>();

	public static void main(String[] argStrings) {
		new SpellChecker();
	}

	public SpellChecker() {
		wordsImport();
		checkWords();
	}

	/**
	 * import file words.txt and store the items in a hash set
	 */
	private void wordsImport() {
		try {
			Scanner filein = new Scanner(new File("src/DSet/words.txt"));
			while (filein.hasNext()) {
				String tk = filein.next();
				tk = tk.toLowerCase();
				dictionaryHashSet.add(tk);
			}
			filein.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		System.out.println("size of dictionary set is: " + dictionaryHashSet.size());
	}

	/**
	 * Lets the user select an input file using a standard file selection dialog
	 * box. If the user cancels the dialog without selecting a file, the return
	 * value is null.
	 */
	private File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Input");
		int option = fileDialog.showOpenDialog(null);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileDialog.getSelectedFile();
	}

	/**
	 * check the word from the file, if it is not in the dictionary. then 1) print
	 * it out; 2) give a list of possible correct spellings.
	 */
	private void checkWords() {
		File file = getInputFileNameFromUser();
		try {
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter("[^a-zA-Z]+");
			while (scanner.hasNext()) {
				String tk = scanner.next();
				tk = tk.toLowerCase();
				if (!dictionaryHashSet.contains(tk)) {
//					System.out.println(tk + " is not in the dictionary.");
					corrections(tk.toLowerCase(), dictionaryHashSet);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	
	private TreeSet<String> corrections(String badWord, HashSet dictionary) {
		TreeSet<String> suggestionSet = new TreeSet<String>();
//		try delete one of the letters
		for (int i = 0; i < badWord.length(); i++) {
			String newWord = badWord.substring(0, i) + badWord.substring(i + 1);
			if (dictionary.contains(newWord)) {
				suggestionSet.add(newWord);
			}
		}
// Change any letter in the misspelled word to any other letter. 
		for (int i = 0; i < badWord.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				String newWord = badWord.substring(0, i) + ch + badWord.substring(i + 1);
				if (dictionary.contains(newWord)) {
					suggestionSet.add(newWord);
				}
			}
		}
//		Insert any letter at any point in the misspelled word. 
		for (int i = 0; i < badWord.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				String newWord = badWord.substring(0, i) + ch + badWord.substring(i);
				if (dictionary.contains(newWord)) {
					suggestionSet.add(newWord);
				}
			}
		}

//		Swap any two neighboring characters in the misspelled word. 
		for (int i = 0; i < badWord.length() - 1; i++) {
			char letterP = badWord.charAt(i);
			char letterN = badWord.charAt(i + 1);
			String newWord = badWord.substring(0, i) + letterN + letterP + badWord.substring(i + 2);
			if (dictionary.contains(newWord)) {
				suggestionSet.add(newWord);
			}
		}

//		Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary) 
		for (int i = 0; i < badWord.length(); i++) {
			String newWord = badWord.substring(0, i);
			String newword2 = badWord.substring(i);
			if (dictionary.contains(newWord)) {
				suggestionSet.add(newWord);
			}
			if (dictionary.contains(newword2)) {
				suggestionSet.add(newword2);
			}
		}
		if (suggestionSet.size() == 0) {
			suggestionSet.add("(no suggestions)");
		}
		System.out.print(badWord + ":");
		for (String w : suggestionSet) {
			System.out.print(" " + w);
		}
		System.out.println();
		return suggestionSet;
	}
}
