import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.*;

public class SolverTest {

    StringBuilder testBuilderSimplex = new StringBuilder();
    StringBuilder testBuilderGeo = new StringBuilder();

    @Before
    public void setUpSimplex() throws Exception {
        testBuilderSimplex.append("3\n");
        testBuilderSimplex.append("10 10 10\n");
        testBuilderSimplex.append("5 4 3\n");
        testBuilderSimplex.append("1 1 1\n");
        testBuilderSimplex.append("y\n");
        testBuilderSimplex.append("3\n");
        testBuilderSimplex.append("2 3 1\n");
        testBuilderSimplex.append("1 1 1\n");
        testBuilderSimplex.append("5\n");
        testBuilderSimplex.append("n\n");
        testBuilderSimplex.append("4 1 2\n");
        testBuilderSimplex.append("1 1 1\n");
        testBuilderSimplex.append("11\n");
        testBuilderSimplex.append("n\n");
        testBuilderSimplex.append("3 4 2\n");
        testBuilderSimplex.append("1 1 1\n");
        testBuilderSimplex.append("8\n");
        testBuilderSimplex.append("n\n");
    }

    @Before
    public void setUpGeo() throws Exception {
        testBuilderGeo.append("4\n");
        testBuilderGeo.append("40000 40000 10000 10000\n");
        testBuilderGeo.append("4 6 3 12\n");
        testBuilderGeo.append("1 1 1 1\n");
        testBuilderGeo.append("y\n");
        testBuilderGeo.append("2\n");
        testBuilderGeo.append("1 2 1.5 6\n");
        testBuilderGeo.append("1 1 1 1\n");
        testBuilderGeo.append("90000\n");
        testBuilderGeo.append("n\n");
        testBuilderGeo.append("2 2 1.5 4\n");
        testBuilderGeo.append("1 1 1 1\n");
        testBuilderGeo.append("120000\n");
        testBuilderGeo.append("n\n");
    }

    //TODO refactor needed
    @Test
    public void proceedMonteCarloSimplexExample() throws Exception {
        LimitsAndGoalHolder holder = new LimitsAndGoalHolder();
        BestPointCalculator calculator = new MonteCarloCalculator(50000,0000001.0);
        Solver s = new Solver(calculator,holder);
        Scanner sc = new Scanner(testBuilderSimplex.toString());
        Double[] bestTestCoords;

        holder.inputData(sc);
        calculator.setCalculationProperties(holder);
        bestTestCoords = s.proceedCalculations().getCoordinates();
        assertEquals(2.0,bestTestCoords[0],0.5);
        assertEquals(0.0,bestTestCoords[1],0.2);
        assertEquals(1.0,bestTestCoords[2],0.5);
        System.out.println();
        for(Double d : bestTestCoords)
            System.out.println(d);
    }

    @Test
    public void proceedMonteCarloGeoExample() throws Exception {
        LimitsAndGoalHolder holder = new LimitsAndGoalHolder();
        BestPointCalculator calculator = new MonteCarloCalculator(50000,0000001.0);
        Solver s = new Solver(calculator,holder);
        Scanner sc = new Scanner(testBuilderGeo.toString());
        Double[] bestTestCoords;

        holder.inputData(sc);
        calculator.setCalculationProperties(holder);
        bestTestCoords = s.proceedCalculations().getCoordinates();
        assertEquals(30000.0,bestTestCoords[0],4000);
        assertEquals(30000.0,bestTestCoords[1],4000);
        assertEquals(0.0,bestTestCoords[2],2000);
        assertEquals(0.0,bestTestCoords[3],2000);
        System.out.println();
        for(Double d : bestTestCoords)
            System.out.println(d);
    }
}