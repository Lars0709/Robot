import java.util.Scanner;

class RobotEdit extends RobotMain {

    static void inputDeleteOrNewRobot(String inputEditOrMove) {

        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        label:
        while (!correctInput) {

            System.out.println("Type \"delete\" to delete a robot.");
            System.out.println("type \"new\" to create a new robot.");
            System.out.println("Type \"exit\" to exit.");
            System.out.print("Input: ");

            String inputDeleteOrNewRobot = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            System.out.println(inputDeleteOrNewRobot);

            switch (inputDeleteOrNewRobot) {
                case "delete":
                    deleteRobot(inputDeleteOrNewRobot);
                    correctInput = true;
                    break;
                case "new":
                    createNewRobot(inputDeleteOrNewRobot);
                    correctInput = true;
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("No valid entry!");
                    System.out.println();
                    break;
            }
        }
    }

    private static void createNewRobot(String inputDeleteOrNewRobot) {

        System.out.println("Method create new robot");

        System.out.println("Name the new robot: \t\t10 characters max!");
        Scanner scanner1 = new Scanner(System.in);
        String robotName = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

        System.out.println("The robots name is: " + robotName);

        System.out.println("Enter starting coordinate \"x\":");
        int robotCoordinateX = scanner1.nextInt();
        System.out.println("The entered x-coordinate is: " + robotCoordinateX);

        System.out.println("Enter starting coordinate \"y\":");
        int robotCoordinateY = scanner1.nextInt();
        System.out.println("The entered y-coordinate is: " + robotCoordinateY);

        RobotDatabase.robotCreateNewFile();
    }

    private static void deleteRobot(String inputDeleteOrNewRobot) {

        System.out.println("Method delete robot");

        System.out.println("What is the name of the robot you want to delete?");
        Scanner scanner1 = new Scanner(System.in);
        String robotName = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

        System.out.println("The robots name for deletion is: " + robotName);

        RobotDatabase.robotDeleteFile();
    }
}
