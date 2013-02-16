package interpolation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * represent an interpolation as a spine of some degree
 * that is a piecewise function of various polynomials
 * @author Scott Valentine
 *
 */
public class SplineInterpolation extends Interpolation{
    /** list of polynomials that make up this spline */
    List<Polynomial> myPolynomials;
    
    //NOTE! myPolynomials should have length 1 less than switch points
    
    /** x values where the spline function changes between two polynomials */
    List<Double> mySwitchPoints;
    
    protected SplineInterpolation (DataFunction data) {
        super(data);
        myPolynomials = new ArrayList<Polynomial>();
        mySwitchPoints = data.getXVals();

    }

    //TODO: this will use a different method for plotting that hermite interpolation

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
     * @param x
     * @return
     * @throws Exception 
     */
    private int getIndex (double x) {
        int size = mySwitchPoints.size();
        double start = mySwitchPoints.get(0);
        double end = mySwitchPoints.get(size-1);
        
        if(x > end || x < start )
            //TODO throw exception
            return -1;
        
        
        
        return 0;
    }

}
