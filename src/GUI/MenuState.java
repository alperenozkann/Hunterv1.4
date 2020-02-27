
package GUI;

import Action.ClickListener;
import Action.UIManager;
import GUI.Assets;
import GUI.UIImageButton;
import Logic.Handler;
import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends States{

    private UIManager uiManager;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        
        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                States.setStates(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(Assets.back_groundImage, 0, 0, null);
        uiManager.render(g);
    }
    
}
