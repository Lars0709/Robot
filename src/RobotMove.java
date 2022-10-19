import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class RobotMove extends RobotMain {

    /**
     * User input in program decides to move a robot or exit.
     * @throws IOException Files
     */

    void robotMove() throws IOException {

        Scanner scanner1 = new Scanner(System.in);

        while (true) {
            System.out.println("\"Move or exit?\"");
            System.out.println("Enter \"move\" to move a robot.");
            System.out.println("Enter \"back\" to go one step back.");
            System.out.println("Enter \"exit\" to end the program.");
            String confirmCreate = scanner1.nextLine();
            System.out.println("Selection: \"" + confirmCreate + "\"");
            System.out.println();

            if (confirmCreate.equals("move")) {
                RobotDatabase robotObjectDatabase = new RobotDatabase();
                System.out.println(robotObjectDatabase.listFilesInRobotArchive2());

                String chosenRobot;

                while (true){

                    System.out.println("Which robot do you want to move?");
                    chosenRobot = scanner1.nextLine();
                    Path robotArchive = Paths.get("RobotArchive");
                    Path robotToRead = Paths.get(robotArchive.toString(), chosenRobot + ".txt");
                    if (Files.exists(robotToRead)) {
                        System.out.println("Robot found!");
                        robotObjectDatabase.pullDataFromFiles(chosenRobot);
                        break;
                    } else {
                        System.out.println("Robot not found!");
                        System.out.println();
                    }
                }

                String axisToMoveOn = robotObjectDatabase.robotDecideDirection();
                ArrayList<Integer> linesFromFileAsInteger = robotObjectDatabase.pullDataFromFiles(chosenRobot);
                moveRobotHowFar(axisToMoveOn, linesFromFileAsInteger);

                robotObjectDatabase.updateExistingFile(chosenRobot, linesFromFileAsInteger);
            }else if (confirmCreate.equals("exit")) {
                break;
            }else if (confirmCreate.equals("back")){
                System.out.println();
                break;
            }else{
                System.out.println("No valid entry!");
                System.out.println();
            }
        }
        RobotMain robotMain = new RobotMain();
        robotMain.userInputAtStartOfProgram();
    }

    /**
     * Asks input for how many steps the robot will be moved.
     * @param axisToMoveOn the axis and direction the robot will move
     * @param linesFromFileAsInteger the position of the robot as ArrayList<Integer>
     */

    private void moveRobotHowFar(String axisToMoveOn, ArrayList<Integer> linesFromFileAsInteger) {

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        while (!correctInput) {

            System.out.println("How many steps should the robot go?");
            String moveDistanceString = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            int moveDistance;

            if ( moveDistanceString.matches("^\\d+")){

                switch (axisToMoveOn) {
                    case "x+":
                        moveDistance = Integer.parseInt(moveDistanceString);
                        moveDistance = linesFromFileAsInteger.get(0) + moveDistance;
                        linesFromFileAsInteger.set(0, moveDistance);
                        System.out.println(linesFromFileAsInteger);
                        System.out.println();
                        correctInput = true;
                        break;
                    case "x-":
                        moveDistance = Integer.parseInt(moveDistanceString);
                        moveDistance = linesFromFileAsInteger.get(0) - moveDistance;
                        linesFromFileAsInteger.set(0, moveDistance);
                        System.out.println(linesFromFileAsInteger);
                        System.out.println();
                        correctInput = true;
                        break;
                    case "y-":
                        moveDistance = Integer.parseInt(moveDistanceString);
                        moveDistance = linesFromFileAsInteger.get(1) + moveDistance;
                        linesFromFileAsInteger.set(1, moveDistance);
                        System.out.println(linesFromFileAsInteger);
                        System.out.println();
                        correctInput = true;
                        break;
                    case "y+":
                        moveDistance = Integer.parseInt(moveDistanceString);
                        moveDistance = linesFromFileAsInteger.get(1) - moveDistance;
                        linesFromFileAsInteger.set(1, moveDistance);
                        System.out.println(linesFromFileAsInteger);
                        System.out.println();
                        correctInput = true;
                        break;
                    default:
                        System.out.println("Error: moveRobotHowFar");
                        break;
                }
            }else{
                System.out.println("No valid entry!");
                System.out.println();
            }
        }
    }
}
