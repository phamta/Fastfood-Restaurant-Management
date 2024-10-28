package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Category_DAL;
import DTO.Category;

public class Category_BLL {
	private static Category_BLL instance;

	private Category_BLL() {

	}

	public static Category_BLL getInstance() {
		if (instance == null) {
			instance = new Category_BLL();
		}
		return instance;
	}
	
	public List<Category> getAllCategory(){
		return Category_DAL.getInstance().getAllCategory();
	}
	
	public Category getCategoryBuID(String categoryid) {
		int id = Integer.parseInt(categoryid);
		Category category = null;
		for(Category temp: getAllCategory()) {
			if(temp.getCategoryID() == id) {
				category = temp;
				break;
			}
		}
		return category;
	}
	
	public void insertCategory(Category category) {
		Category_DAL.getInstance().insertCategory(category);
	}
	
	public void updateCategory(Category category) {
		Category_DAL.getInstance().updateCategory(category);
	}
	
	public List<Category> searchCategoryByName(String search) {
        List<Category> allCategories = getAllCategory();
        List<Category> result = new ArrayList<Category>();

        for (Category category : allCategories) {
            if (category.getCategoryName().toLowerCase().contains(search.toLowerCase())) {
                result.add(category);
            }
        }
        return result;
    }

}
