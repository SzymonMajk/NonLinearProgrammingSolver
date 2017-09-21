import java.util.Scanner;

/**
 * Main program class with starting point. Lets user introduce nonlinear
 * programming problem and uses monte carlo method to provide set of coordinates
 * with best goal function result.
 */
public class Solver {

    private LimitsAndGoalHolder holder;
    private BestPointCalculator calculator;

    Solver (BestPointCalculator calc, LimitsAndGoalHolder h) {
        calculator = calc;
        holder = h;
    }

    public Point proceedCalculations() throws IncorrectCalculationException, DifferentDimensionException {
        return calculator.calculateBestPoint();
    }

    private void presentProblem() {
        System.out.printf("\n|---------------------|\nThe problem you set:\n");
        System.out.printf("Your goal function:\n" + holder.getGoalFunction());
        System.out.printf("Your constant limitations:\n"+holder.getConstantLimitations());
        System.out.printf("Your polynomial limitations:\n");
        for(PolynomialLimitation limit : holder.getPolynomialLimitations())
            System.out.printf(limit.toString());
        System.out.printf("|---------------------|\n\n");
    }

    private void presentResults(Point best) throws DifferentDimensionException, IncorrectCalculationException {
        if (best.getCoordinates() == null) {
            System.out.printf("Impossible to find solution, check limitations and try again.");
            return;
        }

        System.out.printf("Best point: "+best);
        System.out.printf("\nGoalFuntion value for best point: "
                + holder.getGoalFunction().calculatePolynomialValue(best.getCoordinates()));
    }

    /**
     * Simple presentation of possible best use of program. Ask user about mathematical
     * problem: dimension, goal function, constant and polynomial limitation, present
     * problem in command line and use monte carlo method to return best possible set
     * of coordinates for this problem, which is presented in command line too. If no
     * appropriate set of coordinates might have been found, inform user about problem.
     * Catch DifferentDimensionException if any found during calculating.
     *
     * @param args not in use.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LimitsAndGoalHolder holder = new LimitsAndGoalHolder();
        BestPointCalculator calculator = new MonteCarloCalculator(50000,000001.0);
        Solver s = new Solver(calculator,holder);
        Point best;

        try {
            s.holder.inputData(sc);
            s.presentProblem();
            calculator.setCalculationProperties(holder);
            best = s.proceedCalculations();
            s.presentResults(best);
        } catch (IncorrectCalculationException e1) {
            e1.printStackTrace();
        } catch (DifferentDimensionException e2) {
            e2.printStackTrace();
        }
    }
}