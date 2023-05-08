/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import commonStuff.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

/**
 *
 * @author dca
 */
public class Chat_clientGUI extends javax.swing.JFrame{
    protected String username;
    protected String stanzaScelta; 
    protected String ipServer;
    protected int portaServer;
    protected Socket conSocket; //per la comunicazione con il server
    //private ArrayList<String> nomeStanze;
    //private ArrayList<String> utentiConnessi;
    //Per canale di RX e TX
    protected T_TCPclient comunication;   //per ricevere i dati dal server
    protected StylePanelMessages messageDisplay;
    //private ArrayList<String> stanzeCreate;
    /**
     * Creates new form Chat_clientGUI
     */
    public Chat_clientGUI(String name) {
        initComponents();
        setTitle(name);
        messageDisplay = new StylePanelMessages(jTextPane_messaggi);
        //stanzeCreate = new ArrayList<String>();
        jPanel_login.setVisible(true);
        jPanel_chat.setVisible(false);
        jPanel_sceltaStanza.setVisible(false);
jButton_selectImage.setOpaque(false);
jButton_selectImage.setContentAreaFilled(false);
jButton_selectImage.setBorderPainted(false);
        //Cattura doppio clic
        jList_elencoStanze.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
             if (me.getClickCount() == 2) {
               //System.out.println("Stanza selezionata: "+jList_elencoStanze.getSelectedValue());
               logStanza();
             }
          }});
         //Cattura invio
         jList_elencoStanze.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
               //System.out.println("Stanza selezionata: "+jList_elencoStanze.getSelectedValue());      
               logStanza();
            }
         }});   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_chat = new javax.swing.JPanel();
        jTextField_invioMes = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_utentiConnessi = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_messaggi = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton_selectImage = new javax.swing.JButton();
        jPanel_login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_ipServer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_portaServer = new javax.swing.JTextField();
        jButton_okSetServer = new javax.swing.JButton();
        jPanel_sceltaStanza = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_elencoStanze = new javax.swing.JList();
        jButton_aggiornaElencoStanze = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jButton_creaStanza = new javax.swing.JButton();
        jButton_cancStanza = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel_chat.setPreferredSize(new java.awt.Dimension(400, 300));

        jTextField_invioMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_invioMesActionPerformed(evt);
            }
        });

        jList_utentiConnessi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jList_utentiConnessi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_utentiConnessiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList_utentiConnessi);

        jTextPane_messaggi.setEditable(false);
        jScrollPane1.setViewportView(jTextPane_messaggi);

        jLabel6.setText("Utenti:");

        jLabel7.setText("Chat:");

        jButton_selectImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image.png"))); // NOI18N
        jButton_selectImage.setToolTipText("Scegli un immagine da inviare");
        jButton_selectImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_selectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_chatLayout = new javax.swing.GroupLayout(jPanel_chat);
        jPanel_chat.setLayout(jPanel_chatLayout);
        jPanel_chatLayout.setHorizontalGroup(
            jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_chatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel_chatLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 331, Short.MAX_VALUE))
                    .addComponent(jTextField_invioMes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton_selectImage, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
        );
        jPanel_chatLayout.setVerticalGroup(
            jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_chatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_chatLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_chatLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel_chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_invioMes)
                            .addComponent(jButton_selectImage, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setText("IP:");

        jTextField_ipServer.setText("localhost");
        jTextField_ipServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ipServerActionPerformed(evt);
            }
        });

        jLabel3.setText("porta:");

        jTextField_portaServer.setText("6666");
        jTextField_portaServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_portaServerActionPerformed(evt);
            }
        });

        jButton_okSetServer.setText("Conferma");
        jButton_okSetServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okSetServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_loginLayout = new javax.swing.GroupLayout(jPanel_login);
        jPanel_login.setLayout(jPanel_loginLayout);
        jPanel_loginLayout.setHorizontalGroup(
            jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_loginLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_loginLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_ipServer)
                            .addComponent(jTextField_portaServer, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1))
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_okSetServer)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel_loginLayout.setVerticalGroup(
            jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loginLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ipServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_portaServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_okSetServer)
                .addGap(152, 152, 152))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setText("Scelta stanza");

        jList_elencoStanze.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_elencoStanze.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(jList_elencoStanze);

        jButton_aggiornaElencoStanze.setText("Aggiorna");
        jButton_aggiornaElencoStanze.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_aggiornaElencoStanzeMouseClicked(evt);
            }
        });

        jLabel5.setText("Username: ");

        jButton_creaStanza.setText("Crea stanza");
        jButton_creaStanza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_creaStanzaMouseClicked(evt);
            }
        });

        jButton_cancStanza.setText("Cancella Stanza");
        jButton_cancStanza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancStanzaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_sceltaStanzaLayout = new javax.swing.GroupLayout(jPanel_sceltaStanza);
        jPanel_sceltaStanza.setLayout(jPanel_sceltaStanzaLayout);
        jPanel_sceltaStanzaLayout.setHorizontalGroup(
            jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_sceltaStanzaLayout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addGroup(jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_sceltaStanzaLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_creaStanza)
                                .addComponent(jButton_aggiornaElencoStanze)
                                .addComponent(jButton_cancStanza)))
                        .addGroup(jPanel_sceltaStanzaLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_sceltaStanzaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(151, 151, 151)))
                .addGap(43, 43, 43))
        );
        jPanel_sceltaStanzaLayout.setVerticalGroup(
            jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_sceltaStanzaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addGroup(jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_sceltaStanzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_sceltaStanzaLayout.createSequentialGroup()
                        .addComponent(jButton_aggiornaElencoStanze)
                        .addGap(3, 3, 3)
                        .addComponent(jButton_creaStanza)
                        .addGap(1, 1, 1)
                        .addComponent(jButton_cancStanza)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_chat, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_sceltaStanza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_chat, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_sceltaStanza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_ipServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ipServerActionPerformed
        
    }//GEN-LAST:event_jTextField_ipServerActionPerformed

    private void jTextField_portaServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_portaServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_portaServerActionPerformed
    
    private void jButton_okSetServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okSetServerActionPerformed
        // TODO add your handling code here:
        try{//Prendo i dati inseriti
          ipServer = jTextField_ipServer.getText();
          portaServer = Integer.parseInt(jTextField_portaServer.getText());
          try{//Tentativo di connessione
            conSocket = new Socket(ipServer,portaServer);
            System.out.println("Connesso!");
            comunication = new T_TCPclient(conSocket, this);
            comunication.start();
            comunication.sendData("GET_STANZE-");
            //comunication.sendData("GET_STANZE-");
            /*
            //Ottengo canale di TX
            tx = new ObjectOutputStream(conSocket.getOutputStream());//true ad ogni print viene inviato il buffer dei dati            
            //Ottengo canale di RX (Thread)
            rx = new T_TCPclient(conSocket,this);
            rx.start();//Lancio Thread per Rx  dati            
            
            comunication.sendData("GET_STANZE-");*/
            //Cambio schermata
            jPanel_login.setVisible(false);
            jPanel_sceltaStanza.setVisible(true);
            jPanel_chat.setVisible(false);
          }
          catch(Exception e){
            JOptionPane.showMessageDialog(this, "Connessione al server fallita","Attenzione", JOptionPane.ERROR_MESSAGE);        
          }
        }catch(Exception e){
          JOptionPane.showMessageDialog(this, "Valori inseriti non validi","Attenzione", JOptionPane.ERROR_MESSAGE);        
        }
    }//GEN-LAST:event_jButton_okSetServerActionPerformed

    private void jButton_aggiornaElencoStanzeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_aggiornaElencoStanzeMouseClicked
            comunication.sendData("GET_STANZE-");
    }//GEN-LAST:event_jButton_aggiornaElencoStanzeMouseClicked

    private void jButton_creaStanzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_creaStanzaMouseClicked
      //Creazione pannello
      JPanel panel = new JPanel(new BorderLayout(5, 5));
      JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
      label.add(new JLabel("Nome stanza: ", SwingConstants.RIGHT));
      label.add(new JLabel("Password: ", SwingConstants.RIGHT));
      panel.add(label, BorderLayout.WEST);
      JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
      JTextField jt_nomeStanza = new JTextField();
      controls.add(jt_nomeStanza);
      JPasswordField jt_password = new JPasswordField();
      controls.add(jt_password);
      panel.add(controls, BorderLayout.CENTER);
      while(true){
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, panel, "Crea una stanza",  JOptionPane.OK_CANCEL_OPTION);      
        if(showConfirmDialog!=0)break;
        String pwd=new String(jt_password.getPassword());
        String nomeStanza=jt_nomeStanza.getText();
        if(!pwd.isEmpty() && !nomeStanza.trim().isEmpty() && pwd.length()>0 && pwd.length()>0){
                comunication.sendData("NUOVA_STANZA-"+nomeStanza+","+pwd);

          break;
        }
        else{
          JOptionPane.showMessageDialog(this, "Valori inseriti non validi.","Attenzione", JOptionPane.ERROR_MESSAGE);
        }          
      }
    }//GEN-LAST:event_jButton_creaStanzaMouseClicked

    private void jList_utentiConnessiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_utentiConnessiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList_utentiConnessiMouseClicked

    private void jTextField_invioMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_invioMesActionPerformed
        String mes = jTextField_invioMes.getText();
        try {
            sendChatMessage(mes);
        } catch (IOException ex) {
            Logger.getLogger(Chat_clientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField_invioMesActionPerformed

    private void jButton_cancStanzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancStanzaActionPerformed
      try{
        String stanza = jList_elencoStanze.getSelectedValue().toString();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        while(true){
          int option = JOptionPane.showOptionDialog(this, panel, "Cancellazione stanza "+stanza,
                           JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                           null, options, options[0]);
          if(option == 0){ // pressing OK button
            String pwd = new String(pass.getPassword());
            if(!pwd.isEmpty()){
              comunication.sendData("CANC_STANZA-"+stanza+","+pwd);
              break;
            }
            else
              JOptionPane.showMessageDialog(this, "Inserisci una password.","Informazione", JOptionPane.INFORMATION_MESSAGE);   
          }
          else{
            break;    
          }  
        }
      }catch(Exception e){
        JOptionPane.showMessageDialog(this, "Seleziona una stanza.","Informazione", JOptionPane.INFORMATION_MESSAGE); 
      }
    }//GEN-LAST:event_jButton_cancStanzaActionPerformed

    private void jButton_selectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectImageActionPerformed
      System.out.println("Inizione fase di selezione. . .");                
      ImagePreviewJFileChooser fChooser = new ImagePreviewJFileChooser();
      fChooser.showOpenDialog();
      File selectedImg = fChooser.getSelectedFile();
      if(selectedImg!=null){
        System.out.println("Immagine selezionata: "+selectedImg.getAbsolutePath());
          try {
              new StegaViewer(this,selectedImg).setVisible(true);
              //comunication.sendData(new ImageCS(selectedImg,username));
          } catch (IOException ex) {
              Logger.getLogger(Chat_clientGUI.class.getName()).log(Level.SEVERE, null, ex);
              JOptionPane.showMessageDialog(this,"Impossibile visualizzare l'immagine scelta","Errore", JOptionPane.ERROR_MESSAGE);
          }
      }        
    }//GEN-LAST:event_jButton_selectImageActionPerformed
    
    private void resetGui(){
      jPanel_login.setVisible(true);
      jPanel_chat.setVisible(false);
      jPanel_sceltaStanza.setVisible(false);
        setTitle("Utente");
      username="";
      stanzaScelta=""; 
      ipServer="";
      portaServer=0;
      conSocket=null;
      comunication=null;   //per ricevere i dati dal server
      messageDisplay.clear();
      DefaultListModel listModelStanze = (DefaultListModel) jList_elencoStanze.getModel();
      listModelStanze.removeAllElements();
      DefaultListModel listModelUtenti = (DefaultListModel) jList_utentiConnessi.getModel();
      listModelUtenti.removeAllElements();        
    }
    
    public void serverDown(){  
      JOptionPane.showMessageDialog(this,"Purtroppo il server non è più disponibile al momento.","Che peccato!", JOptionPane.ERROR_MESSAGE);
      resetGui(); 
    }
    public void messFromServer(Object mes){
      if(mes instanceof String){
        String mes_str = (String)mes;  
        System.out.println("Server mes: "+mes_str);
        String[] mes_split = mes_str.split("-");
        String mesType = mes_split[0];
        String mesContent="";
        if(mes_split.length>=2)mesContent=mes_split[1];

        //System.out.println("Server,etichetta: "+Arrays.toString(mes_split));
        switch(mesType){
            case "STANZE"://Il server ha inviato la lista di stanze disponibili, formato: nomeStanza1,nomeStanza2, ...
              if(mes_split.length>=2){
                setList(jList_elencoStanze,mesContent);
                jList_elencoStanze.setSelectedIndex(0);
              }
              else{
                JOptionPane.showMessageDialog(this, "Il server non contiene stanze.","Informazione", JOptionPane.INFORMATION_MESSAGE);        
              }
            break;
            case "UTENTI_CONNESSI"://formato mesContent <nome1,nome2, . . .>
              System.out.println("Utenti connessi: "+mesContent);
              if(mes_split.length>=2){
                setList(jList_utentiConnessi,mesContent);
                //appendChatMessage("Prova prova prova...","Test",Color.RED,Color.BLACK);                
              }

            break;
            case "MESS"://Nuovo messaggio ricevuto  formato mesContent <nome,messaggio> 
              System.out.println();
                if(mes_split.length>=2){
                String mittente=mesContent.split(",")[0];
                String mess=mesContent.split(",")[1];
                messageDisplay.appendMessage(mittente,mess,Color.RED,Color.BLACK);
              }
            break;
            case "NUOVO_UTENTE"://Un nuovo utente si è collegato alla stanza
              //Formato mes <nome>
              messageDisplay.appendMessage(mesContent+" si è unito alla stanza","",Color.GREEN,Color.BLACK);  
              addToList(jList_utentiConnessi,mesContent);  
            break;
            case "LOGOUT_UTENTE"://Un utente si è scollegato dalla stanza
              //Formato mes <nome>
                messageDisplay.appendMessage(mesContent+" è uscitto dalla stanza","",Color.RED,Color.BLACK); 
              removeFromList(jList_utentiConnessi,mesContent);    
            break;
            case "NUOVA_STANZA"://Nuova stanza creata
                //Formato mes <nomeStanza>
              addToList(jList_elencoStanze,mesContent);
              //addToList(jList_elencoStanze,stanzeCreate.get(stanzeCreate.size()-1));
            break;
            case "CANC_STANZA"://Cancella stanza
              //Formato mes <nomeStanza>
                removeFromList(jList_elencoStanze,mesContent);
            break;
            case "EXIT_STANZA"://Esci dalla stanza
              JOptionPane.showMessageDialog(this, "La stanza è stata cancellata!","Attenzione", JOptionPane.ERROR_MESSAGE);
              jPanel_login.setVisible(true);
              jPanel_sceltaStanza.setVisible(false);
              jPanel_chat.setVisible(false);                  
            break;                 
            case "CREAZIONE_STANZA_FALLITA"://Da l'esito della richiesta di creazione di una stanza
              JOptionPane.showMessageDialog(this, "Esiste già una stanza con questo nome.","Che sfortuna!", JOptionPane.INFORMATION_MESSAGE);                  
            break;
            case "CANCELLAZIONE_STANZA_FALLITA"://Da l'esito della richiesta di cancellazione di una stanza
              JOptionPane.showMessageDialog(this, "Password inserita non valida.","Cancellazione stanza fallita", JOptionPane.INFORMATION_MESSAGE);                  
            break;                             
            case "VALIDAZIONE"://Il server comunica se è possibile collegarsi o meno alla stanza scelta.
              //formato messaggio: <si> o <no>
              if(mesContent.equals("si")){//Nome valido
                setTitle("Utente "+username+" stanza "+stanzaScelta);
                jPanel_login.setVisible(false);
                jPanel_chat.setVisible(true);
                jPanel_sceltaStanza.setVisible(false);               
              }
              else{
                JOptionPane.showMessageDialog(this, "La stanza selezionata contiene un utente con il tuo stesso nome.\n usa un altro nome se vuoi accedere a questa stanza. ","Che sfortuna!", JOptionPane.INFORMATION_MESSAGE);                
              }
            break;    
        }      
      }
      if(mes instanceof Serial_BufImage){//Nuova immagine ricevuta
          Serial_BufImage img = (Serial_BufImage)mes; System.out.println("Nuova immagine ricevuta da "+img.username);
          
          ImageIcon icon = new ImageIcon(Steganography.convertToBufferedImage(img.imgB));
          ImageButton imgBtn = new ImageButton(img.username, icon,img.imgB,this);
          messageDisplay.appendMessage("Immagine inviata da: "+username,"", Color.red, Color.yellow, imgBtn);

          //messageDisplay.appendMessage();
      }
        
    }
    
    public void logStanza(){
      //String mes = "SCELTA_STANZA-";
      try{  
      stanzaScelta=jList_elencoStanze.getSelectedValue().toString();
      if(!jTextField_username.getText().isEmpty()){
        username = jTextField_username.getText();
        comunication.sendData("SCELTA_STANZA-"+stanzaScelta+","+username);
        System.out.println("Richiesta di connessione alla stanza inviata");
      }else{
        JOptionPane.showMessageDialog(this, "Inserisci un username.","Informazione", JOptionPane.INFORMATION_MESSAGE);     
      }      
      }catch(Exception e){
        JOptionPane.showMessageDialog(this, "Seleziona una stanza","Informazione", JOptionPane.INFORMATION_MESSAGE);     
      }

      
      /*jPanel_login.setVisible(false);
      jPanel_chat.setVisible(true);
      jPanel_sceltaStanza.setVisible(false);*/      
    }
    public void sendChatMessage(String mes) throws IOException{//messaggio formato <messaggio>
      if(!mes.equals("")){
        jTextField_invioMes.setText("");  
        messageDisplay.appendMessage("Tu",mes,Color.RED,Color.BLACK);
        comunication.sendData("MESS-"+mes);
      }
    }
    public void setList(javax.swing.JList list,String listaNomi){//formato mes: a,b,c,...... 
      DefaultListModel model = new DefaultListModel();
      String[] listaNomi_split = listaNomi.split(",");
      for(int i=0;i<listaNomi_split.length;i++)
        model.addElement(listaNomi_split[i]);
      list.setModel(model);
    }
    public void addToList(javax.swing.JList list,String str){
      DefaultListModel model = new DefaultListModel();
      ArrayList<String> elements = getListElemnts(list);
        for (String element : elements) {
            model.addElement(element);
        }
      model.addElement(str);
      list.setModel(model);
    }
    public void removeFromList(javax.swing.JList list,String str){
      DefaultListModel model = new DefaultListModel();
      ArrayList<String> elements = getListElemnts(list);
        for (String element : elements) {
            model.addElement(element);
        }
      if(model.removeElement(str))list.setModel(model);
    }
    public ArrayList<String> getListElemnts(javax.swing.JList list){
      ListModel model = list.getModel();
      ArrayList<String> aux = new ArrayList<>();
        for(int i=0;i<model.getSize();i++)
          aux.add(model.getElementAt(i).toString());
        return aux;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat_clientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat_clientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat_clientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat_clientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chat_clientGUI("Utente").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_aggiornaElencoStanze;
    private javax.swing.JButton jButton_cancStanza;
    private javax.swing.JButton jButton_creaStanza;
    private javax.swing.JButton jButton_okSetServer;
    private javax.swing.JButton jButton_selectImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList_elencoStanze;
    private javax.swing.JList jList_utentiConnessi;
    private javax.swing.JPanel jPanel_chat;
    private javax.swing.JPanel jPanel_login;
    private javax.swing.JPanel jPanel_sceltaStanza;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField_invioMes;
    private javax.swing.JTextField jTextField_ipServer;
    private javax.swing.JTextField jTextField_portaServer;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JTextPane jTextPane_messaggi;
    // End of variables declaration//GEN-END:variables
}