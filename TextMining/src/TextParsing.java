import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author 86188
 *
 */
public class TextParsing {
	
	public static void main(String[] args) 
    { 
        // input string 
        String str = "Welcome???@@##$    to#$% Geeks%$^for$%^&Geeks"; 
          
        // similar to Matcher.replaceAll 
          
        System.out.println(str); 
    }

	public String stemming(String sentence) {
//		https://nlp.stanford.edu/IR-book/html/htmledition/stemming-and-lemmatization-1.html
		String[] sentenceArr = sentence.split(" ");
		
		return sentence;
	}
	
	public String rmSpacePunc(String sentence) {		
		// remove whitespaces
		sentence=sentence.replaceAll(" ", "");
		// remove punctuations
        // similar to Matcher.replaceAll 
		sentence = sentence.replaceAll("\\p{Punct}",""); 
		return sentence;
	}

	/**
	 * @param sentence
	 * @return
	 */
	public String removeStopWords(String sentence) {
		try {
//	    String basePath = new File("").getAbsolutePath();
//	    System.out.println(basePath);
//	    String path = new File("data/stopWords.gold").getAbsolutePath();
//	    System.out.println(path);
			File stopWords = new File("data/stopWords.gold");
			BufferedReader bReader = new BufferedReader(new FileReader(stopWords));
			String fileString = "";
			String lineString = bReader.readLine();
			while (lineString != null) {
				fileString += lineString;
				lineString = bReader.readLine();
			}
			bReader.close();
			String[] stopwordStrings = fileString.split(",");
			for (int j = 0; j < stopwordStrings.length; j++) {
				sentence = sentence.replace(stopwordStrings[j], "");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sentence;
	}

	/**
	 * @param sentence
	 * @return
	 */
	public String toTitleCase(String sentence) {
		// TODO Auto-generated method stub

		// Create a char array of given String
		char ch[] = sentence.toCharArray();
		for (int i = 0; i < sentence.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		return st;
	}
}
