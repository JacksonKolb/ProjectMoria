/* Item Class - Handles the creation and random drops of Items in the dungeon.
 * So far, players can use potions to heal themselves.
*/
package projectmoria;

public class Item {

    private final String name;
    private final String type;
    private final String description;

    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public void use(Player player, Item item) {
        if (item.type.equals("Potion")) {
            player.heal(item);
        }
    }

    public static void addPotion(int numOfPotions, Player player) {
        for (int i = 0; i < numOfPotions; i++) {
            player.getInventory().add(potion());
        }
    }

    private static Item potion() {
        return new Item("Potion", "Potion", " a small vial filled with a "
                + "translucent red liquid. Heals 20 HP.");
    }

    private static Item superPotion() {
        return new Item("Super Potion", "Potion", " a medium sized vial filled"
                + " with a translucent red liquid. Heals 40 HP.");
    }

    private static Item critPotion() {
        return new Item("Critical Hit Potion", "Potion", "a small oval shaped"
                + " vial filled with a bubbling blue liquid. Increases crit chance "
                + "by 10%");
    }

    private static Item teleportPotion() {
        return new Item("Teleport Potion", "Potion", "a potion used for"
                + "teleporting to the beginning of the dungeon.");
    }

    public static Item newRandomItem() {
        Item randomItem = null;
        int random = ProjectMoria.RAND.nextInt(3) + 1; //Change random value parameter to add more room types
        switch (random) {
            case 1:
                randomItem = (potion());
            case 2:
                randomItem = (superPotion());
            case 3:
                randomItem = (critPotion());
            case 4:
                randomItem = (teleportPotion());
        }
        return randomItem;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
