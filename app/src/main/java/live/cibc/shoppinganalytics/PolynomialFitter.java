package live.cibc.shoppinganalytics;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to fit a polynomial to a (potentially very large) dataset.
 *
 * @author Paul Lutus <lutusp@arachnoid.com>
 * @author Ian Clarke <ian.clarke@gmail.com>
 *
 */
public class PolynomialFitter {

    private final int p, rs;

    private long n = 0;

    private final double[][] m;

    private final double[] mpc;

    private Polynomial poly;

    /**
     * @param degree
     *            The degree of the polynomial to be fit to the data
     */
    public PolynomialFitter(final int degree) {
        assert degree > 0;
        p = degree + 1;
        rs = 2 * p - 1;
        m = new double[p][p + 1];
        mpc = new double[rs];
    }

    /**
     * Add a point to the set of points that the polynomial must be fit to
     *
     * @param x
     *            The x coordinate of the point
     * @param y
     *            The y coordinate of the point
     */
    public void addPoint(final double x, final double y) {
        assert !Double.isInfinite(x) && !Double.isNaN(x);
        assert !Double.isInfinite(y) && !Double.isNaN(y);
        if (poly != null)
            throw new RuntimeException("Can't add points after polynomial is produced");
        n++;
        // process precalculation array
        for (int r = 1; r < rs; r++) {
            mpc[r] += Math.pow(x, r);
        }
        // process RH column cells
        m[0][p] += y;
        for (int r = 1; r < p; r++) {
            m[r][p] += Math.pow(x, r) * y;
        }
    }

    /**
     * Returns a polynomial that seeks to minimize the square of the total
     * distance between the set of points and the polynomial.
     *
     * @return A polynomial
     */
    public Polynomial getBestFit() {
        if (poly != null)
            return poly;
        mpc[0] += n;
        // populate square matrix section
        for (int r = 0; r < p; r++) {
            for (int c = 0; c < p; c++) {
                m[r][c] = mpc[r + c];
            }
        }
        gj_echelonize(m);
        final Polynomial result = new Polynomial(p);
        for (int j = 0; j < p; j++) {
            result.add(j, m[j][p]);
        }
        poly = result;
        return poly;
    }
    private double fx(final double x, final List<Double> terms) {
        double a = 0;
        int e = 0;
        for (final double i : terms) {
            a += i * Math.pow(x, e);
            e++;
        }
        return a;
    }
    private void gj_divide(final double[][] A, final int i, final int j, final int m) {
        for (int q = j + 1; q < m; q++) {
            A[i][q] /= A[i][j];
        }
        A[i][j] = 1;
    }

    private void gj_echelonize(final double[][] A) {
        final int n = A.length;
        final int m = A[0].length;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            // look for a non-zero entry in col j at or below row i
            int k = i;
            while (k < n && A[k][j] == 0) {
                k++;
            }
            // if such an entry is found at row k
            if (k < n) {
                // if k is not i, then swap row i with row k
                if (k != i) {
                    gj_swap(A, i, j);
                }
                // if A[i][j] is not 1, then divide row i by A[i][j]
                if (A[i][j] != 1) {
                    gj_divide(A, i, j, m);
                }
                // eliminate all other non-zero entries from col j by
                // subtracting from each
                // row (other than i) an appropriate multiple of row i
                gj_eliminate(A, i, j, n, m);
                i++;
            }
            j++;
        }
    }

    private void gj_eliminate(final double[][] A, final int i, final int j, final int n, final int m) {
        for (int k = 0; k < n; k++) {
            if (k != i && A[k][j] != 0) {
                for (int q = j + 1; q < m; q++) {
                    A[k][q] -= A[k][j] * A[i][q];
                }
                A[k][j] = 0;
            }
        }
    }

    private void gj_swap(final double[][] A, final int i, final int j) {
        double temp[];
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    public static class Polynomial extends ArrayList<Double> {
        private static final long serialVersionUID = 1692843494322684190L;

        public Polynomial(final int p) {
            super(p);
        }

        public double getY(final double x) {
            double ret = 0;
            for (int p=0; p<size(); p++) {
                ret += get(p)*(Math.pow(x, p));
            }
            return ret;
        }

        @Override
        public String toString() {
            final StringBuilder ret = new StringBuilder();
            for (int x = size() - 1; x > -1; x--) {
                ret.append(get(x) + (x > 0 ? "x^" + x + " + " : ""));
            }
            return ret.toString();
        }
    }
}
