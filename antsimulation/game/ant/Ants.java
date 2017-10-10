package antsimulation.game.ant;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import antsimulation.game.GameElement;
import antsimulation.game.terrain.Coordinate;
import antsimulation.game.ant.util.State;

// Manages ants.
public class Ants extends GameElement {
    private ArrayList ants = new ArrayList();
    private int antHeight;
    private int antWidth;
    
    public Ants (int areaHeight, int areaWidth, String path, int antHeight, int antWidth, int startingAnts) {
        super(areaHeight, areaWidth, path);
        
        this.antHeight = antHeight;
        this.antWidth = antWidth;
        
        initAnts(startingAnts);
    }
    
    // Set a particular state for an ant.
    public void setState(char state, int obst) {
        Ant a = (Ant)ants.get(obst);
        a.setState(state);
        
        if (state == State.BREED)
            newAnt();
    }
    
    public void newAnt() {
        Ant p = new Ant(areaHeight, areaWidth);
        ants.add(p);
    }
    
    public Ant getAnt (int index) {
        return (Ant)ants.get(index);
    }
    
    // Check if an ant collided with any terrain in a set.
    public int[] didCollide(Coordinate[] terrains, int width, int height) {
        Ant p;
        int x;
        int y;
        int tx;
        int ty;
        
        // Compare with each ant.
        for (int i = 0; i < ants.size(); i++) {
            p = (Ant)ants.get(i);
            x = p.getX();
            y = p.getY();
            // Compare with each terrain.
            for (int j = 0; j < terrains.length; j++) {
                if (terrains[j] != null) {
                    tx = terrains[j].getX();
                    ty = terrains[j].getY();
                    // These statements ensure any touch to be a collision.
                    if (x >= tx && x <= tx + width && y >= ty && y <= ty + height) {
                        return new int[] {i, j};
                    }
                    if (x + antWidth >= tx && x + antWidth <= tx + width && y + antHeight >= ty && y + antHeight <= ty + width) {
                        return new int[] {i, j};
                    }
                    if (x >= tx && x <= tx + width && y + antHeight >= ty && y + antHeight <= ty + height) {
                        return new int[] {i, j};
                    }
                    if (x + antWidth >= tx && x + antWidth <= tx + width && y >= ty && y <= ty + height) {
                        return new int[] {i, j};
                    }
                }
            }
        }
        
        // Return special value for no collision.
        return new int[] {-1, -1};
    }
    
    // Check if any ant got home.
    public int isHome() {
        Ant a;
        
        for (int i = 0; i < ants.size(); i++) {
            a = (Ant)ants.get(i);
            if (a.isHome())
                return i;
        }
        
        // Return special value for none.
        return -1;
    }
    
    // Kill an ant.
    public void remove (int index) {
        ListIterator li = ants.listIterator();
        li.next();
        for (int i = 0; i < ants.size(); i++) {
            if (i == index) {
                li.remove();
                return;
            } else {
                li.next();
            }
        }
    }
    
    // Draw an ant.
    public void draw(Graphics graphics) {
        Ant a;
        char state;
        int x;
        int y;
        graphics.setColor(Color.RED);
        
        // Draw each ant.
        for (int i = 0; i < ants.size(); i++) {
            a = (Ant)ants.get(i);
            state = a.getState();
            
            if (state == State.GO_HOME) {
                a.goHome();
            } else if (state == State.FIND_FOOD || state == State.FIND_WATER) {
                a.wander();
            }
            
            x = a.getX();
            y = a.getY();
            
            graphics.drawImage(image, x, y, null);
        }
    }
    
    // Set starting number of ants.
    private void initAnts (int num) {
        for (int i = 0; i < num; i++)
            newAnt();
    }
}
