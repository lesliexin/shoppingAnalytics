package live.cibc.shoppinganalytics.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    public String name;
    public List<Retailer> retailers = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
