package javamail;

import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author Henrique Lima e Nicholas Patapoff
 */
public class Emails {
    
        protected String host;
        protected String user;
        protected String senha;
        
        public Emails(String host, String user, String senha)
        {
            this.host = host;
            this.user = user;
            this.senha = senha;
        }
        
        public void deletar()
        {

            Scanner leitor = new Scanner(System.in);
            String resposta;
            Folder inbox;
            Store store;

            try
            {
             for(;;)
             {   //conecta com a inbox pop3
                  Properties properties = System.getProperties();
                  Session sessao = Session.getDefaultInstance(properties, null);
                  store = sessao.getStore("pop3s");
                  store.connect(host, user, senha);
                  inbox = store.getFolder("Inbox");
                  inbox.open(Folder.READ_WRITE);

                  //vetor das mensagens do inbox
                  Message[] mensagens = inbox.getMessages();

                  if (mensagens.length == 0) System.out.println("Nenhuma "
                                                      + "mensagem encontrada.");


                  for (int i = 0; i < mensagens.length; i++) 
                  {
                      // Para depois de 11 mensagens
                      if (i > 10) 
                        break;

                      System.out.println("Mensagem " + (i + 1));
                      System.out.println("De : " + mensagens[i].getFrom()[0]);
                      System.out.println("Assunto : " + 
                                                    mensagens[i].getSubject());
                      System.out.println("Data de envio : " + 
                                                    mensagens[i].getSentDate());
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

    @Override
    public String toString() {
        return "host=" + host + ", user=" + user + ", senha=" + senha + '}';
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.host);
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emails other = (Emails) obj;
        if (!Objects.equals(this.host, other.host)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }
        
    
}
