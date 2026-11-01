package problem1;

public class UtilityLine implements Mappable {
    private final String name;
    private final UtilityType type;

    public UtilityLine(String name, UtilityType type) {
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
            case ELECTRICAL -> Color.RED + " " + LineMarker.DASHED;
            case FIBER_OPTIC -> Color.GREEN + " " + LineMarker.DOTTED;
            case GAS -> Color.ORANGE + " " + LineMarker.SOLID;
            case WATER -> Color.BLUE + " " + LineMarker.SOLID;
        };
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON()
                + String.format(", \"name\": \"%s\", \"utility\": \"%s\"", name, type);
    }
}
