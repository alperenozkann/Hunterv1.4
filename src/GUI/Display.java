
package GUI;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    
    private String title;
    private int widht , height;
    
    public Display(String title , int widht,int height){
        this.title = title;
        this.widht = widht;
        this.height = height;
        
        createDisplay();
    }
    private void createDisplay(){
        
        frame = new JFrame(title); 
        frame.setSize(widht,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(widht,height));
        canvas.setMaximumSize(new Dimension(widht,height));
        canvas.setMinimumSize(new Dimension(widht,height));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
        
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
