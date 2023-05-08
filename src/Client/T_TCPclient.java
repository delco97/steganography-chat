/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import commonStuff.*;
/**
 *
 * @author dca
 */
public class T_TCPclient extends Thread{
  //private BufferedReader rx;
  private ObjectInputStream ois; 
  private ObjectOutputStream oos;
  private boolean running;
  private Chat_clientGUI utente;

  public T_TCPclient(Socket s,Chat_clientGUI utente){
      try {
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        running=true;
        this.utente=utente;
        System.out.println("Inizializzato reader");
      } catch (IOException ex) {
          Logger.getLogger(T_TCPclient.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  @Override
  public void run() {
      while(running){
          try {
            utente.messFromServer(ois.readObject());  
          } catch (Exception ex) {
              Logger.getLogger(T_TCPclient.class.getName()).log(Level.SEVERE, null, ex);
              //SERVER GIU!
              utente.serverDown();
              running=false;
          }
      }
  }
  public boolean sendData(Object o){
      try {
          oos.writeObject(o);
          oos.flush();
          return true;
      } catch (IOException ex) {
          Logger.getLogger(T_TCPclient.class.getName()).log(Level.SEVERE, null, ex);
          return false;
      }
  }  
  public void setRunning(boolean b){running=b;}
}
