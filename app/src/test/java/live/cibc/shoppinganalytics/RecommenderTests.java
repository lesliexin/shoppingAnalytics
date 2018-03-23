package live.cibc.shoppinganalytics;

import junit.framework.Assert;
import junit.framework.TestCase;
//import org.junit.Test;


import org.junit.Test;

import live.cibc.shoppinganalytics.model.Model;
import live.cibc.shoppinganalytics.model.ModelFactory;
import live.cibc.shoppinganalytics.recommender.API;
import live.cibc.shoppinganalytics.recommender.DisplayFilter;
import live.cibc.shoppinganalytics.recommender.HistoryDisplayObject;
import live.cibc.shoppinganalytics.recommender.Recommendation;
import live.cibc.shoppinganalytics.recommender.Recommender;
import live.cibc.shoppinganalytics.recommender.SavingsDisplayObject;
import live.cibc.shoppinganalytics.recommender.Tailor;

import java.util.List;

public class RecommenderTests extends TestCase {

    @Test
    public void tailorTest(){
        Model model = ModelFactory.createModel();
        Model tailoredModel = Tailor.tailorToCustomer(model);
        Assert.assertNotNull(tailoredModel.categories.get(0).retailers.get(0).name);
    }

    @Test
    public void recommenderTest(){
        Model model = ModelFactory.createModel();
        Model tailoredModel = Tailor.tailorToCustomer(model);
        List<Recommendation> recommendations = Recommender.getRecommendations(4,5,tailoredModel);
        Assert.assertFalse(recommendations.isEmpty());
    }

    @Test
    public void displayFilterTest(){
        Model model = ModelFactory.createModel();
        Model tailoredModel = Tailor.tailorToCustomer(model);
        List<Recommendation> recommendations = Recommender.getRecommendations(4,5,tailoredModel);
        SavingsDisplayObject savingsDisplayObject = DisplayFilter.getDisplayObject(recommendations);
        Assert.assertNotNull(savingsDisplayObject.other);
    }

    @Test
    public void apiTest1() {
        SavingsDisplayObject response = API.requestSavings(20);
        for (Recommendation recommendation : response.dining) {
            print(recommendation);
        }
        for (Recommendation recommendation : response.entertainment) {
            print(recommendation);
        }
        for (Recommendation recommendation : response.clothing) {
            print(recommendation);
        }
        for (Recommendation recommendation : response.groceries) {
            print(recommendation);
        }
        for (Recommendation recommendation : response.other) {
            print(recommendation);
        }
    }

    @Test
    public void apiTest2(){
        HistoryDisplayObject historyDisplayObject = API.requestHistory(5);
        System.out.println(historyDisplayObject.diningPercent);
        System.out.println(historyDisplayObject.entertaimentPercent);
        System.out.println(historyDisplayObject.clothingPercent);
        System.out.println(historyDisplayObject.groceriesPercent);
        System.out.println(historyDisplayObject.otherPercent);
    }

    public static void print(Recommendation recommendation){
        System.out.println(recommendation.retailerName + " " +
                recommendation.categoryName + " " +
                recommendation.percentBelowAvg + "%OFF " +
                recommendation.weeksFromNow + "w " +
                recommendation.percentCertainty + "%certainty");
    }
}