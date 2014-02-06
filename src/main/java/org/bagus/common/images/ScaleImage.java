/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.images;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * BUAT GAMBAR YANG BISA DIMASUKKAN KEDALAM PANEL 
 * NAMUN UKURAN GAMBAR TERSEBUT AKAN MENYESUAIKAN DENGAN UKURAN PANEL
 * 
 */
public class ScaleImage extends JPanel{
    private Image image;  
    /**
     * ScaleImage scaleImage = new ScaleImage(path);
     * path = url path bertipe String
     */
    public ScaleImage(String urlPath) {  
        MediaTracker tracker=new MediaTracker(this);  
        image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(urlPath));  
        tracker.addImage(this.image, 0);  
        try {  
            tracker.waitForID(0);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);   
        if (image != null)  
          g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);  
    }  
    
}
