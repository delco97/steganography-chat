/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 *Classe che definisce lo stile nei messaggi nel jPanel
 * @author Andrea Del Corto-Simone Giacomelli 5IA07
 */
public class StylePanelMessages {
    protected JTextPane panel;
    private String fontH;
    private String fontMsg;
    private int dimH;
    private int dimMsg;
     /**
     * Costruttore della classe StylePanelMessages
     * @param p oggetto di tipo JTextPane
     */
    public StylePanelMessages(JTextPane p){
      panel = p;
      fontH = "Impact";
      fontMsg = "Arial";
      dimH = 13;
      dimMsg = 12;   
    }

    public String getFontH() {
        return fontH;
    }

    public String getFontMsg() {
        return fontMsg;
    }

    public int getDimH() {
        return dimH;
    }

    public int getDimMsg() {
        return dimMsg;
    }

    public void setFontH(String fontH) {
        this.fontH = fontH;
    }

    public void setFontMsg(String fontMsg) {
        this.fontMsg = fontMsg;
    }

    public void setDimH(int dimH) {
        this.dimH = dimH;
    }

    public void setDimMsg(int dimMsg) {
        this.dimMsg = dimMsg;
    }

        /**
     * Metodo che stampa messaggi sul jPanel
     * @param header Stringa che dichiara che la scansione è stata interrotta
     * @param msg Stringa che contiene il messaggio da dare
     * @param headerColor oggetto di tipo Color che imposterà il colore al header
     * @param contentColor oggetto di tipo Color che imposterà il colore al msg
     */
    synchronized public void appendMessage(String header,String msg,Color headerColor, Color contentColor){
        if(panel!=null){
        panel.setEditable(true);
        getMsgHeader(header, headerColor);
        getMsgContent(msg, contentColor);
        panel.setEditable(false);
        }
    }
    
    synchronized public void appendMessage(String header,String msg,Color headerColor, Color contentColor,ImageButton btn_img){
        if(panel!=null){
            try {
                panel.setEditable(true);
                getMsgHeader(header, headerColor);
                getMsgContent(msg, contentColor);
                //Inserisco immagine
                //panel.add(img.getLb());
                StyledDocument doc = panel.getStyledDocument();
                SimpleAttributeSet attr = new SimpleAttributeSet();
                //doc.insertString(doc.getLength(), "EVENTUALE TESTO prima di img", attr );
                panel.setCaretPosition(panel.getDocument().getLength());
                panel.insertComponent(btn_img);
                doc.insertString(doc.getLength(), "\n", attr );
                
                /*StyledDocument doc = (StyledDocument) panel.getDocument();
                Style style = doc.addStyle("StyleName", null);
                StyleConstants.setIcon(style,img.);
                
                try {
                doc.insertString(doc.getLength(), "ignored text", style);
                appendMessage("\n","",Color.black,Color.black);
                } catch (BadLocationException ex) {
                Logger.getLogger(JFrame_Gui.class.getName()).log(Level.SEVERE, null, ex);
                }        */
                panel.setEditable(false);
            } catch (BadLocationException ex) {
                Logger.getLogger(StylePanelMessages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    /**
     * Metodo che imposterà il messaggio di testa
     * @param header Stringa che conterrà il messaggio
     * @param color oggetto Color che darà il colore al messaggio
     */
    private void getMsgHeader(String header, Color color){
        int len = panel.getDocument().getLength();
        panel.setCaretPosition(len);
        panel.setCharacterAttributes(MessageStyle.styleMessageContent(color, fontH, dimH), false);
        panel.replaceSelection(header);
    }
    /**
     * Metodo che imposterà il messaggio 
     * @param msgStringa che conterrà il messaggio
     * @param color oggetto Color che darà il colore al messaggio
     */
    private void getMsgContent(String msg, Color color){
        int len = panel.getDocument().getLength();
        panel.setCaretPosition(len);
        panel.setCharacterAttributes(MessageStyle.styleMessageContent(color, fontMsg,dimMsg), false);
        panel.replaceSelection(msg +"\n");
    }
    public void clear(){
      panel.setText("");
    }
}
