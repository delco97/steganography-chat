/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dca
 */
public class TUtente_server extends Thread{
    //Comunicazione
    private Socket conSocket;
    //private PrintWriter tx;   //per inviare i dati ad un livello più alto
    //private BufferedReader rx;
    private ObjectInputStream ois; 
    private ObjectOutputStream oos;    
    private boolean running;    
    //private TReader_server rx; //per ricevere i dati dal client
    //Caratteristiche utente
    private String nickname;
    private Server server;
    private String nomeStanza;//Nome della stanza a cui è collegato
    
    
    public TUtente_server(Socket s,Server server) throws IOException{
      this.server = server;
      conSocket = s;
      running=true;
      nickname="";
      nomeStanza="";
      oos = new ObjectOutputStream(s.getOutputStream());
      ois = new ObjectInputStream(s.getInputStream());
      start();
      //Ottengo canale di Tx
      /*OutputStream os = conSocket.getOutputStream();
      tx = new PrintWriter(os,true);//true ad ogni print viene invi del buffer dei dati da inviare      
      //Ottengo canale di RX (Thread)
      InputStream is = s.getInputStream();
      rx = new BufferedReader(new InputStreamReader(is));
      start();*/
    }
    
     @Override
     public void run() {//In ascolto di messaggi dal client
       while(running){
         try {
           recive(ois.readObject());
          } catch (IOException ex) {
              //Logger.getLogger(TUtente_server.class.getName()).log(Level.SEVERE, null, ex);
              //Scollegato !
              server.frame.appendMessage("[ "+getIp()+" , "+getPort()+" ] "+"si è scollegato");
              server.userLogOut(this);
              running=false;
              nomeStanza="";
          } catch (ClassNotFoundException ex) {
               Logger.getLogger(TUtente_server.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
     }
    
    public void setStanza(String s){nomeStanza=s;} 
    public void setRunning(boolean b){running=b;}    
    public boolean send(Object o){
        try {
            oos.writeObject(o);
            oos.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(TUtente_server.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
      
    }  
    public void recive(Object msg) throws IOException{server.messFromUtente(msg, this);}
    public InetAddress getIp(){return conSocket.getInetAddress();}
    public int getPort(){return conSocket.getPort();}
    public String getStanza(){return nomeStanza;}
    public String getNickname(){return nickname;}
    public void setNickname(String n){nickname=n;}

    
    
}
