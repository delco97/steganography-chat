package commonStuff;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/*
 *@author  William_Wilson
 *@version 1.6
 *Created: May 8, 2007
 */

/*
 *import list
 */
import Client.StegaViewer;
import java.io.File;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/*
 *Class Steganography
 */
public class Steganography
{
	
	/*
	 *Steganography Empty Constructor
	 */
	public Steganography()
	{
	}
	
	/*
        Inserisce il messaggio message all'interno dell'imagine che si trova in f_img.
        Restituisce un BufferedImage contenente l'immagine con il messaggio nascosto
        ritorna null se l'operazione non è andata a buon fine.
	 */
	public BufferedImage encode(File f_img, String message){
            try {
                BufferedImage 	image_orig	= ImageIO.read(f_img);

                BufferedImage image = user_space(image_orig);//Lavoro su una copia
                image = add_text(image,message);
                return image;
            } catch (IOException ex) {
                System.out.println("ERRORE: impossibile leggere immagine da file");
                Logger.getLogger(Steganography.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
	}
	
        public BufferedImage createImageFromBytes(byte[] imageData) {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            try {
                return ImageIO.read(bais);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }        
        
	/*
        Restituisce, se lo trova, il messaggio nascosto contenuto da img.
	 */
	public String decode(BufferedImage img){
		byte[] decode;
		try
		{
			BufferedImage image  = user_space(img);//Lavoro su una copia
			decode = decode_text(get_byte_data(image));
			return(new String(decode));
		}
		catch(Exception e)
		{
			/*JOptionPane.showMessageDialog(null, 
				"Non c'è nessun messaggio nascosto in questa immagine!","Error",
				JOptionPane.ERROR_MESSAGE);*/
			return "";
		}
	}


	/*
        
	 */
	private BufferedImage add_text(BufferedImage image, String text)
	{
		//converto tutto in array di byte
		byte img[]  = get_byte_data(image);
		byte msg[] = text.getBytes();
		byte len[]   = bit_conversion(msg.length);//System.out.println("get_byte_data(image): "+img.length);
		try
                {
			encode_text(img, len,  0); //0 
			encode_text(img, msg, 32); //4 bytes di spazio
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, 
"Target File cannot hold message!", "Error",JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
	
	/*
	 *Creates a user space version of a Buffered Image, for editing and saving bytes
	 *@param image The image to put into user space, removes compression interferences
	 *@return The user space version of the supplied image
	 */
	private BufferedImage user_space(BufferedImage image)
	{
		//create new_img with the attributes of image
		BufferedImage new_img  = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D	graphics = new_img.createGraphics();
		graphics.drawRenderedImage(image, null);
		graphics.dispose(); //release all allocated memory for this image
		return new_img;
	}
	
	/*
	 *Gets the byte array of an image
	 *@param image The image to get byte data from
	 *@return Returns the byte array of the image supplied
	 *@see Raster
	 *@see WritableRaster
	 *@see DataBufferByte
	 */
	public byte[] get_byte_data(BufferedImage image)
	{
		WritableRaster raster   = image.getRaster();
		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
		return buffer.getData();
	}
        
	
	/*
	 *Gernerates proper byte format of an integer
	 *@param i The integer to convert
	 *@return Returns a byte[4] array converting the supplied integer into bytes
	 */
	private byte[] bit_conversion(int i)
	{
		//originally integers (ints) cast into bytes
		//byte byte7 = (byte)((i & 0xFF00000000000000L) >>> 56);
		//byte byte6 = (byte)((i & 0x00FF000000000000L) >>> 48);
		//byte byte5 = (byte)((i & 0x0000FF0000000000L) >>> 40);
		//byte byte4 = (byte)((i & 0x000000FF00000000L) >>> 32);
		
		//only using 4 bytes
		byte byte3 = (byte)((i & 0xFF000000) >>> 24); //0
		byte byte2 = (byte)((i & 0x00FF0000) >>> 16); //0
		byte byte1 = (byte)((i & 0x0000FF00) >>> 8 ); //0
		byte byte0 = (byte)((i & 0x000000FF)	   );
		//{0,0,0,byte0} is equivalent, since all shifts >=8 will be 0
		return(new byte[]{byte3,byte2,byte1,byte0});
	}
	
	/*
	 *Encode an array of bytes into another array of bytes at a supplied offset
	 *@param image	 Array of data representing an image
	 *@param addition Array of data to add to the supplied image data array
	 *@param offset	  The offset into the image array to add the addition data
	 *@return Returns data Array of merged image and addition data
	 */
	private byte[] encode_text(byte[] image, byte[] addition, int offset)
	{
		//check that the data + offset will fit in the image
		if(addition.length + offset > image.length)
		{
			throw new IllegalArgumentException("File non abbastanza lungo!");
		}
		//loop through each addition byte
		for(int i=0; i<addition.length; ++i)
		{
			//loop through the 8 bits of each byte
			int add = addition[i];
			for(int bit=7; bit>=0; --bit, ++offset) //ensure the new offset value carries on through both loops
			{
				//assign an integer to b, shifted by bit spaces AND 1
				//a single bit of the current byte
				int b = (add >>> bit) & 1;
				//assign the bit by taking: [(previous byte value) AND 0xfe] OR bit to add
				//changes the last bit of the byte in the image to be the bit of addition
				image[offset] = (byte)((image[offset] & 0xFE) | b );
			}
		}
		return image;
	}
	
	/*
	 *Retrieves hidden text from an image
	 *@param image Array of data, representing an image
	 *@return Array of data which contains the hidden text
	 */
	private byte[] decode_text(byte[] image)
	{
		int length = 0;
		int offset  = 32;
		//loop through 32 bytes of data to determine text length
		for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
		{
			length = (length << 1) | (image[i] & 1);
		}
		
		byte[] result = new byte[length];
		
		//loop through each byte of text
		for(int b=0; b<result.length; ++b )
		{
			//loop through each bit within a byte of text
			for(int i=0; i<8; ++i, ++offset)
			{
				//assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
				result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
			}
		}
		return result;
	}
        
    public byte[] convertToByteArray(BufferedImage imgBuf){
        byte[] imageInByte = null;
        try {
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           //Converto BufferedImage in array di byte
           ImageIO.write( imgBuf, "png", baos );
           baos.flush();
           imageInByte = baos.toByteArray();
           baos.close();  
           return imageInByte;
       } catch (IOException ex) {
           Logger.getLogger(StegaViewer.class.getName()).log(Level.SEVERE, null, ex);
       }
       return imageInByte;
    }
    
    static  public BufferedImage convertToBufferedImage(byte[] imgByte){
       BufferedImage bImageFromConvert=null;
       try {
           //Ricostruisco BufferedImage partendo dall'arrey di byte appena ottenuto
           InputStream in = new ByteArrayInputStream(imgByte);      
           bImageFromConvert = ImageIO.read(in);
       } catch (IOException ex) {
           Logger.getLogger(StegaViewer.class.getName()).log(Level.SEVERE, null, ex);
       }
       return bImageFromConvert;
    }        
}
