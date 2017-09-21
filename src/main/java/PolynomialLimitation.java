/**
 * Covered data of polynomial limitation, lets user to check if provided point is
 * fulfil by for limitation and could represent it in String.
 */
class PolynomialLimitation extends PolynomialForm {

    private boolean greaterOrEqual;
    private Double constantTerm;

    PolynomialLimitation(Double[] coefficients, Integer[] indicators, Double constantTerm, Boolean greaterOrEqual)
            throws DifferentDimensionException, IncorrectCalculationException {
        super(coefficients,indicators);
        if(constantTerm == null || greaterOrEqual == null) {
            System.err.printf("Program exit, null founded during create polynomial limit.");
            System.exit(1);
        }
        this.constantTerm = constantTerm;
        this.greaterOrEqual = greaterOrEqual;
    }

    public boolean checkPolynomialGreater(Double[] coordinates)
            throws DifferentDimensionException, IncorrectCalculationException {
        if(greaterOrEqual)
            return calculatePolynomialValue(coordinates) >= constantTerm;
        else
            return calculatePolynomialValue(coordinates) <= constantTerm;
    }

    @Override
    public String toString() {
        StringBuilder limitBuilder = new StringBuilder();

        limitBuilder.append(prepareCoefficientsAndIndicators());
        limitBuilder.append(prepareGreaterSymbols());
        limitBuilder.append(prepareConstatntTerm());

        return limitBuilder.toString();
    }

    private String prepareCoefficientsAndIndicators() {
        StringBuilder coefAndIndi = new StringBuilder();

        for(int i = 0; i < coefficients.length; ++i)
        {
            coefAndIndi.append(coefficients[i]);
            coefAndIndi.append(" x_(");
            coefAndIndi.append(i+1);
            coefAndIndi.append(")^(");
            coefAndIndi.append(indicators[i]);
            coefAndIndi.append(")");
            if (i != coefficients.length - 1)
                coefAndIndi.append(" + ");
        }

        return coefAndIndi.toString();
    }

    private String prepareGreaterSymbols() {
        if(greaterOrEqual)
            return " >= ";
        else
            return " <= ";
    }

    private String prepareConstatntTerm() {
        return constantTerm + "\n";
    }
}