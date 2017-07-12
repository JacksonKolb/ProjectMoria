/*
 * Jackson Kolb
 * Program #9, CS 1050, Fall 2015, Mw 6pm
 * java 7.0, Desktop, Windows 10
 */
package projectmoria;

import java.util.Random;

/**
 *
 * @author Jackson
 */
public class Player {

   

    private final String name;
    private final String description;
    private final int maxHitPoints;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final int defense;
    private double critChance;
    private int currX;
    private int currY;
    private Room currRoom;

    public Player(String name, String description, int maxHitPoints,
            int minDamage, int maxDamage, int defense, double critChance) {
        this.name = name;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = maxHitPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.defense = defense;
        this.critChance = critChance;
        this.currX = 14;
        this.currY = 14;
    }

    public int attack() {
        return ProjectMoria.RAND.nextInt(maxDamage - minDamage + 1);
    }

    public int defend(Monster monster) {
        int incomingAttack = monster.attack();
        int random = ProjectMoria.RAND.nextInt(99) + 1;
        if (random <= monster.getCritChance()) {
            incomingAttack = incomingAttack * 2;
            IO.monsterCrit(); //TODO - move to different spot
        }
        IO.playerHitPointsMessage(incomingAttack, monster);
        hitPoints = (hitPoints * defense > incomingAttack)
                ? hitPoints - incomingAttack : 0;
        return hitPoints;
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

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getDefense() {
        return defense;
    }

    public double getCritChance() {
        return critChance;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    public Room getCurrRoom() {
        return currRoom;
    }
    
    public void setCurrRoom(Room room) {
        currRoom = room;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }
    
    

    public static Player newWarrior() {
        return new Player("Warrior", "A tough, well-rounded fighter with"
                + " a balanced skillset.", 100, 20, 30, 3, 10);
    }

    public static Player newDuelist() {
        return new Player("Duelist", "A quick, nimble duelist with an"
                + " aptitude for landing critical attacks.", 80, 10, 50, 2, 18);
    }

}
