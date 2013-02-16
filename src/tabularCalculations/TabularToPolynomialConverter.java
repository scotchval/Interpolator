package tabularCalculations;

import interpolation.Polynomial;
import java.util.ArrayList;
import java.util.List;


/**
 * converts tabular coefficients into standard polynomial form
 * 
 * @author Scott Valentine
 * 
 */
public class TabularToPolynomialConverter {

    /** list of polynomial of degree 1 that represent the x values from the tabular method */
    private List<Polynomial> mySubPolynomials;

    /** coefficients calculated by the tabular method */
    private List<Double> myCoefficients;

    /**
     * creates new converter based on the x values that need to be converted and the coefficents
     * 
     * @param xVals - list of terms from tabular method
     * @param coefficients - list of coeffiecients of these terms from the tabular method
     */
    public TabularToPolynomialConverter (List<Double> xVals, List<Double> coefficients) {

        myCoefficients = coefficients;
        mySubPolynomials = makePolynomialTerms(xVals);
    }

    /**
     * converts the coefficients and subpolynomials in this into the a polynomial
     * 
     * @return a polynomial
     */
    public Polynomial convert () {
        Polynomial total = new Polynomial();
        for (int i = mySubPolynomials.size() - 1; i >= 0; --i) {
            Polynomial term = mySubPolynomials.get(i);

            Double coef = myCoefficients.get(i + 1);

            total.add(coef);
            total.multiply(term);
        }
        total.add(myCoefficients.get(0));
        return total;
    }

    /**
     * creates the correspsonding root polynomail for each x value.
     * That is if x = -2, then polynomial in the list is (x +2)
     * 
     * @param xVals - list of xvalues to convert
     * @return list of polynomials from converted xvalues
     */
    private List<Polynomial> makePolynomialTerms (List<Double> xVals) {
        List<Polynomial> result = new ArrayList<Polynomial>();

        // note the -1 since the last term does not get used
        for (int i = 0; i < xVals.size() - 1; ++i) {
            double d = xVals.get(i);
            Polynomial term = makeRootPolynomial(d);
            result.add(term);
        }
        return result;
    }

    /**
     * makes a polynomial root that corresponds to the passed number
     * that is if d= 2, then the polynomial returned is (x-2)
     * 
     * @param d - value to be converted into root polynomial
     * @return the corresponding root polynomial
     */
    private Polynomial makeRootPolynomial (Double d) {
        List<Double> coefs = new ArrayList<Double>();
        coefs.add(-d);
        coefs.add(1.0);
        Polynomial term = new Polynomial(coefs);
        return term;
    }

}
