//package Extension;
//
//import java.util.*;
//
//import static Extension.AlCabohne.playAlCabohne;
//import static Extension.Game.playGame;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Menu:");
//            System.out.println("1. Play Original Game");
//            System.out.println("2. Play Al Cabohne Extension");
//            System.out.println("3. Cards");
//            System.out.println("4. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    playGame(scanner);
//                    break;
//                case 2:
//                    playAlCabohne(scanner);
//                    break;
//                case 3:
//                    Cards.showCardDetails();
//                    break;
//                case 4:
//                    System.out.println("Exiting the game. Goodbye!");
//                    scanner.close();
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//}

package Extension;

import io.bitbucket.plt.sdp.bohnanza.gui.GUI;
import io.bitbucket.plt.sdp.bohnanza.gui.Size;
import io.bitbucket.plt.sdp.bohnanza.gui.Color;
import io.bitbucket.plt.sdp.bohnanza.gui.Coordinate;

import java.util.Scanner;

import static Extension.AlCabohne.playAlCabohne;
import static Extension.Game.playGame;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Play Original Game");
            System.out.println("2. Play Al Cabohne Extension");
            System.out.println("3. Cards");
            System.out.println("4. Play with GUI");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    playGame(scanner);
                    break;
                case 2:
                    playAlCabohne(scanner);
                    break;
                case 3:
                    Cards.showCardDetails();
                    break;
                case 4:
                    startGUI();
                    break;
                case 5:
                    System.out.println("Exiting the game. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void startGUI() {
        // Initialize the GUI with some example parameters
        Size initialWindowSize = new Size(800, 600);
        Size cardSize = new Size(100, 150);
        Size zoomedCardSize = new Size(200, 300);
        Color background = new Color(255, 255, 255); // White background
        Color foreground = new Color(0, 0, 0); // Black foreground

        // Create the GUI instance
        //GUI gui = new GUI(initialWindowSize, cardSize, zoomedCardSize, background, foreground);
        GUI gui = new GUI(new Size(800, 600), new Size(60, 90), new Size(240, 360), new Color(0,0,0), new Color(255,255,255));
        // Add some example cards and compartments for demonstration
        //gui.addCard(Cards.GARTENBOHNE, new Coordinate(50, 50));
        gui.addCompartment(new Coordinate(200, 200), new Size(300, 200), "Example Compartment");

        // Start the GUI



        gui.start();
    }
}

