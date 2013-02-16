package interpolation;

import java.util.List;
import java.util.Map;
import tabularCalculations.TabularCoefficients;

/**
 * 
 * @author Scott Valentine
 *
 */
public class HermiteInterpolation extends Interpolation{

    /** */
    Polynomial myPolynomial;
    
    /**
     * 
     * @param data
     */
    public HermiteInterpolation(DataFunction data){
        super(data);
        Map<Double, List<Double>> derivatives = data.getValues();
        createInterpolatingPolynomail(derivatives);
    }
    
    /**
     * 
     * @param derivatives
     */
    private void createInterpolatingPolynomail (Map<Double, List<Double>> derivatives) {
        TabularCoefficients tab = new TabularCoefficients(derivatives);
        myPolynomial = tab.convertToPolynomialCoefficients();
    }

    @Override
    public double evaluate (double x) {
        return myPolynomial.evaluate(x);
    }
    
}
