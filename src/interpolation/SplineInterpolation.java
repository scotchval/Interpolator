package interpolation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * represent an interpolation as a spine of some degree
 * that is a piecewise function of various polynomials
 * 
 * TODO: this is going to be 1st or 3rd order natural spline
 * or this could be a quadaic spline
 * higher order splines are difficult to calculate
 * maybe b-splines is the way to go
 * 
 * maybe there is a way to apply these to create shapes (such as a circle or square) - there are
 * obvious problems of things being undefined
 * 
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class SplineInterpolation extends Interpolation {
    /** list of polynomials that make up this spline */
    List<Polynomial> myPolynomials;

    // NOTE! myPolynomials should have length 1 less than switch points

    /**
     * x values where the spline function changes between two polynomials
     * i.e. they are the "knots" that pin the spline down
     * note that this is an actual term according to David Kincaid and Ward Cheney
     */
    List<Double> myKnots;

    /**
     * constructs a new spline that interpolates the data a certain degree
     * 
     * @param degree - of the spline interpolation (degree = 0 is piecewise constant, etc)
     * @param data - data to be interpolated
     */
    public SplineInterpolation (double degree, DataFunction data) {
        super(truncateData(data));
        myPolynomials = new ArrayList<Polynomial>();
        myKnots = data.getXVals();
        
        //this might not be ness, but it MUST be in the right order
        Collections.sort(myKnots);

        generateZValues(data.getValues());

    }

    
    /**
     * UGLY - still need to check
     * 
     * 
     * TODO: this is ugly, basically did the algorithm all in one place
     * gets the second derivative values of the polynomials
     */
    private double[] generateZValues (Map<Double, List<Double>> values) {
        int size = myKnots.size();
        double[] intervals = new double[size-1];
        double[] bArray = new double[size-1];
        
        
        for(int i = 0; i < myKnots.size()-1;  ++i){
            
            double xi = myKnots.get(i);
            double xii = myKnots.get(i+1);
            
            intervals[i] = xii - xi;
            
            bArray[i] = 6.0*(values.get(xii).get(0) - values.get(xi).get(0))/ (intervals[i]);
            
        }
        //TODO: check
        
        double[] uArray = new double[size];
        double[] vArray = new double[size ];
        
        uArray[1] = 2*(intervals[0] + intervals[1]);
        vArray[1] = bArray[1] - bArray[1];

        
        for(int i = 2; i < size -1; ++i){
            
            uArray[i] = 2.0*(intervals[i] + intervals[i-1]) - intervals[i-1]*intervals[i-1] / uArray[i-1];
            
            vArray[i] = bArray[i] - bArray[i-1] - intervals[i-1]*vArray[i-1]/uArray[i-1];  
        }
        
        double[] zArray = new double[size+1];
        zArray[size] = 0;
        for(int i = size-1; i >= 1; --i){
            zArray[i] = vArray[i] - intervals[i]*zArray[i+1]/uArray[i];
        }
        zArray[0] = 0;
        return zArray;
    }


    // TODO: this will use a different method for plotting that hermite interpolation

    @Override
    public double evaluate (double x) {
        int pIndex = getIndex(x);
        return myPolynomials.get(pIndex).evaluate(x);
    }

    /**
     * does a weighted binary search through the values to find the correct interval
     * 
     * 
     * TODO: make sure that there are enough switch points
     * TODO: i expect the list to be realatively regular most of the time
     * 
     * @param x
     * @return
     * @throws Exception
     */
    private int getIndex (double x) {
        int size = myKnots.size();
        double start = myKnots.get(0);
        double end = myKnots.get(size - 1);

        if (x > end || x < start)
            // TODO throw exception
            return -1;

        return 0;
    }
    
    /**
     * truncates the DataFunction so that it only includes points, (no derivative values)
     * 
     * @param data - original data, not truncated
     * @return - data that consists only of points
     */
    private static DataFunction truncateData (DataFunction data) {
        Map<Double, List<Double>> values = data.getValues();
        Map<Double, List<Double>> result = new HashMap<Double, List<Double>>();
        for (double d : values.keySet()) {
            List<Double> novoList = new ArrayList<Double>();
            novoList.add(values.get(d).get(0));

            result.put(d, novoList);
        }
        return new DataFunction(result);
    }

}
