package live.cibc.shoppinganalytics.recommender;

import java.util.concurrent.ThreadLocalRandom;

public class Recommendation {


    public String retailerName;
    public String categoryName;
    public int weeksFromNow;
    public int percentBelowAvg;
    public int percentCertainty = ThreadLocalRandom.current().nextInt(30, 100);

    public Recommendation(String retailerName, String categoryName, int percentBelowAvg, int weeksFromNow){
        this.retailerName = retailerName;
        this.categoryName = categoryName;
        this.weeksFromNow = weeksFromNow;
        this.percentBelowAvg = percentBelowAvg;
    }
}
