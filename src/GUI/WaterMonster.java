
package GUI;

import Logic.Handler;
import Logic.StaticEntity;
import java.awt.Graphics;

public class WaterMonster extends StaticEntity{

    public WaterMonster(Handler handler, float x, float y) {
         super(handler, x, y, Tile.TILEWIDHT, Tile.TILEHEIGHT);
        
        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 10 ;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void tick() {

    }
    
    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.waterItem.crateNew((int) x,(int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.monster_water, (int) (x - handler.getGameCamera().getxOffset()) ,(int) (y - handler.getGameCamera().getyOffset()) ,width,height, null);
    }
    
}
