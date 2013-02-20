package interpolation;

import java.util.List;
import java.util.Map;
import tabularCalculations.TabularCoefficients;

/**
 * Interpolation of points that include derivatives for various degrees for each point
 * 
 * @author Scott Valentine
 *
 */
public class HermiteInterpolation extends Interpolation{

    /** polynomial that currently is acting as the interpolation polynomial */
    private Polynomial myPolynomial;
    
    /**
     * creates a new interpolation from the data passed
     * 
     * @param data - collection of points and their derivatives that will be interpolated
     */
    public HermiteInterpolation(DataFunction data){
        super(data);
        Map<Double, List<Double>> derivatives = data.getValues();
        createInterpolatingPolynomail(derivatives);
    }
    
    /**
     * creates the interpolating polynomial from a mapping of each point to its derivatives
     * 
     * @param derivatives - the derivatives at each point.
     */
    private void createInterpolatingPolynomail (Map<Double, List<Double>> derivatives) {
        TabularCoefficients tab = new TabularCoefficients(derivatives);
        myPolynomial = tab.convertToPolynomialCoefficients();
    }

    @Override
    public double evaluate (double x) {
        return myPolynomial.evaluate(x);
    }
    
    public Polynomial getPolynomail() {
        return myPolynomial;
    }
    
}
