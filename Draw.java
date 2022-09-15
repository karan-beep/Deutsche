import java.util.*;
public class Draw {

    public void draw (){
    }

    // Executes instructions to draw a blank canvas according to specified width and height
    public void drawCanvas(int width, int height){
        for (int i = 0; i < height + 2; i++){
            for (int j = 0; j <= width; j++) {

                // Draw blank Canvas
                if (i == 0 || i == height + 1) {
                    System.out.print("-");
                } else if (j == 0 || j == width) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    // Executes all previous commands including current command to draw a line or rectangle
    public void drawLineAndRectangle(int width, int height, int x1[], int y1[], int x2[], int y2[], char[] op, int count){
        for (int i = 0; i < height + 2; i++){
            process: for (int j = 0; j < width; j++) {

                // Loop through previous sequence of commands and draw Line/Rectangle based on operation type
                for (int k = 1;  k <= count; k++){
                    if (op[count] == 'L'){
                        if ((j >= x1[k] && j <= x2[k] && y1[k] == i) || (i >= y1[k] && i <= y2[k] && x1[k] == j)) {
                            System.out.print("X");
                            continue process;
                        }
                    }
                    if (op[count] == 'R'){
                        if ((j >= x1[k] && j <= x2[k] && (y1[k] == i || y2[k] == i)) ||
                                (i >= y1[k] && i <= y2[k] && (x1[k] == j || x2[k] == j))){
                            System.out.print("X");
                            continue process;
                        }
                    }
                }

                // Draw blank canvas
                if (i == 0 || i == height + 1) {
                    System.out.print("-");
                } else if (j == 0 || j == width - 1) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Draw obj = new Draw();

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        // Array variable to hold all sequence of operations
        char[] op = new char[100];

        // Variables to hold width, height and number of operations in the current program run
        int w = 0,h = 0, count = 0;

        // Array variables to hold coordinates for all commands chronologically
        int[] x1 = new int[100];
        int[] x2  = new int[100];
        int[] y1  = new int[100];
        int[] y2  = new int[100];

        // Keep program running until command Q is given
        while (isRunning) {
            System.out.print("Enter command: ");
            String command = sc.nextLine();

            // Split command into various parameters
            String[] coordinates = command.trim().split("\\s+");

            // Check for illegal number of arguments
            if ((coordinates.length > 5) || (coordinates.length == 1 && coordinates[0] == "")) {
                System.out.println("Wrong number of arguments");
                continue;
            }
            else
                op[count] = coordinates[0].charAt(0);

            // Quit
            if (op[count] == 'Q')
                break;

            // Draw Canvas or Line/Rectangle
            if (op[count] == 'C') {
                w =  Integer.parseInt(coordinates[1]);
                h =  Integer.parseInt(coordinates[2]);
                obj.drawCanvas(w, h);
            } else if (op[count] == 'L' || op[count] == 'R') {
                // Parse current command's coordinate
                x1[count] = Integer.parseInt(coordinates[1]);
                y1[count] = Integer.parseInt(coordinates[2]);
                x2[count] = Integer.parseInt(coordinates[3]);
                y2[count] = Integer.parseInt(coordinates[4]);

                // Pass updated sequence of commands to draw
                obj.drawLineAndRectangle(w, h, x1, y1, x2, y2, op, count);
            } else {
                System.out.println("Illegal command, please enter a valid command");
            }

        count++;
        }
    }
}
