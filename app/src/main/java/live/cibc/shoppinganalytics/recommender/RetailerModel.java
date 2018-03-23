package live.cibc.shoppinganalytics.recommender;

import android.graphics.Point;

import live.cibc.shoppinganalytics.model.Retailer;

public class RetailerModel {

    public String name;
    public String categoryName;
    private PolynomialFitter model = new PolynomialFitter(15);
    public double average;

    public RetailerModel(String name, Retailer retailer, String categoryName){
        this.name = name;
        this.categoryName = categoryName;
        double average = 0;
        int totalPoints = 0;
        for(Point point : retailer.perUnitWeeklyAvgs){
            model.addPoint(point.x, point.y);
            average += point.y;
            totalPoints++;
        }
        this.average = average/(1.0*totalPoints);
    }

    public int getSalePrediction(int week){
        PolynomialFitter.Polynomial polynomial = model.getBestFit();
        int dummyInt = (int)Math.round(100*polynomial.getY(1.0*week)/average);
        return (int)Math.round(100*polynomial.getY(1.0*week)/average);
    }

    public int getUpcomingSalePrediction(int weeks, int currentWeek){
        int bestSale = 200;//200% of average price (relative infinite first guess)
        for(int i = 0; i < weeks; i++){
            int saleOfWeek = getSalePrediction(currentWeek+i);
            if(saleOfWeek < bestSale) bestSale = saleOfWeek;
        }
        return bestSale;
    }

    public int bestSaleWeeksFromNow(int weeks, int currentWeek){
        int bestSale = 200;//200% of average price (relative infinite first guess)
        int bestSaleWeek = 0;
        for(int i = 0; i < weeks; i++){
            int saleOfWeek = getSalePrediction(currentWeek+i);
            if(saleOfWeek < bestSale){
                bestSale = saleOfWeek;
                bestSaleWeek = i;
            }
        }
        return bestSaleWeek;
    }
}
