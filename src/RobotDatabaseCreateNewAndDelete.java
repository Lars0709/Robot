import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RobotDatabaseCreateNewAndDelete extends RobotEdit {

    void robotCreateNewFile() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("What is the name of the robot you want to create?");
        Path robotToCreate = Paths.get(scanner1.nextLine());
        System.out.println(" Robot to create is: " + robotToCreate);
        String robotName = String.valueOf(robotToCreate);

        Path robotArchive = Paths.get("RobotArchive");
        robotToCreate = Paths.get(robotArchive.toString(), robotToCreate + ".txt");

        if (!Files.exists(robotToCreate)) {
            try {
                Files.createFile(robotToCreate);
                robotInputCoordinates(robotToCreate);
                System.out.println("Robot \"" + robotName + "\"was created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Robot exists already and was not created!");
        }
    }

    void robotDeleteFile() throws IOException {

        Path robotArchive = Paths.get("RobotArchive");
        System.out.println(listFilesInRobotArchive("RobotArchive"));

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Which robot should be deleted?");
        Path robotToDelete = Paths.get(scanner1.nextLine());
        System.out.println(" Robot to delete is: " + robotToDelete);


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

    private Set<String> listFilesInRobotArchive(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))){
            return stream
                    .filter(path -> !Files.isDirectory(path))
                    .map(path -> path.getFileName())
                    .map(path -> path.toString())
                    .map(path -> path.replaceAll(".txt", ""))
                    .collect(Collectors.toSet());
        }
    }

    private void robotInputCoordinates(Path robotToCreate){

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;
        String robotCoordinateX = "";
        String robotCoordinateY = "";


        while (!correctInput) {
            System.out.println("Enter starting coordinate \"x\":");
            robotCoordinateX = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            if (robotCoordinateX.matches("^\\d+")) {
                System.out.println("The entered X-coordinate is: " + robotCoordinateX);
                correctInput = true;
            }
        }
        correctInput = false;
        while (!correctInput) {
            System.out.println("Enter starting coordinate \"y\":");
            robotCoordinateY = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            if (robotCoordinateY.matches("^\\d+")) {
                System.out.println("The entered Y-coordinate is: " + robotCoordinateY);
                correctInput = true;
            }
        }
        try{
            FileWriter robotFileWriter = new FileWriter(String.valueOf(robotToCreate));
            robotFileWriter.write("X-coordinate: "+ robotCoordinateX);
            robotFileWriter.write("Y-coordinate: "+ robotCoordinateY);
            robotFileWriter.close();
        }catch (IOException e){
            System.out.println("Error while writing file!");
            e.printStackTrace();
        }
    }

    void robotReadFile() {
        System.out.println("Method: read existing robot file");
    }
}
