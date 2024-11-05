package models;

public class Category {
    private int categoryID;
    private String categoryName;
    private String BrandImagePath;
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
	public String getBrandImagePath() {
		return BrandImagePath;
	}
	public void setBrandImagePath(String brandImagePath) {
		BrandImagePath = brandImagePath;
	}
	public Category(int categoryID, String categoryName, String brandImagePath) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		BrandImagePath = brandImagePath;
	}

    public Category() {}
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + ", BrandImagePath="
				+ BrandImagePath + "]";
	}
    
}

