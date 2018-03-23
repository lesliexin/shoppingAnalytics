package live.cibc.shoppinganalytics.recommender;

import java.util.ArrayList;
import java.util.List;

public class SavingsDisplayObject {

    public List<Recommendation> dining = new ArrayList<>();
    public List<Recommendation> entertainment = new ArrayList<>();
    public List<Recommendation> clothing = new ArrayList<>();
    public List<Recommendation> groceries = new ArrayList<>();
    public List<Recommendation> other = new ArrayList<>();

    public void addRecommendation(Recommendation recommendation){
        if(recommendation.categoryName.equals("Dining")){
            if(dining.size() < 3 && recommendation.percentBelowAvg > 0) dining.add(recommendation);
        } else if(recommendation.categoryName.equals("Entertainment")){
            if(entertainment.size() < 3 && recommendation.percentBelowAvg > 0) entertainment.add(recommendation);
        } else if(recommendation.categoryName.equals("clothing")){
            if(clothing.size() < 3 && recommendation.percentBelowAvg > 0) clothing.add(recommendation);
        } else if(recommendation.categoryName.equals("Groceries")){
            if(groceries.size() < 3 && recommendation.percentBelowAvg > 0) groceries.add(recommendation);
        } else if(recommendation.categoryName.equals("Other")){
            if(other.size() < 3 && recommendation.percentBelowAvg > 0) other.add(recommendation);
        }
    }
}
