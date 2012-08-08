package testOrders.mail;

import testOrders.helper.TestHelper;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail extends TestHelper{

public static void SendMail(String action, String file){

	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){ 
					return new PasswordAuthentication(usermail,passwordmail);
				}
			});
	try {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(passwordmail));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(sendmail));
		msg.setSubject(action);
		msg.setText(action);
		
		BodyPart messageBodyPart = new MimeBodyPart();
		
		Multipart multipart = new MimeMultipart();
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file);
        multipart.addBodyPart(messageBodyPart);
        
        msg.setContent(multipart);
        
		Transport.send(msg);
		System.out.println("Done");
	} catch (MessagingException e) {
		throw new RuntimeException(e);
		}
	}
}

