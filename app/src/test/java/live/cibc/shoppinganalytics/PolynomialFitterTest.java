package live.cibc.shoppinganalytics;

import org.junit.Test;

import java.lang.*;

public class PolynomialFitterTest {

    @Test
    public void getBestFit() {
        double y = 0;
        PolynomialFitter polynomialFitter = new PolynomialFitter(4);

        for (int i = 0; i < 7; i++){
            y = Math.pow(i*1.0,4.0);
            polynomialFitter.addPoint(i,y);
        }

        PolynomialFitter.Polynomial polynomial = polynomialFitter.getBestFit();
        double predictedY = polynomial.getY(9);

        System.out.println("Real Y: " + Math.pow(9.0,4.0));
        System.out.println("Predicted Y: " + predictedY);
    }
}