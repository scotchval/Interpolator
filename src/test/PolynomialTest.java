package test;

import interpolation.Polynomial;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * test for polynomial class
 * @author Scott Valentine
 *
 */
public class PolynomialTest extends TestCase{

    @Test
    public void testGetFunction () {
        ArrayList<Double> x = new ArrayList<Double>();
        x.add(2.0);
        x.add(3.0);
        
        ArrayList<Double> y = new ArrayList<Double>();
        y.add(-2.0);
        y.add(-3.0);
        
        
        
        Polynomial p1 = new Polynomial(x);
        Polynomial p2 = new Polynomial(y);
        
        assertEquals("degree", 1, p2.getDegree());
        assertEquals("degree", 1, p1.getDegree());
   
        p1.add(p2);
        
      assertEquals("degree", -1, p1.getDegree());
      

        
        //fail("Not yet implemented");
    }

    @Test
    public void testEvalutate () {
        List<Double> fact = new ArrayList<Double>();
        fact.add(1.0);
        fact.add(2.0);
        fact.add(1.0);
        Polynomial pReal = new Polynomial(fact);
        assertEquals(1.0, pReal.evaluate(0.0),  .0000001);
        assertEquals( 0.0, pReal.evaluate(-1.0), .0000001);
        assertEquals(160801.0, pReal.evaluate(400.0),  .0000001);
        assertEquals( .9999000025, pReal.evaluate(-.00005), .0000001);
        assertEquals(1.0001000025, pReal.evaluate(.00005), .0000001);
    }

    
    @Test
    public void testMultiply () {
        ArrayList<Double> x = new ArrayList<Double>();
        x.add(4.0);
        //x.add(1.0);

        Polynomial p1 = new Polynomial(x);
        
        ArrayList<Double> y = new ArrayList<Double>();
        y.add(-1.0);
        y.add(1.0);
        Polynomial p2 = new Polynomial(y);


        ArrayList<Double> fact = new ArrayList<Double>();
        fact.add(-4.0);
        fact.add(4.0);
        //fact.add(1.0);
        Polynomial pReal = new Polynomial(fact);
        
        p1.multiply(p2);
        
        assertEquals("Check degree", pReal.getDegree(), p1.getDegree());

        for(int i = 0; i <= Math.min(p1.getDegree(), pReal.getDegree()); ++i){
            assertEquals(pReal.getCoeffAt(i), p1.getCoeffAt(i), .00000000001);
        }
        
        

    }
    
    @Test
    public void testDerivative(){
        List<Double> fact = new ArrayList<Double>();
        fact.add(1.0);
        fact.add(2.0);
        fact.add(1.0);
        Polynomial test = new Polynomial(fact);
        
        test.derivate();
        
        List<Double> derv = new ArrayList<Double>();
        derv.add(2.0);
        derv.add(2.0);
        
        List<Double> t = test.getFunction();
        
        for(int i = 0; i < t.size(); ++i){
            assertEquals(derv.get(i), t.get(i), .0000001);
        }
        
    }
 
    
    @Test
    public void testAntiderivative(){
        List<Double> fact = new ArrayList<Double>();
        fact.add(1.0);
        fact.add(2.0);
        fact.add(1.0);
        
        List<Double> derv = new ArrayList<Double>();
        derv.add(2.0);
        derv.add(2.0);
        Polynomial test = new Polynomial(derv);
        test.antiderivative(1.0);
        
        List<Double> t = test.getFunction();
        
        for(int i =0 ; i < t.size(); ++ i){
            assertEquals(fact.get(i), t.get(i), .0000001);
        }
        
    }
    
    
}
