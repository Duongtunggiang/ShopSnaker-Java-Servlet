package models;

public class Admin {
	private int AdminID;
	private int accountID;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImagePath;
	public Admin() {
		
	}
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	public Admin(int adminID, int accountID, String firstName, String lastName, String email, String profileImagePath) {
		super();
		AdminID = adminID;
		this.accountID = accountID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImagePath = profileImagePath;
	}
    
    
    
}
