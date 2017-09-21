/**
 * Base class for goal function and polynomial limitations. Hold coefficients
 * and indicators in double tables, which are set in constructor. Provides method
 * which can calculate polynomial value for point from argument.
 */
class PolynomialForm {

    protected Double[] coefficients;
    protected Integer[] indicators;

    PolynomialForm(Double[] coefficients, Integer[] indicators)
            throws IncorrectCalculationException,DifferentDimensionException {
        checkInputCoordAndIndicators(coefficients,indicators);

        this.coefficients = coefficients;
        this.indicators = indicators;
    }

    public Double calculatePolynomialValue(Double[] coordinates)
            throws IncorrectCalculationException,DifferentDimensionException {
        checkCoordinatesToCalculate(coordinates);
        Double result = new Double(0);

        for(int i = 0; i < coordinates.length; ++i)
            result += coefficients[i] * Math.pow(coordinates[i],indicators[i]);
        return result;
    }

    private void checkCoordinatesToCalculate(Double[] coordinates)
            throws IncorrectCalculationException,DifferentDimensionException {
        if(coordinates == null)
            throw new IncorrectCalculationException("Null found during calculating polynomial value.");
        else if(coordinates.length != coefficients.length)
            throw new DifferentDimensionException("During calculating polynomial value.");
    }

    private void checkInputCoordAndIndicators(Double[] coords, Integer[] indics)
            throws IncorrectCalculationException, DifferentDimensionException {
        if(coords == null || indics == null)
            throw new IncorrectCalculationException("Null found during polynomial value creation.");
        else if(coords.length != indics.length)
            throw new DifferentDimensionException("During polynomial value creation.");

    }
}