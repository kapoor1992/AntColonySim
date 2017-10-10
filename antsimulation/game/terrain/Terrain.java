package antsimulation.game.terrain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import antsimulation.game.GameElement;

// Manages food, water, and poison.
public class Terrain extends GameElement {
    public static final int CELL_NUMS = 20; // Number of each type of terrain.
    private BufferedImage foodImg;
    private BufferedImage waterImg;
    private BufferedImage poisonImg;
    private Coordinate[] food;
    private Coordinate[] water;
    private Coordinate[] poison;
    private int foodSize;
    private int waterSize;
    private int poisonSize;
    
    public Terrain (int areaHeight, int areaWidth, String foodPath, String waterPath, String poisonPath) {
        super(areaHeight, areaWidth);
        
        try {
            foodImg = ImageIO.read(new File(foodPath));
            waterImg = ImageIO.read(new File(waterPath));
            poisonImg = ImageIO.read(new File(poisonPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        food = new Coordinate[CELL_NUMS];
        water = new Coordinate[CELL_NUMS];
        poison = new Coordinate[CELL_NUMS];
        foodSize = food.length;
        waterSize = water.length;
        poisonSize = poison.length;
        
        generateTerrain();
    }
    
    // Randomly generate terrain.
    public void generateTerrain() {
        int xf = Math.round((float)(Math.random() * (areaWidth)));
        int yf = Math.round((float)(Math.random() * (areaHeight)));
        int xw = Math.round((float)(Math.random() * (areaWidth)));
        int yw = Math.round((float)(Math.random() * (areaHeight)));
        int xp = Math.round((float)(Math.random() * (areaWidth)));
        int yp = Math.round((float)(Math.random() * (areaHeight)));
        
        for (int i = 0; i < foodSize; i++) {
            food[i] = new Coordinate(xf, yf);
            water[i] = new Coordinate(xw, yw);
            poison[i] = new Coordinate(xp, yp);
            xf = Math.round((float)(Math.random() * (areaWidth)));
            yf = Math.round((float)(Math.random() * (areaHeight)));
            xw = Math.round((float)(Math.random() * (areaWidth)));
            yw = Math.round((float)(Math.random() * (areaHeight)));
            xp = Math.round((float)(Math.random() * (areaWidth)));
            yp = Math.round((float)(Math.random() * (areaHeight)));
        }
    }
    
    public Coordinate[] getFood() {
        return food;
    }
    
    public Coordinate[] getWater() {
        return water;
    }
    
    public Coordinate[] getPoison() {
        return poison;
    }
    
    // Remove a terrain.
    public void remove (int index, int key) {
        Coordinate[] list = null;
        boolean shift = false;
        
        if (key == 0)
            list = food;
        else
            list = water;
                
        for (int i = 0; i < list.length; i++) {
            if (shift) {
                list[i - 1] = list[i];
            } else if (i == index) {
                list[i] = null;
                shift = true;
            }
        }
        
        if (key == 0) {
            food = list;
            foodSize--;
        } else {
            water = list;
            waterSize--;
        }
    }
    
    // Draw terrain.
    public void draw(Graphics graphics) {
        for (int i = 0; i < foodSize; i++)
            graphics.drawImage(foodImg, food[i].getX(), food[i].getY(), null);
        
        for (int i = 0; i < waterSize; i++)
            graphics.drawImage(waterImg, water[i].getX(), water[i].getY(), null);
        
        for (int i = 0; i < poisonSize; i++)
            graphics.drawImage(poisonImg, poison[i].getX(), poison[i].getY(), null);
    }
}