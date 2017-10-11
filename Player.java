/* Player Class - Currently, there are two players to choose from.  The duelist
*  class has been buffed for error testing, and exploring the dungeon entirely.
*
*/   
    package projectmoria;


    import java.util.ArrayList;
    import java.util.List;

    
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
        private List<Item> inventory;

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
            this.inventory = new ArrayList<>(10);
            Item.addPotion(3, this);
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

        public void heal(Item potion){
            this.hitPoints += 20;
            inventory.remove(potion);
            IO.heal(this.hitPoints);
        }

        public static Player newWarrior() {
            return new Player("Warrior", "A tough, well-rounded fighter with"
                    + " a balanced skillset.", 100, 20, 30, 3, 10);
        }

        public static Player newDuelist() {
            return new Player("Duelist", "A quick, nimble duelist with an"
                    + " aptitude for landing critical attacks.", 8000, 10, 50, 2, 
                    18);
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

        public List<Item> getInventory() {
            return inventory;
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
    }
