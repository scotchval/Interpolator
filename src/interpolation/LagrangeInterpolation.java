package interpolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a Lagrange interpolation. That is a hermite interpolation that only uses the function
 * values (no derivatives)
 * 
 * @author Scott Valentine
 * 
 */
public class LagrangeInterpolation extends HermiteInterpolation {

    /**
     * truncates data to only points (throws out derivatives)
     * and creates new HermiteInterpolation based on this
     * 
     * @param data - data used for interpolation
     */
    public LagrangeInterpolation (DataFunction data) {
        super(truncateData(data));
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
