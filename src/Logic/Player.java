
package Logic;

import GUI.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends Creature{

    //Animasyonları İçeren Bölüm
    private Animation animDown,animUp,animLeft,animRight,animStand;
   
    //Atak Zamanlayıcısı
    private long lastAtackTimer, atackCooldown = 1, atackTimer = atackCooldown;

    //Çanta(Inventory) Bölümü
    private Inventory inventory;
    
    public Player(Handler handler,float x, float y) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
        
        //Animasyonlar
        animDown = new Animation(350, Assets.player_down);
        animUp = new Animation(350, Assets.player_up);
        animLeft = new Animation(350, Assets.player_left);
        animRight = new Animation(350, Assets.player_right);
        animStand = new Animation(5000, Assets.player_stand);
        
        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animasyon
        
        animStand.tick();
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        
        //Hareketler
        getInput();
        move();
        handler.getGameCamera().centerOnEntitiy(this);
        
        //Ataklar
        checkAttacks();
        
        //Inventory
        inventory.tick();
    }

    private void checkAttacks(){
        atackTimer += System.currentTimeMillis() - lastAtackTimer;
        lastAtackTimer = System.currentTimeMillis();
        if (atackTimer < atackCooldown) {
            return;
        }
        if (inventory.isActive()) {
            return;
        }
        
        Rectangle cb = getCollionsBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;  
        
        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }
        else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }
        else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }
        else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            return;
        }
        
        atackTimer = 0;
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
                
            if (e.getCollionsBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
                 
    }
    
    @Override
    public void die(){
        System.out.println("You Lose !");
    }
    
    private void getInput(){
        if (inventory.isActive()) {
            return;
        }
        
        xMove = 0;
        yMove = 0;
        
        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnmation(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
           
        /* Kontrol Bölümü
                g.fillRect((int) (x+ bounds.x - handler.getGameCamera().getxOffset()),
                (int)(y+ bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width,bounds.height);*/
    }
    
    public void postRender(Graphics g){
        inventory.render(g); 
    }
    
    private BufferedImage getCurrentAnmation(){
        if (xMove < 0) {
            return  animLeft.getCurrentFrame();
        }else if (xMove > 0) {
            return animRight.getCurrentFrame();
        }else if (yMove < 0) {
            return animUp.getCurrentFrame();
        }else if (yMove > 0){
            return animDown.getCurrentFrame();
        }else {
            return animStand.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    
}
