
package GUI;

import GUI.Assets;
import GUI.Tile;
import GUI.Tree;
import Logic.Handler;
import Logic.World;
import java.awt.Graphics;

public class GameState extends States{

   
    private World world;
   
    
    public GameState(Handler handler) {
        
        super(handler);
        world = new World(handler,"res/world/world1.txt");
        handler.setWorld(world);

    }
 
    @Override
    public void tick() {
        world.tick();
  
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
      
    }
    
}
