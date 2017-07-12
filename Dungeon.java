/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmoria;

/**
 *
 * @author kolbja
 */
public final class Dungeon {

    private static boolean northDirection = false;
    private static boolean southDirection = false;
    private static boolean westDirection = false;
    private static boolean eastDirection = false;

    public static Room[][] newRandomDungeon(Player player) {
        Room[][] dungeon = new Room[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                dungeon[i][j] = Room.newRoomInstance();

            }
        }
        player.setCurrRoom(dungeon[14][14]);
        return dungeon;
    }

    public boolean roomExists(int x, int y) {
        return ProjectMoria.currDungeon[x][y] != null;
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
        IO.battle(player, monster);
        playerMovement(player);
    }

    public void dungeonLogic(Player player, Room[][] dungeon) {
        while (player.isAlive() && dungeon[player.getCurrX()][player.getCurrY()]
                .getMonster().isAlive()) {
            battle(player, dungeon[player.getCurrX()][player.getCurrY()]
                    .getMonster(), ProjectMoria.currDungeon);
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
