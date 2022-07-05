/**
 * @author yongzhi xu
 *
 */
public class RecursiveSyntax {

	private String[] conjunction = { "and", "or", "but", "because" };
	private String[] proper_noun = { "Fred", "Jane", "Richard Nixon", "Miss America" };
	private String[] common_noun = { "man", "woman", "fish", "elephant", "unicorn" };
	private String[] determiner = { "a", "the", "every", "some" };
	private String[] adjective = { "big", "tiny", "pretty", "bald" };
	private String[] intransitive_verb = { "runs", "jumps", "talks", "sleeps" };
	private String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds" };

	public static void main(String[] argStrings) {
//		using constructor to initial the program
		new RecursiveSyntax();
	}

	/**
	 * randomly select an item from a list
	 * 
	 * @param listOfStrings
	 * @return
	 */
	String randomItem(String[] listOfStrings) {
		return listOfStrings[(int) (Math.random() * listOfStrings.length)] + " ";
	}

	/**
	 * constructor
	 */
	public RecursiveSyntax() {
		this.sentence();
	}

	/**
	 * create random sentence
	 */
	private void sentence() {
		System.out.print(this.simple_sentence());
		if (Math.random() > 0.9) {
			System.out.print(this.randomItem(conjunction));
			sentence();
		}
	}

	/**
	 * create random simple sentence
	 * @return
	 */
	private String simple_sentence() {
		return this.noun_phrase() + this.verb_phrase();
	}

	
	/**
	 * create verb phrase
	 * @return
	 */
	private String verb_phrase() {
		int lottery = (int) (Math.random() * 4);
		switch (lottery) {
		case 0:
			return this.randomItem(intransitive_verb);
		case 1:
			return this.randomItem(transitive_verb) + this.noun_phrase();
		case 2:
			return "is " + this.randomItem(adjective);
		case 3:
//			where recursion happens
			return "believes that " + this.simple_sentence();
		default:
			return " ";
		}
	}

	/**
	 * create noun phrase
	 * @return
	 */
	private String noun_phrase() {
		String noun_phraseString = "";
		if (Math.random() < 0.75)
			noun_phraseString += this.randomItem(proper_noun);
		else {
			noun_phraseString += this.randomItem(determiner);
			if (Math.random() > 0.6) {
				noun_phraseString += this.randomItem(adjective);
			}
			noun_phraseString += this.randomItem(common_noun);
			if (Math.random() > 0.9) {
				noun_phraseString += "who ";
//				where recursion happens
				noun_phraseString += this.verb_phrase();
			}
		}
		return noun_phraseString;
	}

}
