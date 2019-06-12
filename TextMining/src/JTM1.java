import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.InputMismatchException;
import java.util.Scanner;

import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class JTM1 {

	public static void main(String[] argStrings) {
		try {
			Scanner scanner = new Scanner(System.in);

			String[] dataStrings = null;
			String isExit = "n";
			while (isExit.equals("n")) {
				System.out.println("1. Text importing;\n2. Text transformation;\\n3. Text exploration: POS tagging;\n4. Text exploration: POS tagging;\n5. Text classification");
				int option = scanner.nextInt();
				System.out.println("option: " + option);
				switch (option) {
				case 1:
//					System.out.println("enter the file directory:\n");
//					String directoryString = scanner.nextLine();
//					directoryString = scanner.nextLine();
//				System.out.println("directory:"+directoryString);
//					File dirFile = new File(directoryString);
					File dirFile = new File("data/OpinosisDataset1.0_0/summaries-gold/accuracy_garmin_nuvi_255W_gps");
					File[] files = dirFile.listFiles();
					dataStrings = new String[files.length];
					for (int i = 0; i < files.length; i++) {
						BufferedReader bReader = new BufferedReader(new FileReader(files[i]));
						String conteString = "";
						String lineString = bReader.readLine();
						while (lineString != null) {
							conteString += lineString;
							lineString = bReader.readLine();
						}
						bReader.close();
						dataStrings[i] = conteString;
						System.out.println(conteString);
					}
					break;
				case 2:
					System.out.println("do you want to Converting to lower case?(y/n) ");
					String userString = scanner.next();
					if (userString.contentEquals("y")) {
						for (int i = 0; i < dataStrings.length; i++) {
							dataStrings[i] = dataStrings[i].toLowerCase();
							System.out.println(dataStrings[i]);
						}
					}
					System.out.println("do you want to Converting to upper case?(y/n) ");
					userString = scanner.next();
					if (userString.contentEquals("y")) {
						for (int i = 0; i < dataStrings.length; i++) {
							dataStrings[i] = dataStrings[i].toUpperCase();
							System.out.println(dataStrings[i]);
						}
					}
					System.out.println("do you want to remove stop words?(y/n) ");
					userString = scanner.next();
					if (userString.contentEquals("y")) {
						TextParsing textParsing = new TextParsing();
						for (int i = 0; i < dataStrings.length; i++) {
							dataStrings[i] = textParsing.removeStopWords(dataStrings[i]);
							System.out.println(dataStrings[i]);
						}
					}

					System.out.println("do you want to Converting to titleCase case?(y/n) ");
					userString = scanner.next();
					if (userString.contentEquals("y")) {
						TextParsing textParsing = new TextParsing();
						for (int i = 0; i < dataStrings.length; i++) {
							dataStrings[i] = textParsing.toTitleCase(dataStrings[i]);
							System.out.println(dataStrings[i]);
						}
					}
					System.out.println("do you want to remove whitespace and punctuations?(y/n) ");
					userString = scanner.next();
					if (userString.contentEquals("y")) {
						TextParsing textParsing = new TextParsing();
						for (int i = 0; i < dataStrings.length; i++) {
							dataStrings[i] = textParsing.rmSpacePunc(dataStrings[i]);
							System.out.println(dataStrings[i]);
						}
					}
					break;

				case 3:
					MaxentTagger tagger = new MaxentTagger("C:\\coding\\gate\\stanford-postagger-2018-10-16\\models\\english-left3words-distsim.tagger");
					for (int i = 0; i < dataStrings.length; i++) {
						dataStrings[i] = tagger.tagString(dataStrings[i]);
						System.out.println(dataStrings[i]);
					}
					break;

				case 4:
					String modelString = "C:\\coding\\gate\\stanford-ner-2018-10-16\\classifiers\\english.all.3class.distsim.crf.ser.gz";
					AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(modelString);
					for (int i = 0; i < dataStrings.length; i++) {
						dataStrings[i] = classifier.classifyToString(dataStrings[i]);
						System.out.println(dataStrings[i]);
					}

					break;

				case 5:
					String properityfile = "C:\\coding\\gate\\stanford-classifier-2018-10-16\\examples\\cheese2007.prop";
					String trainfile = "C:\\coding\\gate\\stanford-classifier-2018-10-16\\examples\\cheeseDisease.train";
					String testfile = "C:\\coding\\gate\\stanford-classifier-2018-10-16\\examples\\cheeseDisease.test";

					ColumnDataClassifier clsClassifier = new ColumnDataClassifier(properityfile);
					clsClassifier.trainClassifier(trainfile);
					
					for (String lineString : ObjectBank.getLineIterator(testfile,"utf-8")) {
						Datum<String, String> datum = clsClassifier.makeDatumFromLine(lineString);
						System.out.println(lineString+">="+clsClassifier.classOf(datum)+"\t"+
						clsClassifier.scoresOf(datum).getCount(clsClassifier.classOf(datum)));
					}
					break;
				default:
					break;
				}
				System.out.println("do you want to exit the program?(y/n) ");
				isExit = scanner.next();
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("program exited!");
		}

	}
}
