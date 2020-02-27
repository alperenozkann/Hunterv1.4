
package Logic;

import GUI.FireMonster;
import GUI.ItemManager;
import GUI.StoneMonster;
import GUI.Tile;
import GUI.Tree;
import GUI.WaterMonster;
import Logic.Utils;
import java.awt.Graphics;

public class World {
    
    private Handler handler;
    private int widht , height;
    private int spawnX, spawnY;
    private int[][] tiles;
    
    
    //Varlıklar
    private EntityManager entityManager;
    
    //Item'ler(Düşen Objeler)
    private ItemManager itemManager;
    
    public World(Handler handler,String path){
        this.handler = handler;   
        entityManager = new EntityManager(handler,new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
            
        entityManager.addEntity(new Tree(handler,600,100));
        entityManager.addEntity(new Tree(handler,680,450));
        entityManager.addEntity(new Tree(handler,90,170));
        entityManager.addEntity(new Tree(handler,650,720));
        entityManager.addEntity(new StoneMonster(handler, 170, 470));
        entityManager.addEntity(new StoneMonster(handler, 280, 870));
        entityManager.addEntity(new FireMonster(handler, 650, 660));
        entityManager.addEntity(new FireMonster(handler, 670, 320));
        entityManager.addEntity(new WaterMonster(handler, 800, 800));
        entityManager.addEntity(new WaterMonster(handler, 360, 260));

   
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    
    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }
    
    public void render(Graphics g){
        
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDHT);
        int xEnd = (int) Math.min(widht, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDHT + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT) ;
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g,(int)( x * Tile.TILEWIDHT - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Item
        itemManager.render(g);
        //Varlıklar
        entityManager.render(g);
    }
    
    public Tile getTile(int x,int y){
      
        if(x < 0 || y < 0 || x >= widht || y >= height){
            return Tile.grassTile;
        }
        
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.dirtTile;
        }
        return t;
    }
    
    
    // String Tipinde Aldığımız Veri İle Haritayı Oluşturduğumuz Blok
    private void loadWorld(String path){
        
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        widht = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles =  new int[widht][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < widht; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * widht) + 4]);
            }
            
        }
    }

    public int getWidht() {
        return widht;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    } 
    
}
