
package Logic;

import GUI.Assets;
import GUI.Item;
import GUI.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {
  
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    
    private int invX = 64, invY = 48,
            invWidht = 512, invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2 + 5 ,
            invListSpaceing = 30;
    
    private int invImageX = 452, invImageY = 82,
            invImageWidht = 64, invImageHeight = 64;
            
    private int invCountX = 484, invCountY =172;
    
    private int selectedItem = 0;
    
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
       
    }
    
    public void tick(){
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active) {
            return;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem--;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            selectedItem++;
        }
        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;
        }else if (selectedItem >= inventoryItems.size()) {
            selectedItem = 0;
        }
    }
    
    public void render(Graphics g){
        if (!active) {
            return;
        }
        
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidht, invHeight, null);
        
        int len = inventoryItems.size();
        if (len == 0) {
            return;
        }
        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {
                continue;
            }
            if (i == 0) {
                Text.drawString(g,"> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpaceing, true, Color.YELLOW, Assets.font28);
            }else{
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpaceing, true, Color.WHITE, Assets.font28);
            }
            
        }
        
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidht, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
    }
    
    //Inventory Metodları
    
    public void addItem(Item item){
        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
    
    //Getter Setter   
    public Handler getHandler(){
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
    
    
}
