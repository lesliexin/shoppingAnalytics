package live.cibc.shoppinganalytics.model;

import android.graphics.Point;

import java.util.ArrayList;

import java.util.List;

public class Retailer {

    public List<Point> perUnitWeeklyAvgs = new ArrayList<>();
    public String name;

    Retailer(String name){
        this.name = name;
    }

    public void addPoint(int x, int y){
        assert x <= 52;
        perUnitWeeklyAvgs.add(new Point(x,y));
    }
}
