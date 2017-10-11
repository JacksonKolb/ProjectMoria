/* Jackson Kolb
   Dungeon Class - Handles the creation of the random 30x30 array of room 
   objects, which randomly places monster objects in each room.  This class also
   handles the player movement logic, by determining if there are rooms to the 4
   defined directions (north, south, east, and west).
 */
package projectmoria;

public final class Dungeon {

    private static boolean northDirection = false;
    private static boolean southDirection = false;
    private static boolean westDirection = false;
    private static boolean eastDirection = false;

    public static Room[][] newRandomDungeon(Player player) {
        Room[][] dungeon = new Room[30][30];
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon.length; j++) {
                dungeon[i][j] = Room.newRoomInstance();
            }
        }
        player.setCurrRoom(dungeon[14][14]);
        return dungeon;
    }

    public boolean roomExists(int x, int y) {
        return (rowExists(x)) && (colExists(y));
    }

    public boolean rowExists(int x) {
        return (x >= 0) && (x <= 29);
    }

    public boolean colExists(int y) {
        return (y >= 0) && (y <= 29);
    }

    public void playerMovement(Player player) {
        northDirection = roomExists(player.getCurrX(), player.getCurrY()
                + 1);
        southDirection = roomExists(player.getCurrX(), player.getCurrY()
                - 1);
        eastDirection = roomExists(player.getCurrX() + 1,
                player.getCurrY());
        westDirection = roomExists(player.getCurrX() - 1,
                player.getCurrY());
        IO.movePlayer(player);

    }

    public void battle(Player player, Monster monster, Room[][] dungeon) {
        IO.battleIntro(player, dungeon[player.getCurrX()][player.getCurrY()]);
        IO.battle(player, monster, dungeon[player.getCurrX()][player.getCurrY()]);
    }
    /* dungeonLogic - while the player is alive, and the monster in the 
    *  current room is alive, initiate a battle. Otherwise, if the player is
    *  alive and the monster is dead, let the player move.
    */
    public void dungeonLogic(Player player, Room[][] dungeon) {
        while (player.isAlive()) {
            if (player.isAlive() && dungeon[player.getCurrX()][player.getCurrY()]
                    .getMonster().isAlive()) {
                battle(player, dungeon[player.getCurrX()][player.getCurrY()]
                        .getMonster(), ProjectMoria.currDungeon);
            } else if (player.isAlive()) {
                playerMovement(player);
            }
        }
    }

    public static boolean isNorthDirection() {
        return northDirection;
    }

    public static boolean isSouthDirection() {
        return southDirection;
    }

    public static boolean isWestDirection() {
        return westDirection;
    }

    public static boolean isEastDirection() {
        return eastDirection;
    }

}
