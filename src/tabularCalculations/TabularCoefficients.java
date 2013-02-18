package tabularCalculations;

import interpolation.Polynomial;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Calculates the tabular coefficients to be used in interpolating polynomial
 * TODO: it might be a good idea to store the rows as a graph or tree so that data can be appended
 * easily later
 * 
 * @author Scott Valentine
 * 
 */
public class TabularCoefficients {

    /** makes and calulates the values of the row of the tabular method */
    private TabularRowCalculator myRowMaker;

    /** list of the coefficients resulting from the tabular method */
    private List<Double> myCoefficients;

    /** List of x values used in tabular method calculation */
    private List<Double> myXVals;

    /**
     * constructor set up for tabular method
     * 
     * @param xVals
     * @param dervs - mapping of known derivatives for each xValue
     */
    public TabularCoefficients (Map<Double, List<Double>> dervs) {
        myCoefficients = new ArrayList<Double>();
        myXVals = makeXValues(dervs);
        myRowMaker = new TabularRowCalculator(myXVals, dervs);
        calculatedCoefficients();
    }

    /**
     * starts the recursive calculation of coefficients
     */
    private void calculatedCoefficients () {
        List<Double> firstRow = myRowMaker.makeFirstRow();
        recursiveTabularCalculation(firstRow);
    }

    /**
     * recursively gets the next coefficient and next row in tabular method
     * 
     * @param currentRow - last calculated row
     */
    private void recursiveTabularCalculation (List<Double> currentRow) {
        if (currentRow.size() <= 0) { return; }

        // adds the next coefficient
        myCoefficients.add(currentRow.get(0));

        List<Double> nextRow = myRowMaker.makeNewRow(currentRow);
        recursiveTabularCalculation(nextRow);
    }

    /**
     * gets the base x values to be used from the mapping of the derivatives
     * 
     * @return - the x values to be used in the tabular calculation
     */
    private List<Double> makeXValues (Map<Double, List<Double>> derivatives) {
        List<Double> result = new ArrayList<Double>();
        for (Double x : derivatives.keySet()) {
            int size = derivatives.get(x).size();

            for (int i = 0; i < size; i++) {
                result.add(x);
            }
        }
        return result;
    }

    /**
     * gives the calculated coefficients for interpolation
     * 
     * @return the tabular coefficients for newton interpolation
     */
    public List<Double> tabularCoefficients () {
        return myCoefficients;
    }

    /**
     * converts myCoefficients into the standard polynomial form using nested multiplication
     * http://en.wikipedia.org/wiki/Horner's_method
     * 
     * i.e. instead of a0 + a1 (x-c1) + a2(x-c1)(x-c2) + ...
     * we have
     * d0 + d1*x + d2*x^2 + ....
     * 
     * @return the correct polynomial
     */
    public Polynomial convertToPolynomialCoefficients () {
        TabularToPolynomialConverter tabToPoly =
                new TabularToPolynomialConverter(myXVals, myCoefficients);
        Polynomial p = tabToPoly.convert();
        return p;
    }

}
