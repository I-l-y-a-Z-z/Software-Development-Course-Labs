package problem3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Tim", 10, 15);
        List<String> saved = player.write();
        player.setWeapon("Axe");
        player.setHitPoints(5);
        System.out.println("Changed: " + player);
        player.read(saved);
        System.out.println("Restored: " + player);

        ISaveable monster = new Monster("Werewolf", 20, 40);
        List<String> savedMonster = monster.write();
        monster.read(List.of("Dragon", "100", "80"));
        System.out.println("Changed: " + monster);
        monster.read(savedMonster);
        System.out.println("Restored: " + monster);
    }
}
