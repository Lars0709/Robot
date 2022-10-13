import java.util.Scanner;

public class RobotMain {

    public static void main(String[] args) {

        RobotMain robotMain = new RobotMain();

        robotMain.userInputAtStartOfProgram();

    }


    private void userInputAtStartOfProgram() {

        Scanner scanner1 = new Scanner(System.in);
        boolean correctInput = false;

        while (!correctInput) {

            System.out.println("Type \"edit\" to delete or create a new robot.");
            System.out.println("type \"move\" to move existing robot.");
            System.out.println("Type \"exit\" to exit.");
            System.out.print("Input: ");

            String inputEditOrMove = scanner1.nextLine().toLowerCase().replaceAll(" ", "");
            System.out.println(inputEditOrMove);

            switch (inputEditOrMove) {
                case "edit":
                    correctInput = true;
                    RobotEdit.inputDeleteOrNewRobot(inputEditOrMove);
                    break;
                case "move":
                    correctInput = true;
                    RobotMove.robotMove(inputEditOrMove);
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
}
