package models;

public class Category {
    private int categoryID;
    private String categoryName;

    // Constructor
    public Category() {
    }

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    // Getters and Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

