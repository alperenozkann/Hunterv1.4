
package GUI;

import Logic.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {
    
    //Handler
    
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood,"Wood", 0);
    public static Item fireItem = new Item(Assets.fire,"Fire", 1);
    public static Item waterItem = new Item(Assets.water,"Water", 2);
    public static Item StoneItem = new Item(Assets.rock,"Stone", 3);
    
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    
    protected Rectangle bounds;
    
    protected int x, y, count;
    protected boolean pickedUP = false;
    
    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.count = count;
        count = 1;
        
        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        
        items[id] = this;
    }
    
    public void tick(){
        if (handler.getWorld().getEntityManager().getPlayer().getCollionsBounds(0f, 0f).intersects(bounds)) {
            pickedUP = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }
    
    public void render(Graphics g){
        if (handler == null) {
            return;
        }
        
        render(g, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()));
    }
    
    public void render (Graphics g, int x, int y){
        g.drawImage(texture, x, y,ITEMHEIGHT,ITEMHEIGHT, null);
    }

     public Item crateNew(int count){
        Item i= new Item(texture, name, id);
        i.setPickedUP(true);
        i.setCount(count);
        return i;
    }
    
    public Item crateNew(int x, int y){
        Item i= new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    
    //Getter Setter Metodları
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUP() {
        return pickedUP;
    }

    public void setPickedUP(boolean pickedUP) {
        this.pickedUP = pickedUP;
    }

    public int getId() {
        return id;
    }
}
