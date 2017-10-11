/* Monster Class - There are a total of 4 monsters, which are all randomly 
* generated when a new game is selected.
*
*/

package projectmoria;

import java.util.Random;

public class Monster {

    private final String name;
    private final String description;
    private final int maxHitPoints;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final int defense;
    private final double critChance;
    private boolean aggression;
    private static final Random rand = new Random();
    

    public Monster(String name, String description, int maxHitPoints,
            int minDamage, int maxDamage, int defense, double critChance,
            boolean aggression) {
        this.name = name;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = maxHitPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.defense = defense;
        this.critChance = critChance;
        this.aggression = aggression;
    }

    public int attack() {
        return rand.nextInt(maxDamage - minDamage - 1);
    }

    public int defend(Player player) {
        int incomingAttack = player.attack();
        int random = rand.nextInt(99) + 1;
        if (random <= player.getCritChance()) {
            incomingAttack = incomingAttack * 2;
            IO.playerCrit();
        }
        IO.monsterHitPointsMessage(incomingAttack, this);
        hitPoints = (hitPoints * defense > incomingAttack)
                ? hitPoints - incomingAttack : 0;
        return hitPoints;
    }

    public static Monster newRandomMonster() {
        int random = rand.nextInt(4) + 1;
        Monster monster = null;
        switch (random) {
            case 1:
                monster = newGoblin();
                break;
            case 2:
                monster = newOrc();
                break;
            case 3:
                monster = newShadowAssassin();
                break;
            case 4:
                monster = newFeralGhoul();
                break;
        }
        return monster;
    }

    public static Monster newGoblin() {
        return new Monster("Goblin", "A grotesque, small enemy with a sub-par "
                + "skill-set. ", 30, 10, 15, 2, 10, true);
    }

    public static Monster newOrc() {
        return new Monster("Orc", "A brutish, green mass of destruction. High "
                + "Attack and Defense.", 50, 15, 20, 3, 10, true);
    }

    public static Monster newDragon() {
        return new Monster("Dragon", "A massive scaled wivern.  I hope you "
                + "brought some potions..", 100, 20, 30, 4, 11, true);
    }

    public static Monster newShadowAssassin() {
        return new Monster("Shadow Assassin", "A dark, elusive assassin trained"
                + " to land critical hits.  Low HP, with high attack.", 20, 15,
                35, 2, 20, true);
    }

    public static Monster newGhoul() {
        return new Monster("Ghoul", "A humaniod exposed to radiation, but still"
                + " maintains a consciousness.  Can be aggressive in certain "
                + "situations.", 40, 10, 25, 2, 8, false);
    }

    public static Monster newFeralGhoul() {
        return new Monster("Feral Ghoul", "A humaniod exposed to radiation, so "
                + "much that it can't distinguish friend from foe.",
                40, 10, 25, 2, 8, true);
    }

    public String getDescription() {
        return description;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + description;
    }

    public double getCritChance() {
        return critChance;
    }
}
