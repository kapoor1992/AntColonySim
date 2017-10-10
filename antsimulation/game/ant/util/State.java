package antsimulation.game.ant.util;

// States in the state machine for each ant.
// "Die" state not required explicitly.
public final class State {
    public static final char FIND_FOOD = 'F';
    public static final char FIND_WATER = 'W';
    public static final char GO_HOME = 'H';
    public static final char BREED = 'B';
    
    private State() {
    }
}