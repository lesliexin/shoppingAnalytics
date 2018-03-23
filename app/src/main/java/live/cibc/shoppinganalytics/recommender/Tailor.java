package live.cibc.shoppinganalytics.recommender;

import live.cibc.shoppinganalytics.Utils.PropertiesParser;
import live.cibc.shoppinganalytics.model.Category;
import live.cibc.shoppinganalytics.model.Model;
import live.cibc.shoppinganalytics.model.Retailer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Tailor {

    public static Model tailorToCustomer(Model model){
        Properties history = PropertiesParser.getProperties((new File("shoppinganalytics/recommender/history.properties")).getAbsolutePath());
        List<String> historyRetailerNames = new ArrayList<>();
        boolean retrieved = false;
        int i = 1;
        while(!retrieved){
            String purchaseString = history.getProperty(Integer.toString(i));
            if(purchaseString != null){
                String[] purchaseArray = purchaseString.split(" ");
                historyRetailerNames.add(purchaseArray[0]);
                i++;
            } else {
                retrieved = true;
            }
        }

        //Iterator avoids ConcurrentModificationException
        for(Category category : model.categories){
            Iterator<Retailer> retailerIterator = category.retailers.iterator();
            while(retailerIterator.hasNext()){
                Retailer retailer = retailerIterator.next();
                boolean found = false;
                for(String retailerOfPurchase : historyRetailerNames){
                    if(retailerOfPurchase.equals(retailer.name)) found = true;
                }
                if(!found){
                    retailerIterator.remove();
                }
            }
        }

        return model;
    }
}
