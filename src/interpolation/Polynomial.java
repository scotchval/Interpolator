package interpolation;

import java.util.ArrayList;
import java.util.List;


/**
 * represents a 2d polynomial function
 * 
 * @author Scott Valentine
 * 
 */
public class Polynomial {
    List<Double> myCoefficients;

    public Polynomial () {
        myCoefficients = new ArrayList<Double>();
    }

    /**
     * TODO
     * 
     * @param coefficients
     */
    public Polynomial (List<Double> coefficients) {
        myCoefficients = coefficients;
    }

    /**
     * adds another polynomial to this polynomial
     * 
     * @param otherPolynomial polynomial to be added to this polynomial
     */
    public void add (Polynomial otherPolynomial) {
        int minDegree = Math.min(this.getDegree(), otherPolynomial.getDegree());
        for (int i = 0; i <= minDegree; ++i) {
            double newCoef = myCoefficients.get(i) + otherPolynomial.getCoeffAt(i);
            myCoefficients.set(i, newCoef);

        }
        // make sure there are no trailing zeros
        int i = this.getDegree();
        while (myCoefficients.size() > 0 && myCoefficients.get(i) == 0) {
            myCoefficients.remove(i);
            i -= 1;
        }
    }

    /**
     * adds a constant to this polynomial
     * 
     * @param constant - constant to be added to this polynomial
     */
    public void add (Double constant) {
        if (myCoefficients.size() == 0) {
            myCoefficients.add(constant);
        }
        else {
            double value = myCoefficients.get(0) + constant;
            myCoefficients.set(0, value);
        }

    }

    /**
     * returns the coefficient that corresponds to the ith degree term in this
     * 
     * @param i - degree of term to get coefficient
     * @return - the ith coeffieicnt of this polynomial
     */
    public double getCoeffAt (int i) {
        return myCoefficients.get(i);

    }

    /**
     * gives the maximum power of this polynomial,
     * if the function is completely empty, return -1
     * 
     * @return the maximum degree of this polynomial
     */
    public int getDegree () {
        int size = myCoefficients.size();
        if (size <= 0) {
            return -1;
        }
        else {
            return size - 1;
        }
    }

    /**
     * multiplies two given polynomials and returns new polynomial equal to their product
     * 
     * @param p2 polynomial to be multiplied
     */
    public void multiply (Polynomial p2) {
        List<Double> result = createSkeletonOfDegree(this.getDegree(), p2.getDegree());

        for (int i = 0; i <= this.getDegree(); ++i) {
            for (int j = 0; j <= p2.getDegree(); ++j) {
                result.set(i + j, this.getCoeffAt(i) * p2.getCoeffAt(j) + result.get(i + j));
            }
        }
        myCoefficients = result;
    }

    /**
     * multiplies the given polynomial by a constant
     * 
     * @param p1 - polynomial to be multiplied
     * @param constant - constant factor to multiply by
     * @return - polynomaial with constant multiplied through
     */
    public void multiply (double constant) {
        List<Double> temp = new ArrayList<Double>();
        temp.add(constant);
        Polynomial constantP = new Polynomial(temp);
        this.multiply(constantP);
    }

    /**
     * TODO
     * creates a list that represents a polynomial with 0 for all coefficients
     * 
     * @param degree1
     * @param degree2
     * @return the skeleton of a polynomial
     */
    private List<Double> createSkeletonOfDegree (int degree1, int degree2) {
        List<Double> skeleton = new ArrayList<Double>();
        
        if (degree1 < 0 || degree2 < 0) { return skeleton; }
        int totalDegree = degree1 + degree2;
        for (int i = 0; i <= totalDegree; ++i) {
            skeleton.add(0.0);
        }
        return skeleton;
    }

    /**
     * prints out the polynomial function
     * not perfect, runs into problems when function is only a constant value
     * TODO: fix this, could be done with another if statement, but this is ugly
     */
    public String toString () {
        String toPrint = "";
        for (int i = 0; i < myCoefficients.size(); ++i) {
            if (i == 0) {
                toPrint += (myCoefficients.get(i) + " + ");
            }
            else if (i == myCoefficients.size() - 1) {
                toPrint += (myCoefficients.get(i) + "x^" + i);
            }

            else {
                toPrint += (myCoefficients.get(i) + "x^" + i + " + ");
            }
        }
        toPrint += "\n";
        return toPrint;
    }

    /**
     * returns a list of the coefficients that define the polynomial
     * NB: index indicates the degree of x for the coefficient
     * i.e. [15,2,1] --> f(x) = 15 + 2x + x^2
     * 
     * @return
     */
    public List<Double> getFunction () {
        return myCoefficients;
    }

    /**
     * evaluates function at given point using nested multiplication(Horner's method)
     * 
     * @param x - point at which the function (polynomial) is evaluated.
     */

    public double evaluate (double x) {
        double value = this.getCoeffAt(this.getDegree());

        for (int d = this.getDegree() - 1; d >= 0; --d) {
            value = x * value + this.getCoeffAt(d);
        }
        return value;
    }

    /**
     * changes this polynomial to its derivative
     * 
     */
    public void derivate () {
        int size = myCoefficients.size();
        for (int i = 1; i < size; ++i) {
            myCoefficients.set(i - 1, myCoefficients.get(i) * i);
        }
        myCoefficients.remove(size - 1);
    }

    /**
     * changes this polynomial to its antiderivative
     * use the default value of 0 for the integration constant
     */
    public void antiderivative () {
        antiderivative(0.0);
    }

    /**
     * changes this polynomial to its antiderivative
     * 
     * @param contstant - constant value for antiderivative
     */
    public void antiderivative (double constant) {
        // do last index first since it requires adding a term to the list
        int size = myCoefficients.size();
        double last = myCoefficients.get(size - 1) / (size);

        for (int i = myCoefficients.size() - 2; i >= 0; --i) {
            myCoefficients.set(i + 1, myCoefficients.get(i) / (i + 1));
        }
        myCoefficients.set(0, constant);
        myCoefficients.add(last);
    }

}
