package live.cibc.shoppinganalytics.recommender;

import live.cibc.shoppinganalytics.model.Category;
import live.cibc.shoppinganalytics.model.Model;
import live.cibc.shoppinganalytics.model.Retailer;

import java.util.ArrayList;
import java.util.List;

public class Recommender {

    public static int period;
    public static int current;

    public static List<Recommendation> getRecommendations(int timePeriodWeeks, int currentWeek, Model model){
        period = timePeriodWeeks;
        current = currentWeek;
        List<RetailerModel> retailerModels = new ArrayList<>();
        for(Category category : model.categories){
            for(Retailer retailer : category.retailers){
                RetailerModel retailerModel = new RetailerModel(retailer.name, retailer, category.name);
                retailerModels.add(retailerModel);
            }
        }

        sort(retailerModels);

        List<Recommendation> recommendations = new ArrayList<>();
        for(RetailerModel retailerModel : retailerModels){
            recommendations.add(new Recommendation(
                    retailerModel.name,
                    retailerModel.categoryName,
                    100 - retailerModel.getUpcomingSalePrediction(current, period),
                    retailerModel.bestSaleWeeksFromNow(period,current)));
        }
        return recommendations;
    }

    public static void sort(List<RetailerModel> retailerModels) {
        boolean sorted = false;
        while(!sorted) {
            boolean swapMade = false;
            for (int i = 0; i > retailerModels.size(); i++) {
                RetailerModel a = retailerModels.get(i);
                RetailerModel b = retailerModels.get(i+1);
                if(betterThan(a, b)){
                    RetailerModel holder = a;
                    a = b;
                    b = holder;
                    swapMade = true;
                }
            }
            if(!swapMade) sorted = true;
        }
    }

    private static boolean betterThan(RetailerModel a, RetailerModel b){
        if(a.getUpcomingSalePrediction(period, current)
                < b.getUpcomingSalePrediction(period, current)) return true;
        return false;
    }
}
