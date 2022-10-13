import java.io.IOException;
import java.util.Scanner;

class RobotEdit extends RobotMain {

     void inputDeleteOrNewRobot(String inputEditOrMove) throws IOException {
        RobotEdit robotObjectInputDeleteOrNew = new RobotEdit();
        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        while (!correctInput) {

            System.out.println("Type \"delete\" to delete a robot.");
            System.out.println("type \"new\" to create a new robot.");
            System.out.println("Type \"exit\" to exit.");
            System.out.print("Input: ");

            String inputDeleteOrNewRobot = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

            switch (inputDeleteOrNewRobot) {
                case "delete":
                    robotObjectInputDeleteOrNew.deleteRobot(inputDeleteOrNewRobot);
                    correctInput = true;
                    break;
                case "new":
                    robotObjectInputDeleteOrNew.createNewRobot(inputDeleteOrNewRobot);
                    correctInput = true;
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("No valid entry!");
                    System.out.println();
                    break;
            }
        }
    }

    private void createNewRobot(String inputDeleteOrNewRobot) {


        Scanner scanner1 = new Scanner(System.in);

        /*
        System.out.println("Name the new robot: \t\t10 characters max!");
        String robotName = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

        System.out.println("The robots name is: " + robotName);


         */

        System.out.println("");
        RobotDatabaseCreateNewAndDelete robotObjectDatabaseCreateNewAndDelete = new RobotDatabaseCreateNewAndDelete();
        robotObjectDatabaseCreateNewAndDelete.robotCreateNewFile();
    }

    private void deleteRobot(String inputDeleteOrNewRobot) throws IOException {

        /*
        System.out.println("What is the name of the robot you want to delete?");
        Scanner scanner1 = new Scanner(System.in);
        String robotName = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

        System.out.println("The robots name for deletion is: " + robotName);
         */

        System.out.println("");
        RobotDatabaseCreateNewAndDelete robotObjectDatabaseCreateNewAndDelete = new RobotDatabaseCreateNewAndDelete();
        robotObjectDatabaseCreateNewAndDelete.robotDeleteFile();
    }
}
