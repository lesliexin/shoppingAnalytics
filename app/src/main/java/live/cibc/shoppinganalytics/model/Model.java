package live.cibc.shoppinganalytics.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Category> categories = new ArrayList<>();

    public void addRetailer(Retailer retailer, String categoryOfRetailer){
        boolean categoryFound = false;
        for(Category category : categories){
            if(category.name.equals(categoryOfRetailer)) {
                category.retailers.add(retailer);
                categoryFound = true;
            }
        }
        if(!categoryFound){
            Category category = new Category(categoryOfRetailer);
            category.retailers.add(retailer);
            categories.add(category);
        }
    }

}
