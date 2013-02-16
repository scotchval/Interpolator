package interpolation;

import java.util.List;

/**
 * represent an interpolation as a spine of some degree
 * that is a piecewise function of various polynomials
 * @author Scott Valentine
 *
 */
public class SplineInterpolation extends Interpolation{
    List<Polynomial> myPolynomials;
    
    protected SplineInterpolation (DataFunction data) {
        super(data);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double evaluate (double x) {
        // TODO Auto-generated method stub
        return 0;
    }

}
