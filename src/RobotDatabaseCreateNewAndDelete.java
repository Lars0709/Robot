import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RobotDatabaseCreateNewAndDelete extends RobotEdit {

    void robotCreateNewFile() {

        Scanner scanner1 = new Scanner(System.in);

        System.out.println("What is the name of the robot you want to create?");
        Path robotToCreate = Paths.get(scanner1.nextLine());
        String robotName = String.valueOf(robotToCreate);

        /*
        int maxCharacterLengthNewRobot = robotName.length();
        boolean nameSizeOk = false;
        if (maxCharacterLengthNewRobot >= 255){
            nameSizeOk = true;
        }
         */

        System.out.println(" Robot to create is: " + robotToCreate);

        System.out.println("\"Confirm?\" -> yes or no");
        String confirmCreate = scanner1.nextLine();
        System.out.println("Selection: \"" + confirmCreate + "\"");

        Path robotArchive = Paths.get("RobotArchive");
        robotToCreate = Paths.get(robotArchive.toString(), robotToCreate + ".txt");

        if (!Files.exists(robotToCreate)) {
            try {
                Files.createFile(robotToCreate);
                robotInputCoordinatesAndWriteToFile(robotToCreate);
                System.out.println("Robot \"" + robotName + "\" was created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Robot exists already and was not created!");
        }
    }

    void robotDeleteFile() throws IOException {

        Path robotArchive = Paths.get("RobotArchive");
        System.out.println(listFilesInRobotArchive1());

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Which robot should be deleted?");
        Path robotToDelete = Paths.get(scanner1.nextLine());
        System.out.println(" Robot to delete is: " + robotToDelete);

        System.out.println("\"Confirm?\" -> yes or no");
        String confirmDelete = scanner1.nextLine();
        System.out.println("Selection: \"" + confirmDelete + "\"");


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

    private Set<String> listFilesInRobotArchive1() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("RobotArchive"))) {
            return stream
                    .filter(path -> !Files.isDirectory(path))
                    .map(path -> path.getFileName())
                    .map(path -> path.toString())
                    .map(path -> path.replaceAll(".txt", ""))
                    .collect(Collectors.toSet());
        }
    }

    Set<String> listFilesInRobotArchive2() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("RobotArchive"))) {
            return stream
                    .filter(path -> !Files.isDirectory(path))
                    .map(path -> path.getFileName())
                    .map(path -> path.toString())
                    .map(path -> path.replaceAll(".txt", ""))
                    .collect(Collectors.toSet());
        }
    }

    private void robotInputCoordinatesAndWriteToFile(Path robotToCreate) {

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;
        String robotCoordinateX = "";
        String robotCoordinateY = "";


        while (!correctInput) {
            System.out.println("Enter starting coordinate \"X\":");
            robotCoordinateX = scanner1.nextLine().replaceAll(" ", "");
            if (robotCoordinateX.matches("^\\d+")) {
                System.out.println("The entered X-coordinate is: \"" + robotCoordinateX + "\"");
                correctInput = true;
            }
        }

        correctInput = false;
        while (!correctInput) {
            System.out.println("Enter starting coordinate \"Y\":");
            robotCoordinateY = scanner1.nextLine().replaceAll(" ", "");
            if (robotCoordinateY.matches("^\\d+")) {
                System.out.println("The entered Y-coordinate is: \"" + robotCoordinateY + "\"");
                correctInput = true;
            }
        }

        try {
            FileWriter robotFileWriter = new FileWriter(String.valueOf(robotToCreate));
            BufferedWriter robotBufferedWriter = new BufferedWriter(robotFileWriter);

            robotBufferedWriter.write(robotCoordinateX);
            robotBufferedWriter.newLine();
            robotBufferedWriter.write(robotCoordinateY);
            robotBufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing file!");
            e.printStackTrace();
        }
    }

    ArrayList<Integer> pullDataFromFiles(String chosenRobot) {

        Path robotArchive = Paths.get("RobotArchive");
        Path robotToRead = Paths.get(robotArchive.toString(), chosenRobot + ".txt");

        ArrayList<String> linesFromFileAsString = new ArrayList<>();

        try {
            Scanner scannerReadFile = new Scanner(robotToRead);
            while (scannerReadFile.hasNextLine()) {
                linesFromFileAsString.add(scannerReadFile.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Reading File and filling ArrayList!");
        }

        System.out.println("Coordinates X and Y: " + linesFromFileAsString);

        ArrayList<Integer> linesFromFileAsInteger = new ArrayList<>();
        linesFromFileAsInteger.add(0, Integer.parseInt(linesFromFileAsString.get(0)));
        linesFromFileAsInteger.add(1, Integer.parseInt(linesFromFileAsString.get(1)));

        return linesFromFileAsInteger;
    }

    String robotDecideDirection() {

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;
        String direction = "";
        String axisToMoveOn = "";

        while (!correctInput) {

            System.out.println("Type \"north\", \"south\", \"west\" or \"east\" to set the direction.");
            System.out.println("Type \"exit\" to exit");
            System.out.print("Input: ");

            String inputEditOrMove = scanner1.nextLine().toLowerCase().replaceAll(" ", "");

            switch (inputEditOrMove) {
                case "north":
                    direction = "north";
                    axisToMoveOn = "x+";
                    correctInput = true;
                    break;
                case "south":
                    direction = "south";
                    axisToMoveOn = "x-";
                    correctInput = true;
                    break;
                case "west":
                    direction = "west";
                    axisToMoveOn = "y+";
                    correctInput = true;
                    break;
                case "east":
                    direction = "east";
                    axisToMoveOn = "y-";
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
        System.out.println("Direction set: " + direction);
        System.out.println("Axis Movement: " + axisToMoveOn);

        return axisToMoveOn;
    }

    void updateExistingFile(String chosenRobot, ArrayList<Integer> linesFromFileAsInteger) {

        Path robotName = Paths.get(chosenRobot);

        Path robotArchive = Paths.get("RobotArchive");
        robotName = Paths.get(robotArchive.toString(), robotName + ".txt");

        System.out.println(linesFromFileAsInteger + " Arraylist in updateExistingFile");


    }

    private void robotReadDataFromFile() {

        Scanner scannerNameInput = new Scanner(System.in);
        System.out.println("What is the name of the robot you want to read?");
        Path robotToRead = Paths.get(scannerNameInput.nextLine());

        Path robotArchive = Paths.get("RobotArchive");
        robotToRead = Paths.get(robotArchive.toString(), robotToRead + ".txt");

        if (Files.exists(robotToRead)) {
            try {
                Scanner scannerReadFile = new Scanner(robotToRead);
                while (scannerReadFile.hasNextLine()) {
                    String readDataString = scannerReadFile.nextLine();
                    System.out.println(readDataString);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Reading File!");
            }
        } else {
            System.out.println("File \"" + robotToRead + "\" does not exist!");
        }
    }

}
