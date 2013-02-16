package tabularCalculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * calculates rows of tabular method
 * 
 * @author Scott Valentine
 * 
 */
public class TabularRowCalculator {

    /** the base row of x values for the tabular method */
    private List<Double> myXVals;
    /** the known derivatives for each unique x value */
    private Map<Double, List<Double>> myDerivatives;

    /**
     * constructor for row calculator, gives it starting info needed for constructing any row
     * 
     * @param xVals - list of x values the tabular method stared with
     * @param dervs - mapping of each unique x value to a list of its derivatives
     */
    public TabularRowCalculator (List<Double> xVals, Map<Double, List<Double>> dervs) {
        myDerivatives = dervs;
        myXVals = xVals;
    }

    /**
     * creates the first row of the tabular method
     * by getting the function value at each x value in myXVals
     * 
     * @return first row of tabular method
     */
    public List<Double> makeFirstRow () {
        List<Double> firstRow = new ArrayList<Double>();
        for (int i = 0; i < myXVals.size(); ++i) {
            // TODO: make this legible
            firstRow.add(myDerivatives.get(myXVals.get(i)).get(0));
        }
        return firstRow;
    }

    /**
     * makes a new row of the tabular method from values of the previous row and the x values in
     * myXVals
     * 
     * @param lastRow the last row of the tabular method
     * @return the next row in the tabular method
     */
    public List<Double> makeNewRow (List<Double> lastRow) {
        int level = myXVals.size() - lastRow.size()+1;

        List<Double> nextRow = new ArrayList<Double>();

        for (int i = 0; i < lastRow.size() - 1; ++i) {

            double x0 = myXVals.get(i);
            double x1 = myXVals.get(i + level);

            // get derivative
            if (x0 == x1) {
                double derivativeValue = myDerivatives.get(x0).get(level);
                nextRow.add(derivativeValue / factorial(level));
            }
            // else do default calculation
            else {
                double y0 = lastRow.get(i);
                double y1 = lastRow.get(i + 1);

                double valToBeAdded = (y0 - y1) / (x0 - x1);
                nextRow.add(valToBeAdded);
            }
        }
        return nextRow;
    }

    /**
     * returns n! = n * (n-1) * ... *(1)
     * 
     * @param n
     * @return n!
     */
    private double factorial (int n) {
        if (n <= 1) { return 1; }
        return n * (n - 1);
    }
}
