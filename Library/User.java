package Library;

public class User {

	private String userID;
	private String userFName;
	private String userLName;
	private String userEmail;

	public User(String userID, String userFName, String userLName, String userEmail) {
		this.userID = userID;
		this.userFName = userFName;
		this.userLName = userLName;
		this.userEmail = userEmail;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getUserFName() {
		return this.userFName;
	}

	public String getUserLName() {
		return this.userLName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String toString() {
		String userDetails = "User Details:\n";
		userDetails += "User ID: " + getUserID() + "\n";
		userDetails += "First Name: " + getUserFName() + "\n";
		userDetails += "Last Name: " + getUserLName() + "\n";
		userDetails += "Email: " + getUserEmail() + "\n";
		return userDetails;
	}

}