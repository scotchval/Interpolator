package interpolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  Calculates the tabular coefficients to be used in interpolating polynomial
 *  
 * @author Scott Valentine
 *
 */
public class TabularCoefficients {

    /** makes and calulates the values of the row of the tabular method*/
    RowCalculator myRowMaker;
    
    /** list of the coefficients resulting from the tabular method*/
    List<Double> myCoefficients;
    
    /** List of x values used in tabular method calculation*/
    List<Double> myXVals; 

    /**
     * constructor set up for tabular method
     * 
     * @param xVals
     * @param dervs - mapping of known derivatives for each xValue
     */
    public TabularCoefficients (Map<Double, List<Double>> dervs) {
        myCoefficients = new ArrayList<Double>();
        myXVals = makeXValues(dervs);
        myRowMaker = new RowCalculator(myXVals, dervs);
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

}
