package problem3;

import java.util.ArrayList;
import java.util.List;

public class Monster implements ISaveable {
    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() { return name; }
    public int getHitPoints() { return hitPoints; }
    public int getStrength() { return strength; }

    @Override
    public List<String> write() {
        return new ArrayList<>(List.of(name, Integer.toString(hitPoints), Integer.toString(strength)));
    }

    @Override
    public void read(List<String> values) {
        if (values == null || values.isEmpty()) return;
        if (values.size() != 3) throw new IllegalArgumentException("Expected 3 saved fields");
        int savedHitPoints = Integer.parseInt(values.get(1));
        int savedStrength = Integer.parseInt(values.get(2));
        name = values.get(0);
        hitPoints = savedHitPoints;
        strength = savedStrength;
    }

    @Override
    public String toString() {
        return "Monster{name='" + name + "', hitPoints=" + hitPoints
                + ", strength=" + strength + "}";
    }
}
