import java.util.Date;
//import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * formating the data from files
 *
 */
public class Profile implements Comparable<Object> {

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Profile profile = (Profile)o;
		return profile.getSex().compareTo(this.sex);
	}

	private String firstName;
	private String lastName;
	private String sex;
	private Date birthday;
	private String favoriteColor;

	String getName() {
		return getFirstName() + " " + getLastName();
	}

	String getFirstName() {
		return firstName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	String getLastName() {
		return lastName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	String getSex() {
		return sex;
	}

	void setSex(String sex) {
		if (sex.toLowerCase().equals("m")) {
			this.sex = "Male";
		} else if (sex.toLowerCase().equals("f")) {
			this.sex = "Female";
		} else {
			this.sex = sex;
		}
	}

	Date getBirthday() {
		return birthday;
	}
	String showBirthday() {
		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dFormat.format(this.birthday);
	}

	void setBirthday(String birthday) {

		Calendar calendar = Calendar.getInstance();
		String[] dateStrings;
		if (birthday.contains("-")) {
			dateStrings = birthday.split("-");
			try {
				calendar.set(Integer.valueOf(dateStrings[2]), Integer.valueOf(dateStrings[0])-1,
						Integer.valueOf(dateStrings[1]));
				this.birthday = calendar.getTime();
			} catch (Exception e) {
				// TODO: handle exception
				this.birthday = new Date();
			}
		} else if (birthday.contains("/")) {
			dateStrings = birthday.split("/");
			try {
				calendar.set(Integer.valueOf(dateStrings[2]), Integer.valueOf(dateStrings[0])-1,
						Integer.valueOf(dateStrings[1]));
				this.birthday = calendar.getTime();
			} catch (Exception e) {
				// TODO: handle exception
				this.birthday = new Date();
			}
		} else {
			this.birthday = new Date();
		}
	}

	String getFavoriteColor() {
		return favoriteColor;
	}

	void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
}
