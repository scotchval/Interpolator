package test;

import interpolation.LagrangeInterpolation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import junit.framework.TestCase;

public class InterpolateTester extends TestCase {

    @Test
    public void testDividedDifference(){
        List<Double> xVals = new ArrayList<Double>();
        xVals.add(0.0);
        xVals.add(0.0);
        xVals.add(1.0);
        xVals.add(1.0);
        Map<Double, List<Double>> derv = new HashMap<Double, List<Double>>();
        
        List<Double> l1 = new ArrayList<Double>();
        l1.add(0.0);
        l1.add(0.0);
        derv.put(0.0, l1);
        
        List<Double> l2 = new ArrayList<Double>();
        l2.add(2.0);
        l2.add(2.0);
        derv.put(1.0, l2);
        
        double res = LagrangeInterpolation.dividedDifference(xVals, derv);
        
        assertEquals(0.0, res, .00000001);
        
        
    }
    
    
}
