package problem1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Mappable> places = List.of(
                new Building("Sydney Town Hall", UsageType.GOVERNMENT),
                new UtilityLine("College St", UtilityType.FIBER_OPTIC),
                new Building("Sydney Opera House", UsageType.ENTERTAINMENT),
                new Building("Central Apartments", UsageType.RESIDENTIAL),
                new Building("City Offices", UsageType.BUSINESS),
                new UtilityLine("Main St", UtilityType.ELECTRICAL),
                new UtilityLine("Market St", UtilityType.GAS),
                new UtilityLine("River Rd", UtilityType.WATER)
        );

        for (Mappable place : places) {
            Mappable.mapIt(place);
        }
    }
}
