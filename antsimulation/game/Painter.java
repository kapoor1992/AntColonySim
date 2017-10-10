package antsimulation.game;

import java.awt.Graphics;
import antsimulation.game.ant.Ants;
import antsimulation.game.terrain.Terrain;

// Draws everything.
public class Painter extends GameElement {
    private Terrain terrain;
    private Ants ants;
    private Background background;
    
    public Painter(int height, int width, Terrain terrain, 
                   Ants ants, Background background) {
        super(height, width);
        
        this.terrain = terrain;
        this.ants = ants;
        this.background = background;
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        background.draw(graphics);
        ants.draw(graphics);
        terrain.draw(graphics);
    }
}
