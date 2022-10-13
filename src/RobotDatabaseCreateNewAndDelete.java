import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RobotDatabaseCreateNewAndDelete extends RobotEdit {

    static void robotCreateNewFile() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("What is the name of the robot you want to create?");
        Path robotToCreate = Paths.get(scanner1.nextLine());
        System.out.println(" Robot to create is: " + robotToCreate);

        robotInputCoordinates();

        Path robotArchive = Paths.get("RobotArchive");
        robotToCreate = Paths.get(robotArchive.toString(), robotToCreate + ".txt");

        if (!Files.exists(robotToCreate)) {
            try {
                Files.createFile(robotToCreate);
                System.out.println("Robot \"" + robotToCreate + "\" created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Robot exists already and was not created!");
        }
    }

    static void robotDeleteFile() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Which robot should be deleted (File Name)");
        Path robotToDelete = Paths.get(scanner1.nextLine());
        System.out.println(" Robot to delete is: " + robotToDelete);

        Path robotArchive = Paths.get("RobotArchive");
        robotToDelete = Paths.get(robotArchive.toString(), robotToDelete + ".txt");

        if (Files.exists(robotToDelete)) {
            try {
                Files.deleteIfExists(robotToDelete);
                System.out.println("Robot deleted!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Robot not found!");
        }
    }

    static void robotReadFile() {
        System.out.println("Method: read existing robot file");
    }

    static void robotInputCoordinates(){

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        while (!correctInput) {
            System.out.println("Enter starting coordinate \"x\":");
            String robotCoordinateX = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            if (robotCoordinateX.matches("^\\d+")) {
                System.out.println("The entered x-coordinate is: " + robotCoordinateX);
                correctInput = true;
            }
        }
        correctInput = false;
        while (!correctInput) {
            System.out.println("Enter starting coordinate \"y\":");
            String robotCoordinateY = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            if (robotCoordinateY.matches("^\\d+")) {
                System.out.println("The entered x-coordinate is: " + robotCoordinateY);
                correctInput = true;
            }
        }
    }
}
