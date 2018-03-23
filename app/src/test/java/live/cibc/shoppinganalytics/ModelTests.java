package live.cibc.shoppinganalytics;

import junit.framework.TestCase;

import org.junit.Test;

import live.cibc.shoppinganalytics.model.Model;
import live.cibc.shoppinganalytics.model.ModelFactory;

public class ModelTests extends TestCase {

    @Test
    public void modelFactoryTest() {
        Model model = ModelFactory.createModel();
        System.out.println(model.categories.get(0).retailers.get(0).name);
    }

    @Test
    public void modelTest() {
    }
    @Test
    public void retailerTest() {
    }

}
