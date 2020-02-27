
package Logic;

import GUI.States;
import GUI.MenuState;
import GUI.GameState;
import Action.KeyManager;
import Action.MouseManager;
import GUI.Assets;
import GUI.Display;
import GUI.ImageLoader;
import GUI.SpriteSheet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements Runnable{
 
    private Display display;   
    private int widht, height;
    public String title;
    
    private boolean running = false;
    private  Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //Bölüm İsimleri
    public States gameState;
    public States menuState;
    
    //Girişleri Aldığımız Bölüm
    private KeyManager keyManager;
    private MouseManager mouseManager;
   
    //Kamera Kontrolleri
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    
    public Game(String title,int widht,int height){
        this.widht = widht;
        this.height = height;  
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void  init(){
         display = new Display(title, widht, height);
         display.getFrame().addKeyListener(keyManager);
         display.getFrame().addMouseListener(mouseManager);
         display.getFrame().addMouseMotionListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
         Assets.init();
         
         handler = new Handler(this);
         gameCamera = new GameCamera(handler,0, 0);
         
         
         gameState = new GameState(handler);
         menuState = new MenuState(handler);    
         States.setStates(menuState);
         
    }
    

    private  void tick(){
        keyManager.tick();
        
        if (States.getStates() != null) {
           States.getStates().tick();
        }
    }
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
       
        g.clearRect(0, 0, widht, height); //Ekran Temizleme
   
        //Burada Çizim Başlıyor
        
        if (States.getStates() != null) {
           States.getStates().render(g);
        }
        
        //Burda Çizim Btityor
        bs.show();
        g.dispose();
    }
    
    public void run(){
        
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta +=(now-lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                tick();
                render(); 
                ticks++;
                delta--;
            }
            
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " +ticks);
                ticks = 0;
                timer = 0;
            } 
        }
        
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    } 
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth() {
        return widht;
    }

    public int getHeight() {
        return height;
    }    
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();  
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
