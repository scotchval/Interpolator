package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import tabularCalculations.TabularRowCalculator;
import junit.framework.TestCase;


public class TabularRowCalculatorTester extends TestCase {
    TabularRowCalculator myRC;

    @Test
    public void testMakeFirstRow () {
        createDerivativesAndXVals();
        List<Double> res = myRC.makeFirstRow();

        List<Double> actual = new ArrayList<Double>();
        actual.add(0.0);
        actual.add(0.0);
        actual.add(1.0);
        actual.add(1.0);
        actual.add(1.0);

        for (int i = 0; i < res.size(); ++i) {
            assertEquals(actual.get(i), res.get(i), .00000000001);
        }

    }

    @Test
    public void testMakeNewRow () {
        createDerivativesAndXVals();

        List<Double> row1 = new ArrayList<Double>();
        row1.add(0.0);
        row1.add(0.0);
        row1.add(1.0);
        row1.add(1.0);
        row1.add(1.0);

        List<Double> res1 = myRC.makeNewRow(row1);

        List<Double> row2 = new ArrayList<Double>();
        row2.add(1.0);
        row2.add(1.0);
        row2.add(2.0);
        row2.add(2.0);

        List<Double> res2 = myRC.makeNewRow(row2);

        List<Double> row3 = new ArrayList<Double>();
        row3.add(0.0);
        row3.add(1.0);
        row3.add(3.0/2.0);

        List<Double> res3 = myRC.makeNewRow(row3);

        for (int i = 0; i < res1.size(); ++i) {
            assertEquals(row2.get(i), res1.get(i), .00000000001);
        }

        for (int i = 0; i < res2.size(); ++i) {
            assertEquals(row3.get(i), res2.get(i), .00000000001);
        }

        List<Double> last = new ArrayList<Double>();
        last.add(1.0);
        last.add(.5);

        for (int i = 0; i < res3.size(); ++i) {
            assertEquals(last.get(i), res3.get(i), .00000000001);
        }

    }

    private void createDerivativesAndXVals () {
        Map<Double, List<Double>> dervs = new HashMap<Double, List<Double>>();
        List<Double> xvals = new ArrayList<Double>();
        List<Double> l1 = new ArrayList<Double>();
        l1.add(0.0);
        l1.add(1.0);
        xvals.add(0.0);
        xvals.add(0.0);
        
        List<Double> l2 = new ArrayList<Double>();
        l2.add(1.0);
        l2.add(2.0);
        l2.add(3.0);
        xvals.add(1.0);
        xvals.add(1.0);
        xvals.add(1.0);
        
        dervs.put(0.0, l1);
        dervs.put(1.0, l2);

        myRC = new TabularRowCalculator(xvals, dervs);
    }
}
