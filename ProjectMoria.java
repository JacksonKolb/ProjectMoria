package projectmoria;

import java.io.Serializable; //needed for saving the game's state
import java.util.Random;
import java.util.Scanner; //needed for user_input

public class ProjectMoria {

    public static final Scanner USERINPUT = new Scanner(System.in);
    public static final Random RAND = new Random();
    public static Room[][] currDungeon;
    public static Player currPlayer;

    public static void main(String[] args) {
        mainMenu();
    }
    //mainMenu - Only NewGame and Credits will work for now.
    public static void mainMenu() {
        boolean status = false;
        do {
            IO.Welcome();
            String selection = USERINPUT.nextLine();
            switch (selection) {
                case "1":
                    newGame();
                    status = true;
                    break;
                case "2":
                    loadGame();
                    status = true;
                    break;
                case "3":
                    settings();
                    status = true;
                    break;
                case "4":
                    IO.credits();
                    status = true;
                    break;
                case "5":
                    System.exit(0);
            }

        } while (status == false);

    }

    public static void newGame() {
        Dungeon dungeon = new Dungeon();
        boolean status = false;
        do {
            IO.newGameIntroduction();
            String selection = USERINPUT.nextLine();
            switch (selection) {
                case "1":
                    status = IO.displayPlayerStats("Warrior", "A tough, "
                            + "well-rounded fighter with a balanced skillset.",
                            100, 20, 30, 3, 0.10);
                    currPlayer = Player.newWarrior();

                    break;
                case "2":
                    status = IO.displayPlayerStats("Dueler", "A quick, nimble "
                            + "duelist with an aptitude for landing critical "
                            + "attacks.", 80, 10, 50, 2, 0.18);
                    currPlayer = Player.newDuelist();

                    break;
            }
        } while (status == false);

        currDungeon = Dungeon.newRandomDungeon(currPlayer);
        dungeon.dungeonLogic(currPlayer, currDungeon);

    }

    public static void loadGame() {

    }

    public static void settings() {

    }

}
