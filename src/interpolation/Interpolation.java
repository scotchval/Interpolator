package interpolation;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * connects all the values in the passed function with some sort of continuous function
 * the degree of continuity depends on the subclass used
 * @author Scott Valentine
 *
 */
public abstract class Interpolation {
    DataFunction myData;
    
    /**
     * default constructor, adds data to interpolate
     * @param data to interpolate
     */
    protected Interpolation(DataFunction data){
        myData = data;
    }
    
    /**
     * give the current data that is interpolated
     * @return the data that the interpolation is based off of
     */
    protected DataFunction getData() {
        return myData;
    }
    
    /**
     * |
     * evaluates the interpolation at the given point
     * 
     * @param x , evaluate at this point
     * @return the y value of this interpolation at x
     */
    public abstract double evaluate (double x);

    /**
     * gives a discrete mapping of this interpolation in the given range given the resolution
     * 
     * @param rangeBegin - start of interval to be mapped
     * @param rangeEnd - end of interval to be mapped
     * @param steps - how many data point to evaulate at
     * @return - a discrete mapping of 'steps' amount of points in the interval [rangeBegin,
     *         rangeEnd]
     */
    public Map<Double, Double> graphValues (double rangeBegin, double rangeEnd, int steps) {
        Map<Double, Double> mapping = new TreeMap<Double, Double>();

        if (rangeBegin >= rangeEnd) {
            double temp = rangeBegin;
            rangeBegin = rangeEnd;
            rangeEnd = temp;
        }

        double resolution = (rangeEnd - rangeBegin) / (double) steps;
        double x = rangeBegin;
        for (int i = 0; i < steps; ++i) {
            mapping.put(x, this.evaluate(x));
            x += resolution;
        }
        return mapping;
    }
    
    /**
     * calculates the divided difference through the recursive definition
     * http://en.wikipedia.org/wiki/Divided_differences
     * TODO
     * @param xVals
     * @param derivatives
     * @return
     */
    public static double dividedDifference (List<Double> xVals,
                                            Map<Double, List<Double>> derivatives) {
        if (xVals.size() == 1) {
            return derivatives.get(xVals.get(0)).get(0);
        }
        int n = xVals.size() - 1;
        double x0 = xVals.get(0);
        double xn = xVals.get(n);
        if (x0 == xn) {
            List<Double> differentials = derivatives.get(x0);
            double dxdy = differentials.get(n);
            return dxdy / (double) factorial(n);
        }
        xVals.remove(0);
        double a = dividedDifference(xVals, derivatives);
        // restore the map and set up for next recusive call
        xVals.add(0, x0);
        xVals.remove(xVals.size() - 1);

        double b = dividedDifference(xVals, derivatives);
        double diff = xn - x0;
        return (a - b) / diff;
    }

    /**
     * computes the factorial of n
     * 
     * @param n input
     * @return n!
     */
    protected static int factorial (int n) {
        if (n <= 1) { return 1; }
        return n * factorial(n - 1);
    }

}
