
package Logic;

import GUI.Tile;

public abstract class Creature extends Entity{
    
   
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;
    
   
    protected float speed;
    protected float xMove,yMove;
    
    public Creature(Handler handler,float x, float y,int widht,int height) {
        super(handler,x, y,widht,height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        if (!checkEntityColisions(xMove, 0f)) {     
            moveX();
        }
        if (!checkEntityColisions(0f, yMove)) {
            moveY();        
        }
    }
    
    public void moveX(){
        if (xMove > 0) { //Sağa Hareket           
            int tx = (int) (x + xMove + bounds.x + bounds.width ) / Tile.TILEWIDHT;
           
            if (!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height)/ Tile.TILEHEIGHT)){
                x += xMove;               
            }
            
        }else if (xMove < 0) { //Sola Hareket
            int tx = (int) (x + xMove + bounds.x - 12) / Tile.TILEWIDHT;
           
            if (!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;               
            }
        }
    }
    
    public void moveY(){
        if (yMove < 0) { //Yukarı Hareket
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDHT, ty) &&
                 !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDHT, ty)   ){
               y += yMove;            
            }else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
            
        }else if (yMove > 0) { //Aşağı Hareket
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDHT, ty) &&
                 !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDHT, ty)   ){
               y += yMove;            
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1 ;
            }
        }
    }
    
    protected boolean collisionWithTile(int x,int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    // GETTER SETTER METODLARI
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
    
 
    
    
}
