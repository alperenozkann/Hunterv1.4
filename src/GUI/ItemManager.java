
package GUI;

import Logic.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }
    
    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if (i.isPickedUP()) {
                it.remove();
            }
        }
    }
    
    public void render(Graphics g){
        for (Item i : items) {
            i.render(g);
        }
    }
    
    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    public Handler getHandler() {
        return handler;
    }
    
}
