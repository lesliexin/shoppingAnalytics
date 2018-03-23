package live.cibc.shoppinganalytics.recommender;

import live.cibc.shoppinganalytics.Utils.PropertiesParser;
import live.cibc.shoppinganalytics.model.Model;
import live.cibc.shoppinganalytics.model.ModelFactory;

import java.io.File;
import java.util.*;

public class API {

    public static SavingsDisplayObject requestSavings(int timePeriodWeeks){
        Model model = ModelFactory.createModel();
        Model tailoredModel = Tailor.tailorToCustomer(model);
        List<Recommendation> recommendations = Recommender.getRecommendations(timePeriodWeeks,getCurrentWeek(),tailoredModel);
        return DisplayFilter.getDisplayObject(recommendations);
    }

    private static int getCurrentWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static HistoryDisplayObject requestHistory(int timePeriodWeeks){
        Properties history = PropertiesParser.getProperties((new File("shoppinganalytics/recommender/history.properties")).getAbsolutePath());
        List<String> historyStrings = new ArrayList<>();
        boolean retrieved = false;
        int i = 1;
        while(!retrieved){
            String purchaseString = history.getProperty(Integer.toString(i));
            if(purchaseString != null){
                historyStrings.add(purchaseString);
                i++;
            } else {
                retrieved = true;
            }
        }
        double dining = 0;
        double entertainment = 0;
        double clothing = 0;
        double groceries = 0;
        double other = 0;
        double total = 0;
        for(String item : historyStrings){
            total += Double.parseDouble(item.split(" ")[2]);
            String category = item.split(" ")[1];
            switch(category){
                case "Dining":
                    dining += Double.parseDouble(item.split(" ")[2]);
                    break;
                case "Entertainment":
                    entertainment += Double.parseDouble(item.split(" ")[2]);
                    break;
                case "clothing":
                    clothing += Double.parseDouble(item.split(" ")[2]);
                    break;
                case "Groceries":
                    groceries += Double.parseDouble(item.split(" ")[2]);
                    break;
                case "Other":
                    other += Double.parseDouble(item.split(" ")[2]);
                    break;
            }
        }
        HistoryDisplayObject response = new HistoryDisplayObject();
        response.diningPercent = (int) Math.round(100*dining/total);
        response.entertaimentPercent = (int) Math.round(100*entertainment/total);
        response.clothingPercent = (int) Math.round(100*clothing/total);
        response.groceriesPercent = (int) Math.round(100*groceries/total);
        response.otherPercent = (int) Math.round(100*other/total);
        return response;
    }
}
