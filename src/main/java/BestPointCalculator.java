/**
 * Strategy pattern for separate calculation method from presentation and data input.
 */
public interface BestPointCalculator {
    public Point calculateBestPoint() throws DifferentDimensionException, IncorrectCalculationException;
    public void setCalculationProperties(LimitsAndGoalHolder h);
}
