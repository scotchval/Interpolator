import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import tabularCalculations.TabularCoefficients;


/**
 * Much of the math used in this project directly came from a course in Numerical Analysis at Duke
 * University taught by Mark Iwen.
 * The textbook used was Numerical Analysis: Mathematics of Scientific Computer third edition by
 * David Kincaid and Ward Cheney
 * 
 * 
 * 
 * TODO: This is not serving a purpose here except to help me check my homework solutions
 * 
 * @author Scott Valentine
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main (String[] args) {
        List<Double> l1 = new ArrayList<Double>();
        l1.add(2.0);
        l1.add(-9.0);

        List<Double> l2 = new ArrayList<Double>();
        l2.add(-4.0);
        l2.add(4.0);

        List<Double> l3 = new ArrayList<Double>();
        l3.add(44.0);

        List<Double> l4 = new ArrayList<Double>();
        l4.add(2.0);

        Map<Double, List<Double>> dervs = new TreeMap<Double, List<Double>>();

        dervs.put(0.0, l1);
        dervs.put(1.0, l2);
        dervs.put(2.0, l3);
        dervs.put(3.0, l4);

        TabularCoefficients tb = new TabularCoefficients(dervs);
        List<Double> ans = tb.tabularCoefficients();

        String str = "";
        for (double d : ans) {
            str += "\t" + d;
        }
        System.out.println(str);

    }

}
