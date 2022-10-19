import java.io.IOException;
import java.util.Scanner;

public class RobotMain {

    /**
     * Main method for initiating Program.
     * @throws IOException Files
     */

    public static void main(String[] args) throws IOException {

        RobotMain robotMain = new RobotMain();

        robotMain.userInputAtStartOfProgram();

    }

    /**
     * First decision in program decides to edit, move or exit.
     * @throws IOException Files
     */

    void userInputAtStartOfProgram() throws IOException {

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        breakWhileLoop:
        while (!correctInput) {

            System.out.println("Type \"edit\" to delete or create a new robot.");
            System.out.println("Type \"move\" to move existing robot.");
            System.out.println("Type \"exit\" to exit.");
            System.out.print("Input: ");

            String inputEditOrMove = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

            switch (inputEditOrMove) {
                case "edit":
                    correctInput = true;
                    inputDeleteOrNewRobot();
                    break;
                case "move":
                    correctInput = true;
                    System.out.println();
                    RobotMove robotObjectMove = new RobotMove();
                    robotObjectMove.robotMove();
                    break;
                case "exit":
                    break breakWhileLoop;
                default:
                    System.out.println("No valid entry!");
                    System.out.println();
                    break;
            }
        }
    }

    /**
     * User input in program decides to new, delete or exit.
     * @throws IOException Files
     */

    void inputDeleteOrNewRobot() throws IOException {

        RobotDatabase robotObjectDatabaseCreateNewAndDelete = new RobotDatabase();
        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        breakWhileLoop:
        while (!correctInput) {

            System.out.println("Enter \"delete\" to delete a robot.");
            System.out.println("Enter \"new\" to create a new robot.");
            System.out.println("Enter \"exit\" to exit.");
            System.out.println("Enter \"back\" to go one step back.");
            System.out.print("Input: ");
            System.out.print("");

            String inputDeleteOrNewRobot = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

            switch (inputDeleteOrNewRobot) {
                case "delete":
                    robotObjectDatabaseCreateNewAndDelete.robotDeleteFile();
                    correctInput = true;
                    break;
                case "new":
                    robotObjectDatabaseCreateNewAndDelete.robotCreateNewFile();
                    correctInput = true;
                    break;
                case "back":
                    System.out.println();
                    userInputAtStartOfProgram();
                    break breakWhileLoop;
                case "exit":
                    break breakWhileLoop;
                default:
                    System.out.println("No valid entry!");
                    System.out.println();
                    break;
            }
        }
    }
}
