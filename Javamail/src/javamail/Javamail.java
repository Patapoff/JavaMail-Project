/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

import java.util.Scanner;
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
       String senha = "EmailLH12";
       Scanner leitor = new Scanner(System.in);
       String resposta;
       Folder inbox;
       Store store;
       
       try
       {
        for(;;)
        {// connect to my pop3 inbox
             Properties properties = System.getProperties();
             Session session = Session.getDefaultInstance(properties, null);
             store = session.getStore("pop3s");
             store.connect(host, user, senha);
             inbox = store.getFolder("Inbox");
             inbox.open(Folder.READ_WRITE);

             // get the list of inbox messages
             Message[] mensagens = inbox.getMessages();

             if (mensagens.length == 0) System.out.println("Nenhuma mensagem encontrada.");


             for (int i = 0; i < mensagens.length; i++) 
             {
                 // stop after listing ten messages
                 if (i > 10) 
                   break;
                 
                 System.out.println("Mensagem " + (i + 1));
                 System.out.println("De : " + mensagens[i].getFrom()[0]);
                 System.out.println("Assunto : " + mensagens[i].getSubject());
                 System.out.println("Data de envio : " + mensagens[i].getSentDate());
                 System.out.println();
                 System.out.println("Quer deletar esta mensagem?[S/N]");
                 resposta = leitor.nextLine();
                 if(resposta.equals("S") || resposta.equals("s"))
                 {
                     Message mensagem = mensagens[i];
                     mensagem.setFlag(Flags.Flag.DELETED, true);
                     System.out.println("A mensagem foi deletada.");
                 }
             }
             /*System.out.println("Deseja ver os seus emails de novo?[S/N]");
             resposta = leitor.nextLine();
             if(resposta.equals("N") || resposta.equals("n"))*/
                break;
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
