import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class RobotMove extends RobotMain {

    void robotMove() throws IOException {

        Scanner scanner1 = new Scanner(System.in);
        RobotDatabaseCreateNewAndDelete robotObjectDatabaseCreateNewAndDelete = new RobotDatabaseCreateNewAndDelete();
        System.out.println(robotObjectDatabaseCreateNewAndDelete.listFilesInRobotArchive2());

        System.out.println("Which robot do you want to move?");
        String chosenRobot = scanner1.nextLine();
        robotObjectDatabaseCreateNewAndDelete.pullDataFromFiles(chosenRobot);

        String axisToMoveOn = robotObjectDatabaseCreateNewAndDelete.robotDecideDirection();
        ArrayList<Integer> linesFromFileAsInteger = robotObjectDatabaseCreateNewAndDelete.pullDataFromFiles(chosenRobot);
        moveRobotHowFar(axisToMoveOn, linesFromFileAsInteger);

        robotObjectDatabaseCreateNewAndDelete.updateExistingFile(chosenRobot, linesFromFileAsInteger);
    }

    private ArrayList<Integer> moveRobotHowFar(String axisToMoveOn, ArrayList<Integer> linesFromFileAsInteger) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("How many steps should the robot go?");
        int moveDistance = scanner1.nextInt();

        if (axisToMoveOn.equals("x+")) {

            moveDistance = linesFromFileAsInteger.get(0) + moveDistance;
            linesFromFileAsInteger.set(0, moveDistance);
            System.out.println(linesFromFileAsInteger);

        } else if (axisToMoveOn.equals("x-")) {

            moveDistance = linesFromFileAsInteger.get(0) - moveDistance;
            linesFromFileAsInteger.set(0, moveDistance);
            System.out.println(linesFromFileAsInteger);

        } else if (axisToMoveOn.equals("y+")) {

            moveDistance = linesFromFileAsInteger.get(1) + moveDistance;
            linesFromFileAsInteger.set(1, moveDistance);
            System.out.println(linesFromFileAsInteger);

        } else if (axisToMoveOn.equals("y-")) {

            moveDistance = linesFromFileAsInteger.get(1) - moveDistance;
            linesFromFileAsInteger.set(1, moveDistance);
            System.out.println(linesFromFileAsInteger);

        } else {
            System.out.println("Error: moveRobotHowFar");
        }
        return linesFromFileAsInteger;
    }
}
