package models;

public class Account {
	private int accountID;
    private String username;
    private String password;
    private int roleID;

    // Constructor
    public Account() {
    }

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public Account(int accountID, String username, String password, int roleID) {
		super();
		this.accountID = accountID;
		this.username = username;
		this.password = password;
		this.roleID = roleID;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", username=" + username + ", password=" + password + ", roleID="
				+ roleID + "]";
	}
    
}
