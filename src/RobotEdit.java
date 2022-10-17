import java.io.IOException;
import java.util.Scanner;

class RobotEdit extends RobotMain {

     void inputDeleteOrNewRobot() throws IOException {
        RobotEdit robotObjectInputDeleteOrNew = new RobotEdit();
        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        breakWhileLoop:
        while (!correctInput) {

            System.out.println("Type \"delete\" to delete a robot.");
            System.out.println("Type \"new\" to create a new robot.");
            System.out.println("Type \"exit\" to exit.");
            System.out.println("Type \"back\" to go one step back.");
            System.out.print("Input: ");
            System.out.print("");

            String inputDeleteOrNewRobot = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

            switch (inputDeleteOrNewRobot) {
                case "delete":
                    robotObjectInputDeleteOrNew.deleteRobot();
                    correctInput = true;
                    break;
                case "new":
                    robotObjectInputDeleteOrNew.createNewRobot();
                    correctInput = true;
                    break;
                case "back":
                    System.out.println("Should go back to main menu");

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

    private void createNewRobot() {

        System.out.println("");
        RobotDatabaseCreateNewAndDelete robotObjectDatabaseCreateNewAndDelete = new RobotDatabaseCreateNewAndDelete();
        robotObjectDatabaseCreateNewAndDelete.robotCreateNewFile();
    }

    private void deleteRobot() throws IOException {

        System.out.println("");
        RobotDatabaseCreateNewAndDelete robotObjectDatabaseCreateNewAndDelete = new RobotDatabaseCreateNewAndDelete();
        robotObjectDatabaseCreateNewAndDelete.robotDeleteFile();
    }
}
