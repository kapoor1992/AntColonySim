package antsimulation.game;

import java.awt.Graphics;

// Used to initialize game background.
class Background extends GameElement {
    public Background(int height, int width, String path) {
        super(height, width, path);
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
    }
}