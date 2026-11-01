package problem1;

public class Building implements Mappable {
    private final String name;
    private final UsageType type;

    public Building(String name, UsageType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return name + " (" + type + ")";
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case BUSINESS -> Color.BLACK + " " + PointMarker.CIRCLE;
            case ENTERTAINMENT -> Color.GREEN + " " + PointMarker.TRIANGLE;
            case GOVERNMENT -> Color.RED + " " + PointMarker.STAR;
            case RESIDENTIAL -> Color.BLUE + " " + PointMarker.SQUARE;
        };
    }

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON()
                + String.format(", \"name\": \"%s\", \"usage\": \"%s\"", name, type);
    }
}
