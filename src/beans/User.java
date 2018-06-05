package beans;

public class User {
	private String userID;
	private String username;
	private String useraccount;
	private String userpassword;
	
	public User() {}

	public User(String userID, String username, String useraccount, String userpassword) {
		super();
		this.userID = userID;
		this.username = username;
		this.useraccount = useraccount;
		this.userpassword = userpassword;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	
	
}
