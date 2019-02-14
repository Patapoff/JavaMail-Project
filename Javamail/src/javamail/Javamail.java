/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author u17197
 */
public class Javamail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // mail server connection parameters
       String host = "pop.gmail.com";
       String user = "littleheadfilms@gmail.com";
       String password = "EmailLH12";

       
       try
       {
       // connect to my pop3 inbox
       Properties properties = System.getProperties();
       Session session = Session.getDefaultInstance(properties, null);
       Store store = session.getStore("pop3s");
       store.connect(host, user, password);
       Folder inbox = store.getFolder("Inbox");
       inbox.open(Folder.READ_ONLY);

       // get the list of inbox messages
       Message[] messages = inbox.getMessages();

       if (messages.length == 0) System.out.println("No messages found.");

       for (int i = 0; i < messages.length; i++) {
         // stop after listing ten messages
         if (i > 10) {
           System.exit(0);
           inbox.close(true);
           store.close();
         }

         System.out.println("Message " + (i + 1));
         System.out.println("From : " + messages[i].getFrom()[0]);
         System.out.println("Subject : " + messages[i].getSubject());
         System.out.println("Sent Date : " + messages[i].getSentDate());
         System.out.println();
       }

       inbox.close(true);
       store.close();
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
    
    }
}
