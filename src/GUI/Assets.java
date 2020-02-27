
package GUI;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
    
    private static final int width = 32, height = 32;
    
    public static Font font28;
    
    public static BufferedImage dirt,grass,stone,tree,monster_stone,monster_fire,monster_water,back_groundImage;
    public static BufferedImage wood,rock,water,fire;
    public static BufferedImage[] player_down, player_up, player_left, player_right,player_stand;
    public static BufferedImage[] btn_start;
    public static BufferedImage inventoryScreen;
    
    public static void init(){
        
        font28 = FontLoader.loadFont("res/font/Sukro-Regular.ttf", 28);//Font Düzenleme
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sheet.png"));//Bölümleyerek Resim Alma
    
        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");//Çanta
        
        back_groundImage = ImageLoader.loadImage("/textures/MenuScreen.png");//Arka Plan

        //Item Görselleri
        wood = sheet.crop(0, height * 3, width, height);
        rock = sheet.crop(width, height, width, height);
        water = sheet.crop(width, height * 3, width, height);
        fire = sheet.crop(width * 2, height, width, height);
 
        //Buton Görsel Dizilieri
        btn_start = new BufferedImage[2];
        btn_start[1] = sheet.crop(width * 5, height * 3, width * 2, height);
        btn_start[0] = sheet.crop(width * 5, height * 4, width * 2, height);
   
        //Karakter Görsel Dizlieri
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_stand = new BufferedImage[1];
   
        //Karakterler
        player_down[0] = sheet.crop(width * 3, 0, width, height);
        player_down[1] = sheet.crop(width * 4, 0, width, height);

        player_up[0] = sheet.crop(width * 5, 0, width, height);
        player_up[1] = sheet.crop(width * 6, 0, width, height);

        player_left[0] = sheet.crop(width * 5, height, width, height);
        player_left[1] = sheet.crop(width * 6, height, width, height);

        player_right[0] = sheet.crop(width * 3, height, width, height);
        player_right[1] = sheet.crop(width * 4, height, width, height);

        player_stand[0] = sheet.crop(width * 3, height * 2, width, height);

        //Objeler
        dirt = sheet.crop(0, 0, width, height);
        grass = sheet.crop(width , 0, width, height);
        stone = sheet.crop(width * 2, 0, width, height);
        tree = sheet.crop(0, height, width, height);
        
        //Yaratıklar
        monster_fire = sheet.crop(width , height * 2, width, height);
        monster_stone = sheet.crop(width * 2, height * 2, width, height);
        monster_water = sheet.crop(0, height * 2, width, height);
        
    }
}
