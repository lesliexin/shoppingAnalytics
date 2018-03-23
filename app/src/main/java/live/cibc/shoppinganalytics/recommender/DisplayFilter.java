package live.cibc.shoppinganalytics.recommender;

import java.util.List;

public class DisplayFilter {

    public static SavingsDisplayObject getDisplayObject(List<Recommendation> recommendations){
        SavingsDisplayObject savingsDisplayObject = new SavingsDisplayObject();
        for(Recommendation recommendation : recommendations) {
            savingsDisplayObject.addRecommendation(recommendation);
        }
        return savingsDisplayObject;
    }
}
