
package Logic;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
   
    public static final int DEFAULT_HEALT = 20;
    protected Handler handler;
    protected float x,y;
    protected int width,height;
    protected boolean active = true;
    protected Rectangle bounds;
    protected int health;
    
    public Entity(Handler handler,float x,float y,int width,int height){ 
        this.handler = handler;
        this.x = x ;
        this.y = y ;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALT;
        
        bounds =  new Rectangle(0, 0, width , height);
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract void die();
    
    public void hurt(int dmg){
        health -= dmg;
        if (health <= 0) {
            active = false;
            die();
        }
    }
    
    public boolean checkEntityColisions(float xOffset, float yOffset){
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this)){
                continue;      
            }    
            if (e.getCollionsBounds(0f, 0f).intersects(getCollionsBounds(xOffset, yOffset))) {
                return true;
            }
        }           
        return false;

    }
    
    public Rectangle getCollionsBounds(float xOffset,float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset) , (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
  
    
}
