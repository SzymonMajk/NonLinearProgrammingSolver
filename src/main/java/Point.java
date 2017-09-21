/**
 * Hold table of double values, represents coordinates in table length dimension
 * universe. Lets to return coordinates, and calculate value, where goal function
 * is necessary. Overrides toString method, where return String with point representation.
 */
public class Point {

    private Double[] coordinates;
    public Double[] getCoordinates() {
        return coordinates;
    }

    Point(Double[] coordinates) {
        if(coordinates == null) {
            System.err.printf("Program exit, null found during point creation.");
            System.exit(1);
        }
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        StringBuilder pointBuilder = new StringBuilder();

        pointBuilder.append("Point coordinates: ");
        pointBuilder.append(preparePointCoordinates());

        return pointBuilder.toString();
    }

    private String preparePointCoordinates() {
        StringBuilder pointCoors = new StringBuilder();
        for (Double d : coordinates) {
            pointCoors.append(d);
            pointCoors.append(" ");
        }
        return pointCoors.toString();
    }
}