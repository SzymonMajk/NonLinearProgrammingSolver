/**
 * Covered data of goal functions, lets user to calculate values of coordinates
 * for holds function and could represent it in String.
 */
public class GoalFunction extends PolynomialForm {

    private Boolean maximalize;
    public Boolean getMaximalize() { return maximalize; }

    GoalFunction(Double[] coefficients, Integer[] indicators, Boolean maximalize)
            throws IncorrectCalculationException,DifferentDimensionException {
        super(coefficients,indicators);
        if(maximalize == null)
            throw new IncorrectCalculationException("Null found during create goal function.");
        this.maximalize = maximalize;
    }

    @Override
    public String toString() {
        StringBuilder functionBuilder = new StringBuilder();

        functionBuilder.append("F (...) = ");
        for (int i = 0; i < coefficients.length; ++i) {
            functionBuilder.append(coefficients[i]);
            functionBuilder.append(" x_");
            functionBuilder.append(i + 1);
            functionBuilder.append("^(");
            functionBuilder.append(indicators[i]);
            functionBuilder.append(") ");
            if (i != coefficients.length - 1)
                functionBuilder.append("+ ");
        }

        if (maximalize)
            functionBuilder.append(" <- maximalize\n");
        else
            functionBuilder.append(" <- minimalize\n");
        return functionBuilder.toString();
    }
}