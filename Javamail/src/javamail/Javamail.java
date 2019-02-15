package javamail;

import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javamail.Emails;

/**
 *
 * @author Henrique Lima e Nicholas Patapoff
 */
public class Javamail {

    
    public static void main(String[] args) {
           
       Emails meuGmail = new Emails("pop.gmail.com", 
                         "littleheadfilms@gmail.com", "EmailLH12");
       meuGmail.deletar();
    }

}


