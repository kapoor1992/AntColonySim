package antsimulation.game.ant;

import antsimulation.game.ant.util.State;

// An individual ant.
public class Ant {
    public static final int SPEED = 1;
    private int x;
    private int y;
    private int areaHeight;
    private int areaWidth;
    private char state;
    
    public Ant(int areaHeight, int areaWidth) {
        this.x = areaWidth / 2;
        this.y = areaHeight / 2;
        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.state = State.FIND_FOOD;
    }
    
    public char getState() {
        return state;
    }
    
    public void setState (char state) {
        this.state = state;
    }
    
    // Check if an ant is at home.
    public boolean isHome() {
        if (x == areaWidth / 2 && y == areaHeight / 2)
            return true;
        return false;
    }
    
    // Roam around.
    public void wander() {
        int randX = (int)Math.round(Math.random());
        int randY = (int)Math.round(Math.random());
        
        if (randX == 0 && x < areaWidth)
            x += SPEED;
        else if (randX == 1 && x > 0)
            x -= SPEED;
        
        if (randY == 0 && y < areaHeight)
            y += SPEED;
        else if (randY == 1 && y > 0)
            y -= SPEED;
    }
    
    // Makes an ant go home.
    public void goHome() {
        if (x > areaWidth / 2)
            x -= SPEED;
        else if (x < areaWidth / 2)
            x += SPEED;
        
        if (y > areaHeight / 2)
            y -= SPEED;
        else if (y < areaHeight / 2)
            y+= SPEED;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
