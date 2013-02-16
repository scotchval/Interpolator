package interpolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HermiteInterpolation extends Interpolation{

    Polynomial myPolynomial;
    
    
    public HermiteInterpolation(DataFunction data){
        super(data);
        Map<Double, List<Double>> derivatives = data.getValues();
        createInterpolatingPolynomail(derivatives);
    }
    
    private void createInterpolatingPolynomail (Map<Double, List<Double>> derivatives) {
        List<Double> xVals = getxVals(derivatives);
        
        
    }

    /**
     * creates a list of xvals for tabular method calculation of interpolation polynomail
     * @param derivatives
     * @return
     */
    private List<Double> getxVals (Map<Double, List<Double>> derivatives) {
        List<Double> result = new ArrayList<Double>();
        for(Double x: derivatives.keySet()){
            int size = derivatives.get(x).size();
            for(int i =0; i < size; ++i){
                result.add(x);
            }
        }
        return result;
    }

    public static double getTaylorCoefficient(int derivative){
        
        
        
        
        return 0.0;
    }
    
    public static void printCoeffs(List<Double> firstRow, List <Double> xVals, Map<Double, List<Double>> derivatives) {
       System.out.print(firstRow.get(0) + "\n");
        
//        for(double d: xVals){
//            System.out.print(d + "\t");
//        }
//        System.out.print("\n");
//        for(double a: firstRow){
//            System.out.print(a + "\t");
//        }
//        System.out.print("\n");
        
       tabRecurse(firstRow, xVals, derivatives);
        
        
        
    }
    
    private static void tabRecurse(List<Double> lastRow, List<Double> xVals, Map<Double, List<Double>> derivatives) {
        //basecase
        if (lastRow.size() <= 1){
            return;
        }
        //create skeleton list
        List<Double> novo = new ArrayList<Double>();
        for(int i = 0; i < lastRow.size()-1; ++i){
            novo.add(0.0);
        }
        
        int level = xVals.size() - lastRow.size()+1;

        
        //get new level
        for(int i = 0; i < novo.size(); ++i) {
            double val;
            double xTop = xVals.get(i);
            double xBottom = xVals.get(i + level);
            
            if(xTop == xBottom){
                val = derivatives.get(xTop).get(level) / factorial(level);
            }
            else{
                val = (lastRow.get(i) - lastRow.get(i+1))/(xTop - xBottom);
            }
            novo.set(i, val); 
            //System.out.print(val + "\t");
        }
        //prints first element (the coeff)
        System.out.print(novo.get(0) +"\n");   
        //System.out.print("\n");
        tabRecurse( novo, xVals, derivatives);
    }

    /**
     * number of measurements used in map
     * @param derivatives
     * @return
     */
    private static int sizeOf (Map<Double, List<Double>> derivatives) {
        int size = 0;
        for(double d: derivatives.keySet()){
            size += derivatives.get(d).size();
        }
        return size;
    }

    @Override
    public double evaluate (double x) {
        return myPolynomial.evaluate(x);
    }
    
}
