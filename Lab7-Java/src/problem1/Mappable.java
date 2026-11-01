package problem1;

public interface Mappable {
    String JSON_PROPERTY = "\"properties\":{%s}";

    String getLabel();

    String getMarker();

    Geometry getShape();

    default String toJSON() {
        return String.format("\"type\": \"%s\", \"label\": \"%s\", \"marker\": \"%s\"",
                getShape(), getLabel(), getMarker());
    }

    static void mapIt(Mappable mappable) {
        System.out.printf(JSON_PROPERTY + "%n", mappable.toJSON());
    }
}
