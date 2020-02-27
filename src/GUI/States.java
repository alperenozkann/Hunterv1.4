
package GUI;

import Logic.Handler;
import java.awt.Graphics;

public abstract class States {
    
    private static States currentState = null;
    
    public static void setStates(States state){
        currentState = state;
    }

    public static States getStates() {
        return currentState;
    }
    
    protected Handler handler;
    
    public States(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
