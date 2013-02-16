import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import interpolation.HermiteInterpolation;
import interpolation.LagrangeInterpolation;

/**
 * computes the interpolating polynomial for a given set of data
 * and prints out the coefficients according to Newton's tabular method
 * @author Scotch
 *
 */
public class NewtonTabular {

    /**
     * @param args
     */
    public static void main (String[] args) {
        List<Double> xVals = new ArrayList<Double>();
        xVals.add(0.0);
        xVals.add(0.0);
        xVals.add(1.0);
        xVals.add(1.0);
        xVals.add(2.0);
        
        
        Map<Double, List<Double>> map = new HashMap<Double, List<Double>>();
        
        
        List<Double> l1 = new ArrayList<Double>();
        l1.add(2.0);
        l1.add(-9.0);
        map.put(0.0, l1);
        
        List<Double> l2 = new ArrayList<Double>();
        l2.add(-4.0);
        l2.add(4.0);
        map.put(1.0, l2);
        
        List<Double> l3 = new ArrayList<Double>();
        l3.add(44.0);
        map.put(2.0, l3);
//        
//        printCoefs(xVals, map);
//        
//        List<Double> yVals = new ArrayList<Double>();
//        yVals.add(0.0);
//        yVals.add(1.0);
//        yVals.add(1.0);
//        
//        
//        printCoefs(yVals, map);
        
        List<Double> firstRow = new ArrayList<Double>();
        firstRow.add(2.0);
        firstRow.add(2.0);
        firstRow.add(-4.0);
        firstRow.add(-4.0);
        firstRow.add(44.0);
        
        HermiteInterpolation.printCoeffs(firstRow, xVals, map);

        
    }

    private static void printCoefs (List<Double> xVals, Map<Double, List<Double>> map) {
        if (xVals.size() <=0){
            
            return;
        }
        ArrayList<Double> next = new ArrayList<Double>();
        for(Double d: xVals){
            next.add(d);
        }
        
        
        
        System.out.print(LagrangeInterpolation.dividedDifference(xVals, map) + "\n");
        
        int i = next.size();
        next.remove(i -1);
        //System.out.print(xVals.size() + "\t");
        printCoefs(next, map);
    }

    private static void recurse (double[] y, double[] x, double[][] derivatives) {
        
        int n = y.length; 
        if(n <= 1){
            //System.out.print(y[0]);
            return;
        }
        double novo[] = new double[n-1];
        int dist = x.length - n;
        
        for(int i = 0; i < novo.length;++i){
            double val;
            if(x[i] == x[i + dist+1]){
                val = derivatives[i][dist];
            }
            else{
            val = (y[i] - y[i+1])/(x[i] - x[i+dist+1]);
            }
            novo[i] = val;
            
        }
        for(int i = 0; i < novo.length;++i){
            System.out.print(novo[i] + "\t");
        }
        System.out.print("\n");
        
        recurse(novo, x, derivatives);
        
    }

}
