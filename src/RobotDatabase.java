import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RobotDatabase extends RobotMain {

    /**
     * Creates new File in RobotArchive.
     */

    void robotCreateNewFile() throws IOException {

        System.out.println();
        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        whileLoop:
        while (!correctInput) {
            Path robotToCreate = null;
            String robotName = "";
            boolean nameFormatOk = false;
            boolean nameInputOk = false;

            while (!nameInputOk) {

                System.out.println("What is the name of the robot you want to create?");
                robotToCreate = Paths.get(scanner1.nextLine());
                robotName = String.valueOf(robotToCreate);
                int maxCharacterLengthNewRobot = robotName.length();
                if (maxCharacterLengthNewRobot >= 252){
                    System.out.println("No valid entry! -> 251 characters max");
                    System.out.println();
                    continue;
                }
                for (int i = 0; i < maxCharacterLengthNewRobot; i++){
                    if (!Character.isLetterOrDigit(robotName.charAt(i))){
                        nameFormatOk = false;
                        System.out.println("No valid entry! -> only numbers and letters");
                        System.out.println();
                        break;
                    }else{
                        nameFormatOk = true;
                    }
                }
                if (nameFormatOk){
                    break;
                }else{
                    System.out.println();
                }
            }

            System.out.println(" Robot to create is: " + robotToCreate);

            while (true){
                System.out.println("\"Confirm?\" -> yes or no");
                String confirmCreate = scanner1.nextLine();
                System.out.println("Selection: \"" + confirmCreate + "\"");

                if (confirmCreate.equals("yes")){
                    Path robotArchive = Paths.get("RobotArchive");
                    robotToCreate = Paths.get(robotArchive.toString(), robotToCreate + ".txt");
                    if (!Files.exists(robotToCreate)) {
                        try {
                            Files.createFile(robotToCreate);
                            robotInputCoordinatesForNewRobot(robotToCreate);
                            System.out.println("Robot \"" + robotName + "\" was created!");
                            System.out.println();
                            userInputAtStartOfProgram();
                            break whileLoop;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Error: Robot exists already and was not created!");
                        break;
                    }
                }else if (confirmCreate.equals("no")){
                    System.out.println("Robot \""+robotToCreate.toString()+"\" was not created!" );
                    System.out.println();
                    userInputAtStartOfProgram();
                    break whileLoop;
                }else{
                    System.out.println("No valid entry!");
                    System.out.println();
                }
            }
        }
    }

    /**
     * Deletes existing file from RobotArchive.
     * @throws IOException Files
     */

    void robotDeleteFile() throws IOException {

        System.out.println();
        Scanner scanner1 = new Scanner(System.in);

        whileLoop:
        while (true) {
            Path robotArchive = Paths.get("RobotArchive");
            System.out.println(listFilesInRobotArchive1());

            System.out.println("Which robot should be deleted?");
            Path robotToDelete = Paths.get(scanner1.nextLine());
            System.out.println(" Robot to delete is: " + robotToDelete);

            do {
                System.out.println("\"Confirm?\" -> \"yes\" or \"no\"");
                String confirmDelete = scanner1.nextLine();
                System.out.println("Selection: \"" + confirmDelete + "\"");

                if (confirmDelete.equals("yes")) {
                    robotToDelete = Paths.get(robotArchive.toString(), robotToDelete + ".txt");
                    if (Files.exists(robotToDelete)) {
                        try {
                            Files.deleteIfExists(robotToDelete);
                            System.out.println("Robot deleted!");
                            System.out.println();
                            userInputAtStartOfProgram();
                            break whileLoop;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Robot not found!");
                    }
                } else if (confirmDelete.equals("no")) {
                    System.out.println("Robot \"" + robotToDelete.toString() + "\" was not deleted!");
                    System.out.println();
                    userInputAtStartOfProgram();
                    break whileLoop;
                } else {
                    System.out.println("No valid entry!");
                    System.out.println();
                }

            } while (true);
        }
    }

    /**
     * Lists all existing files in RobotArchive.
     * @return all existing files in RobotArchive as String set
     * @throws IOException Files
     */

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

    /**
     * Lists all existing files in RobotArchive.
     * @return all existing files in RobotArchive as String set
     * @throws IOException Files
     */

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

    /**
     * Sets the coordinates for a new robot.
     * @param robotToCreate Robot name
     */

    private void robotInputCoordinatesForNewRobot(Path robotToCreate) {

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

    /**
     * Checks existing robot in RobotArchive for data.
     * @param chosenRobot robot name
     * @return content of file as ArrayList<Integer>
     */

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

    /**
     * Asks user to input the direction the robot will move.
     * @return x or y axis and direction on that axis
     */

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
                default:
                    System.out.println("No valid entry!");
                    System.out.println();
                    break;
            }
        }
        System.out.println("Direction set: \"" + direction+"\"");
        System.out.println();

        return axisToMoveOn;
    }

    /**
     * Updates existing file after robot movement.
     * @param chosenRobot robot name
     * @param linesFromFileAsInteger updated coordinates as ArrayList<Integer>
     * @throws IOException Files
     */

    void updateExistingFile(String chosenRobot, ArrayList<Integer> linesFromFileAsInteger) throws IOException {

        Path robotNamePath = Paths.get(chosenRobot);

        Path robotArchive = Paths.get("RobotArchive");
        robotNamePath = Paths.get(robotArchive.toString(), robotNamePath + ".txt");
        List<String> lines = Files.readAllLines(robotNamePath, StandardCharsets.UTF_8);
        lines.set(0, String.valueOf(linesFromFileAsInteger.get(0)));
        Files.write(robotNamePath, lines, StandardCharsets.UTF_8);

        lines.set(1, String.valueOf(linesFromFileAsInteger.get(1)));
        Files.write(robotNamePath, lines, StandardCharsets.UTF_8);
    }
}
