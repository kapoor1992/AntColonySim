package antsimulation;

import antsimulation.game.GameController;
import java.io.File;
import java.util.Scanner;

// Entry point.
public class AntSimulationMain {
    public static void main(String[] args) throws InterruptedException {
        // Set most arguments to be passed.
        int aX = 500;
        int aY = 500;
        int tX = 20;
        int tY = 20;
        int nX = 15;
        int nY = 15;
        
        // Grab images.
        String fp = new File("antsimulation/images/food.jpg").getAbsolutePath();
        String wp = new File("antsimulation/images/water.jpg").getAbsolutePath();
        String pp = new File("antsimulation/images/poison.jpg").getAbsolutePath();
        String ap = new File("antsimulation/images/ant.jpg").getAbsolutePath();
        String bp = new File("antsimulation/images/background.jpg").getAbsolutePath();
        
        // Gives brief information.
        intro();
        
        // Get starting ants.
        int a = getStartingAnts();
        
        // Start game.
        GameController gc = new GameController(aY, aX, tY, tX, nX, nY, fp, wp, pp, ap, bp, a);
    }
    
    public static void intro() {
        System.out.println("Welcome to the ant colony simulation!\n\n" +
                           "Ants start by randomly roaming around for food.\n" + 
                           "If they find food, they return home and breed.\n" +
                           "If they bred, they roam for water.\n" + 
                           "Once they find water, they roam for food once again.\n" +
                           "If at any point an ant runs into poison, it immediately dies.\n\n");
    }
    
    // Get a number between 1 and 10 (inclusive).
    public static int getStartingAnts() {
        Scanner in = new Scanner(System.in);
        String response;
        int result;
        
        try {
            System.out.println("Please specify the number of starting ants (between 1 and 15):");
            response = in.nextLine();
            response.replaceAll("\\s","");
            result = Integer.parseInt(response);
            
            if (result >= 1 && result <= 15)
                return result;
            else
                throw new IllegalArgumentException();
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.\n");
            return getStartingAnts();
        }
    }
}