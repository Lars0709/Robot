import java.util.Scanner;

class RobotEdit extends RobotMain {

    static void inputDeleteOrNewRobot(String inputEditOrMove) {
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
            System.out.println(inputDeleteOrNewRobot);

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

        System.out.println("Method createNewRobot to Method robotCreateNewFile");
        RobotDatabaseCreateNewAndDelete.robotCreateNewFile();
    }

    private void deleteRobot(String inputDeleteOrNewRobot) {

        /*
        System.out.println("What is the name of the robot you want to delete?");
        Scanner scanner1 = new Scanner(System.in);
        String robotName = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

        System.out.println("The robots name for deletion is: " + robotName);
         */

        System.out.println("Method deleteRobot to Method robotDeleteFile");
        RobotDatabaseCreateNewAndDelete.robotDeleteFile();
    }
}
