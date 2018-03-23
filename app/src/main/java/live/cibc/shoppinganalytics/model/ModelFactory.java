package live.cibc.shoppinganalytics.model;

import live.cibc.shoppinganalytics.Utils.PropertiesParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ModelFactory {

    public static Model createModel(){
        Model model = new Model();
        File propertiesFolder = new File("shoppinganalytics/model/assets");
        List<Properties> retailersData = new ArrayList<>();
        for(File file : propertiesFolder.listFiles()){
            retailersData.add(PropertiesParser.getProperties(file.getAbsolutePath()));
        }

        for(Properties retailerData : retailersData){
            Retailer retailer = new Retailer(retailerData.getProperty("name"));
            String category = retailerData.getProperty("type");
            boolean retrieved = false;
            int i = 1;
            while(!retrieved){
                String pointString = retailerData.getProperty(Integer.toString(i));
                if(pointString != null){
                    String[] pointArray = pointString.split(" ");
                    retailer.addPoint(Integer.parseInt(pointArray[0])*2, Integer.parseInt(pointArray[1]));//*2 spreads across 40 weeks rather than 20
                    i++;
                } else {
                    retrieved = true;
                }
            }
            model.addRetailer(retailer, category);
        }
        return model;
    }
}
