package antsimulation.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import antsimulation.game.terrain.Terrain;
import antsimulation.game.ant.Ants;
import antsimulation.game.ant.Ant;
import antsimulation.game.ant.util.State;

// Sets up game and keeps tabs on the ants.
public class GameController implements ActionListener {
    private Timer timer;
    private JFrame window;
    private int areaHeight;
    private int areaWidth;
    private int terrainHeight;
    private int terrainWidth;
    private int antHeight;
    private int antWidth;
    private String foodPath;
    private String waterPath;
    private String poisonPath;
    private String antPath;
    private String backgroundPath;
    private int startingAnts;
    private Terrain terrain;
    private Ants ants;
    private Background background;
    private Painter painter;
    
    // Constructor.
    public GameController (int areaHeight, int areaWidth, int terrainHeight, int terrainWidth, int antHeight, int antWidth, 
                           String foodPath, String waterPath, String poisonPath, String antPath, String backgroundPath, 
                           int startingAnts) {
        timer = new Timer(5, this);
        
        window = new JFrame("Ant Simulation");
        
        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.terrainHeight = terrainHeight;
        this.terrainWidth = terrainWidth;
        this.antHeight = antHeight;
        this.antWidth = antWidth;
        this.foodPath = foodPath;
        this.waterPath = waterPath;
        this.poisonPath = poisonPath;
        this.antPath = antPath;
        this.backgroundPath = backgroundPath;
        this.startingAnts = startingAnts;
        
        initElements();
        attachElements();
        formatWindow();
        
        timer.start();
    }
    
    // Instantiate other package classes.
    private void initElements() {
        terrain = new Terrain(areaHeight, areaWidth, foodPath, waterPath, poisonPath);
        ants = new Ants(areaHeight, areaWidth, antPath, antHeight, antWidth, startingAnts);
        background = new Background(areaHeight, areaWidth, backgroundPath);
        painter = new Painter(areaHeight, areaWidth, terrain, ants, background);
    }
    
    // Layer the game window.
    private void attachElements() {
        window.add(painter);
        painter.add(background);
        background.add(terrain);
        terrain.add(ants);
    }
    
    // Adjust window parameters.
    private void formatWindow() {
        window.setResizable(false);
        window.setSize(areaHeight, areaWidth);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Transitions ants when necessary.
    @Override
    public void actionPerformed(ActionEvent event){
        Ant a;
        char state;
        int[] foodHit = ants.didCollide(terrain.getFood(), terrainWidth, terrainHeight);
        int[] waterHit = ants.didCollide(terrain.getWater(), terrainWidth, terrainHeight);
        int[] poisonHit = ants.didCollide(terrain.getPoison(), terrainWidth, terrainHeight);
        int homeHit = ants.isHome();
        
        // Did an ant find food?
        if (foodHit[0] != -1) {
            a = ants.getAnt(foodHit[0]);
            state = a.getState();
            
            // Were they looking for food?
            if (state == State.FIND_FOOD) {
                // Tell them to go home.
                ants.setState(State.GO_HOME, foodHit[0]);
                
                // Remove the food from game.
                if (foodHit[1] != -1) {
                    terrain.remove(foodHit[1], 0);
                }
            }
        }
        
        // Did an ant get home?
        if (homeHit != -1) {
            a = ants.getAnt(homeHit);
            state = a.getState();
            
            // Were they trying to get home?
            if (state == State.GO_HOME) {
                // Tell them to breed.
                ants.setState(State.BREED, homeHit);
                
            // Did they already breed?
            } else if (state == State.BREED) {
                // Tell them to look for water.
                ants.setState(State.FIND_WATER, homeHit);
            }
        }
        
        // Did an ant find water?
        if (waterHit[0] != -1) {
            a = ants.getAnt(waterHit[0]);
            state = a.getState();
            
            // Were they looking for water?
            if (state == State.FIND_WATER) {
                // Tell them to look for food.
                ants.setState(State.FIND_FOOD, waterHit[0]);
                
                // Remove the water from the game.
                if (waterHit[1] != -1) {
                    terrain.remove(waterHit[1], 1);
                }
            }
        }
        
        // Did an ant find poison?
        if (poisonHit[0] != -1) {
            // Murder them.
            ants.remove(poisonHit[0]);
        }
        
        // Draw frame.
        painter.repaint();
    }
}