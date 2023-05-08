/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonStuff;

import Client.StegaViewer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author dca
 */
public class Serial_BufImage implements Serializable{

    private static final long serialVersionUID = 1L;
    //public BufferedImage buf=null;
    public byte[] imgB;
    public String username;//Nome di chi ha inviato l'immagine
    
    public Serial_BufImage(byte[] imgB,String username){
      this.imgB=imgB;
      this.username=username;
    }
  
}