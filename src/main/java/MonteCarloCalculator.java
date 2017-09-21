import java.util.ArrayList;

/**
 * Created to provide Monte Carlo random method for calculate best point.
 */
class MonteCarloCalculator implements BestPointCalculator {

    private GoalFunction goalFunction;
    private ConstantLimitations constantLimitations;
    private ArrayList<PolynomialLimitation> polynomialLimitation;
    private Integer sampleNumber;
    private final Double maximalRange;
    private Double[] bestCoordinates;

    MonteCarloCalculator(Integer sampleNumber, Double maximalRange) {
        this.sampleNumber = sampleNumber;
        this.maximalRange = maximalRange;
    }

    public void setCalculationProperties(LimitsAndGoalHolder h) {
        goalFunction = h.getGoalFunction();
        constantLimitations = h.getConstantLimitations();
        polynomialLimitation = h.getPolynomialLimitations();
    }

    public Point calculateBestPoint()
            throws DifferentDimensionException, IncorrectCalculationException {
        Double currentRange = 10.0;

        while(currentRange > maximalRange) {
            Double[] currentCoordinates;

            Generate : for(int j = 0; j < sampleNumber; ++j) {
                currentCoordinates = constantLimitations.generateNewCoordinates();
                for(PolynomialLimitation limit : polynomialLimitation)
                    if (!limit.checkPolynomialGreater(currentCoordinates))
                        continue Generate;
                bestCoordinates = getBetterPoint(bestCoordinates,currentCoordinates);
            }
            currentRange = currentRange * 0.5;
            constantLimitations.scaleConstrantsNearPoint(new Point(bestCoordinates),currentRange);
        }

        return new Point(bestCoordinates);
    }

    private Double[] getBetterPoint(Double[] bestSet ,Double[] newSet)
            throws DifferentDimensionException, IncorrectCalculationException {
        if(bestSet == null)
            return newSet;
        if(goalFunction.getMaximalize()) {
            if(goalFunction.calculatePolynomialValue(bestSet) <
                    goalFunction.calculatePolynomialValue(newSet))
                return newSet;
            return bestSet;
        }
        else {
            if(goalFunction.calculatePolynomialValue(bestSet) >
                    goalFunction.calculatePolynomialValue(newSet))
                return newSet;
            return bestSet;
        }
    }
}