/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import commonStuff.Steganography;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

    public class ImageButton extends JButton{
      protected String username;
      protected ImageIcon icon;
      protected byte[] imgB;
      protected JPopupMenu jPopupMenu_imgOption;
      protected JMenuItem jMenuItem_viewImage;
      protected JMenuItem jMenuItem_findMes; 
      protected JFrame parent;
      
      /*        jMenuItem_decripta = new javax.swing.JMenuItem();
      
        jMenuItem_decripta.setText("Decripta");
        jMenuItem_decripta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_decriptaActionPerformed(evt);
            }
        });      */

      public ImageButton(String username,ImageIcon icon,byte[] imgB,JFrame f){
       super();
       this.icon = icon;
       this.username=username;   
       this.imgB=imgB;
       parent = f;
       jPopupMenu_imgOption = new JPopupMenu("Opzioni immagine");
       //jMenuItem_viewImage = new JMenuItem("Visualizza immagine per intero");
       jMenuItem_findMes = new JMenuItem("Trova messaggio nascosto");
       setIcon(icon);
       //jPopupMenu_imgOption.add(jMenuItem_viewImage);
       jPopupMenu_imgOption.add(jMenuItem_findMes);
       
       
       //Ascoltatore click su immagine per aprire il context menu (visualizza immagine per intero o trova mes)
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ImageButton_messaggiMouseReleased(evt);
            }
        });
       /*
       //Ascoltatore click su voce menù: "Visualizza immagine per intero"
       jMenuItem_viewImage.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_viewImage(evt);
            }
       });*/
       //Ascoltatore click su voce menù: "Trova messaggio nascosto"        
       jMenuItem_findMes.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_findMes(evt);
            }
       });             
       
       }
        //***METODI ASCOLTATORI
        private void jMenuItem_viewImage(java.awt.event.ActionEvent evt) {                                         
      
            System.out.println("Selzionata immagine di: "+username+" per visualizzarla per intero.");

        }        
        
        private void jMenuItem_findMes(java.awt.event.ActionEvent evt){
            Steganography temp = new Steganography();
            String mesNascosto = temp.decode(temp.createImageFromBytes(imgB));
            System.out.println("Selzionata immagine di: "+username+" per trovare un messaggio nascosto.");
            System.out.println("Server - Messaggio nascosto (da buff): "+mesNascosto);
            
            if(!mesNascosto.isEmpty()){
                JOptionPane.showMessageDialog(parent, "Messaggio nascosto: "+mesNascosto,"Messaggio trovato",JOptionPane.INFORMATION_MESSAGE);                            
            }
            else{
                JOptionPane.showMessageDialog(parent, "Non c'è nessun messaggio nascosto in questa immagine!","Errore",JOptionPane.ERROR_MESSAGE);              
            }
        }
        
        private void ImageButton_messaggiMouseReleased(java.awt.event.MouseEvent evt) {                                         


            if(evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
              System.out.println("Selzionata immagine inviata da: "+username);
              System.out.println("Visualizza menuù contestuale");
              jPopupMenu_imgOption.show(evt.getComponent(), evt.getX(), evt.getY());
            }
            
        }
        //************
        
    }
  