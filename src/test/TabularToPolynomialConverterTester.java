package test;


import java.util.ArrayList;
import java.util.List;
import interpolation.Polynomial;
import org.junit.Test;
import tabularCalculations.TabularToPolynomialConverter;
import junit.framework.TestCase;


public class TabularToPolynomialConverterTester extends TestCase {

    private TabularToPolynomialConverter myTabToPoly;

    @Test
    public void testTabularToPolynomial () {
        makeTabToPoly();
        
        Polynomial res = myTabToPoly.convert();
        
        List<Double> result = res.getFunction();
        
        List<Double> actual = new ArrayList<Double>();
        actual.add(-19.0);
        actual.add(37.0);
        actual.add(-21.0);
        actual.add(4.0);
        
        for(int i = 0; i < result.size(); ++i){
            assertEquals(actual.get(i), result.get(i), .00000001);
        }
        
    }

    private void makeTabToPoly () {
        
        List<Double> xvals = new ArrayList<Double>();
        xvals.add(1.0);
        xvals.add(2.0);
        xvals.add(3.0);
        xvals.add(4.0);
        
        
        List<Double> coefficients = new ArrayList<Double>();
        coefficients.add(1.0);
        coefficients.add(2.0);
        coefficients.add(3.0);
        coefficients.add(4.0);

        
        myTabToPoly = new TabularToPolynomialConverter(xvals, coefficients);

    }

}
