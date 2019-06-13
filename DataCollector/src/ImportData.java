import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * reading the data from files each with a different separator
 * 
 * @author s
 *
 */
public class ImportData {
	// ArrayList variable, to store data from files in a specific order
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	String filesFolder = "src/input_files"; // where files are

	public static void main(String[] argStrings) {
		new ImportData();
	}

	/**
	 * constructor
	 */
	public ImportData() {
		displayData();
	}

	/**
	 * A comparator class for comparing objects of type Profile according to their
	 * sex and firstName. This is used for sorting the list of Profile by thus two
	 * fields.
	 */
	private class SexNameCompare implements Comparator<Profile> {
		public int compare(Profile profile1, Profile profile2) {
			int i = profile1.getSex().compareTo(profile2.getSex());
			if (i != 0) {
				return i;
			}
			i = profile1.getFirstName().compareTo(profile2.getFirstName());
			return i;
		}
	} // end class SexNameCompare

	/**
	 * A comparator class for comparing objects of type Profile according to their
	 * firstName in descending order. This is used for sorting the list of Profile
	 * by firstName.
	 */
	private class ReverseNameCompare implements Comparator<Profile> {
		public int compare(Profile profile1, Profile profile2) {
			int i = profile1.getFirstName().compareTo(profile2.getFirstName());
			return 0 - i;
		}
	} // end class ReverseNameCompare

	/**
	 * A comparator class for comparing objects of type Profile according to their
	 * birthday and first name. This is used for sorting the list of Profile thus.
	 */
	private class BirthCompare implements Comparator<Profile> {
		public int compare(Profile profile1, Profile profile2) {
			int i = profile1.getBirthday().compareTo(profile2.getBirthday());
			if (i != 0) {
				return i;
			}
			i = profile1.getFirstName().compareTo(profile2.getFirstName());
			return i;
		}
	} // end class BirthCompare

	/**
	 * display the data while sorting by different requirements, first name,
	 * birthday or sex
	 */
	private void displayData() {
		getData();// get the data from files and then store them in list profiles

		Collections.sort(profiles, new SexNameCompare());
		System.out.println("Output 1:");
		for (Profile profile : profiles) {
			System.out.println(profile.getName() + " " + profile.getSex() + " " + profile.showBirthday() + " "
					+ profile.getFavoriteColor());
		}
		System.out.println();

		Collections.sort(profiles, new BirthCompare());
		System.out.println("Output 2:");
		for (Profile profile : profiles) {
			System.out.println(profile.getName() + " " + profile.getSex() + " " + profile.showBirthday() + " "
					+ profile.getFavoriteColor());
		}
		System.out.println();

		Collections.sort(profiles, new ReverseNameCompare());
		System.out.println("Output 3:");
		for (Profile profile : profiles) {
			System.out.println(profile.getName() + " " + profile.getSex() + " " + profile.showBirthday() + " "
					+ profile.getFavoriteColor());
		}
		System.out.println();
	}

	/**
	 * get the data from files and then store them in list profiles
	 */
	public void getData() {
		File file = new File(filesFolder);
		String[] files = file.list();
		for (String string : files) {
			file = new File(filesFolder + "/" + string);
			if (file.exists()) {
				try {
					Scanner scanner = new Scanner(file);
					switch (file.getName().charAt(0)) {
					case 'c':
						while (scanner.hasNext()) {
							String[] info = scanner.nextLine().split(",");
							Profile profile = new Profile();
							profile.setFirstName(info[0].trim());
							profile.setLastName(info[1].trim());
							profile.setSex(info[2].trim());
							profile.setFavoriteColor(info[3].trim());
							profile.setBirthday(info[4].trim());
							profiles.add(profile);
						}
						break;

					case 'p':
						while (scanner.hasNext()) {
							String[] info = scanner.nextLine().split("\\|");
							Profile profile = new Profile();
							profile.setFirstName(info[0].trim());
							profile.setLastName(info[1].trim());
							profile.setSex(info[3].trim());
							profile.setFavoriteColor(info[4].trim());
							profile.setBirthday(info[5].trim());
							profiles.add(profile);
						}
						break;

					case 's':
						while (scanner.hasNext()) {
							String[] info = scanner.nextLine().split(" ");
							Profile profile = new Profile();
							profile.setFirstName(info[0].trim());
							profile.setLastName(info[1].trim());
							profile.setSex(info[3].trim());
							profile.setFavoriteColor(info[5].trim());
							profile.setBirthday(info[4].trim());
							profiles.add(profile);
						}
						break;

					default:
						break;
					}
					scanner.close();
				} catch (IOException e) {
					// TODO: handle exception
				} finally {

				}

			}
		}
	}

}
