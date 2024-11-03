package models;

public class Customer {
    private int customerID;
    private int accountID;
    private String firstName;
    private String lastName;
    private String from;
    private String address;
    private String phoneNumber;
    private String email;
    private String profileImagePath;

    // Constructor
    public Customer() {
    }

    public Customer(int customerID, int accountID, String firstName, String lastName, String from, String address, String phoneNumber, String email, String profileImagePath) {
        this.customerID = customerID;
        this.accountID = accountID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.from = from;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImagePath = profileImagePath;
    }

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}

