import java.util.ArrayList;
import java.util.Scanner;

public class LimitsAndGoalHolder {

    private GoalFunction goalFunction;
    private ConstantLimitations constantLimitations = new ConstantLimitations();
    private ArrayList<PolynomialLimitation> polynomialLimitations =
            new ArrayList<PolynomialLimitation>();

    public GoalFunction getGoalFunction() {
        return goalFunction;
    }

    public ConstantLimitations getConstantLimitations() {
        return constantLimitations;
    }

    public ArrayList<PolynomialLimitation> getPolynomialLimitations() {
        return polynomialLimitations;
    }


    /**
     * Lets user to specify math problem. Ask for dimension, goal function and limitations, both
     * constant and polynomial. In case of wrong entry, informs about mistake and repeat input.
     *
     * @param userInput Scanner objects with string lines, which specify details of math problem.
     *                  There is no check for null or empty value.
     * @throws DifferentDimensionException Threw if arguments lengths
     *      are not equal.
     */
    public void inputData(Scanner userInput)
            throws DifferentDimensionException, IncorrectCalculationException {
        Integer dimension = null;
        Integer numberOfPolynomialLimitations = null;

        System.out.printf("Enter dimension. ");
        while((dimension = inputDimesion(userInput)) == null)
            System.err.printf("Dimension must be positive integer.\n");
        System.out.printf("Enter constant limitations. ");
        while(!inputConstantLimitations(userInput,dimension))
            System.err.printf("Try again...\n");
        System.out.printf("Enter goal function. ");
        while(!inputGoalFunction(userInput,dimension))
            System.err.printf("Try again...\n");
        System.out.printf("Enter number of polynomial limitations. ");
        while((numberOfPolynomialLimitations = inputPolynomialLimitationsNumber(userInput)) == null)
            System.err.printf("This number must be positive integer.\n");
        for(int i = 0; i < numberOfPolynomialLimitations; ++i)
        {
            System.out.printf("Enter %d polynomial limitation. ",i+1);
            while(!inputPolynomialLimitation(userInput,dimension))
                System.err.printf("Try again...\n");
        }
    }

    private Integer inputDimesion(Scanner userInput) {
        Integer dimension = null;

        try {
            dimension = Integer.parseInt(userInput.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }

        if(dimension > 0)
            return dimension;
        return null;
    }

    private boolean inputConstantLimitations(Scanner userInput, Integer dimension) throws DifferentDimensionException {
        Double[] rightLimitations = new Double[dimension];
        Double[] leftLimitations = new Double[dimension];
        for(int i = 0; i < leftLimitations.length; ++i)
            leftLimitations[i] = 0.0;

        System.out.printf("One positive double value for every dimension separated " +
                "with white space: \n");
        try {
            String line = userInput.nextLine();
            String[] limitationsEntry = line.split("\\s");

            if(!dimension.equals(limitationsEntry.length)) {
                System.err.printf("Wrong input String format, write all " +
                        "limitations separated with white space! ");
                return false;
            }

            for(int i = 0; i < dimension; ++i) {
                Double limitation = Double.parseDouble(limitationsEntry[i]);
                if(limitation <= 0.0) {
                    System.err.printf("Right limit must be positive double value! ");
                    return false;
                }
                rightLimitations[i] = Double.parseDouble(limitationsEntry[i]);
            }
        } catch (NumberFormatException e) {
            System.err.printf("Limitation must be double value! ");
            return false;
        }
        constantLimitations.setConstrants(leftLimitations,rightLimitations);
        return true;
    }

    private boolean inputGoalFunction(Scanner userInput, Integer dimension)
            throws DifferentDimensionException, IncorrectCalculationException {
        boolean maximalize = false;
        Double[] coefficients = new Double[dimension];
        Integer[] indicators = new Integer[dimension];

        System.out.printf("In one line enter separated with white space coefficients " +
                "and in second line input separated indicators (positive integers), " +
                "both number must be equal to dimension: \n");
        try {
            String line = userInput.nextLine();
            String[] splitedEntry = line.split("\\s");

            if(!dimension.equals(splitedEntry.length)) {
                System.err.printf("Wrong input String format, write all " +
                        "coefficients separated with white space! ");
                return false;
            }

            for(int i = 0; i < dimension; ++i)
                coefficients[i] = Double.parseDouble(splitedEntry[i]);

            line = userInput.nextLine();
            splitedEntry = line.split("\\s");

            if(!dimension.equals(splitedEntry.length)) {
                System.err.printf("Wrong input String format, write all " +
                        "indicators separated with white space! ");
                return false;
            }

            for(int i = 0; i < dimension; ++i) {
                Integer indicator = Integer.parseInt(splitedEntry[i]);
                if(indicator <= 0.0) {
                    System.err.printf("Indicator must be positive integer! ");
                    return false;
                }
                indicators[i] = indicator;
            }
        } catch (NumberFormatException e) {
            System.err.printf("Coefficient must be double, indicator must be positive integer! ");
            return false;
        }

        System.out.printf("Maximalize goal function? [y/n]: ");
        String line = userInput.nextLine();
        if(line != null && !line.equals(""))
            if(line.charAt(0) == 'y' || line.charAt(0) == 'Y')
                maximalize = true;

        goalFunction = new GoalFunction(coefficients,indicators,maximalize);
        return true;
    }

    private Integer inputPolynomialLimitationsNumber(Scanner userInput) {
        Integer limitationsNumber = null;

        try {
            limitationsNumber = Integer.parseInt(userInput.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }

        if(limitationsNumber > 0)
            return limitationsNumber;
        return null;
    }

    private boolean inputPolynomialLimitation(Scanner userInput, Integer dimension)
            throws DifferentDimensionException, IncorrectCalculationException {
        boolean greater = false;
        Double constantValue;
        Double[] coefficients = new Double[dimension];
        Integer[] indicators = new Integer[dimension];

        System.out.printf("In one line enter separated with white space coefficients " +
                "and in second line input separated indicators (positive integers), " +
                "both number must be equal to dimension: \n");
        try {
            String line = userInput.nextLine();
            String[] splitedEntry = line.split("\\s");

            if(!dimension.equals(splitedEntry.length)) {
                System.err.printf("Wrong input String format, write all " +
                        "coefficients separated with white space! ");
                return false;
            }

            for(int i = 0; i < dimension; ++i)
                coefficients[i] = Double.parseDouble(splitedEntry[i]);

            line = userInput.nextLine();
            splitedEntry = line.split("\\s");

            if(!dimension.equals(splitedEntry.length)) {
                System.err.printf("Wrong input String format, write all " +
                        "indicators separated with white space! ");
                return false;
            }

            for(int i = 0; i < dimension; ++i) {
                Integer indicator = Integer.parseInt(splitedEntry[i]);
                if(indicator <= 0.0) {
                    System.err.printf("Indicator must be positive integer! ");
                    return false;
                }
                indicators[i] = indicator;
            }
        } catch (NumberFormatException e) {
            System.err.printf("Coefficient must be double, indicator must be positive integer! ");
            return false;
        }

        System.out.printf("Enter constant value associated with this limitation: ");
        try {
            constantValue = Double.parseDouble(userInput.nextLine());
        } catch (NumberFormatException e) {
            return false;
        }

        System.out.printf("Greater or equal? [y/n]: ");
        String line = userInput.nextLine();
        if(line != null && !line.equals(""))
            if(line.charAt(0) == 'y' || line.charAt(0) == 'Y')
                greater = true;

        polynomialLimitations.
                add(new PolynomialLimitation(coefficients,indicators,constantValue,greater));
        return true;
    }
}
