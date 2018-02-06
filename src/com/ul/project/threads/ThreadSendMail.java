package com.ul.project.threads;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ul.project.constants.CarRentalConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreadSendMail.
 */
public class ThreadSendMail extends Thread{
	
	/** The id. */
	private int id;
	
	/** The type. */
	private String to,type;
	
	/**
	 * Instantiates a new thread send mail.
	 *
	 * @param id the id
	 * @param to the to
	 * @param type the type
	 */
	public ThreadSendMail(int id,String to,String type){
		this.id=id;
		this.to=to;
		this.type=type;}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "465");    
		Session session = Session.getDefaultInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(CarRentalConstants.FROM_EMAIL_ID,CarRentalConstants.FROM_EMAIL_PASSWORD);  
			}    });    
		try {    
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			message.setSubject(CarRentalConstants.CAR_REGISTRATION_SUBJECT); 
			if("adding".equalsIgnoreCase(type)) {
				message.setSubject(CarRentalConstants.CAR_REGISTRATION_SUBJECT); 
				message.setText(CarRentalConstants.CAR_REGISTRATION_MESSAGE+id);   
			}else if ("booking".equalsIgnoreCase(type)){
				message.setSubject(CarRentalConstants.BOOK_CAR_SUBJECT); 
				message.setText(CarRentalConstants.BOOK_CAR_MESSAGE+id);
			}else if ("registration".equalsIgnoreCase(type)){
				message.setSubject(CarRentalConstants.USER_REGISTRATION_SUBJECT);
				message.setText(CarRentalConstants.USER_REGISTRATION_MESSAGE+id); 
			}else {
				message.setSubject(CarRentalConstants.BOOKING_CANCELATION_SUBJECT);
				message.setText(CarRentalConstants.BOOKING_CANCELATION_MESSAGE); 
			}
			Transport.send(message);    
		} catch (MessagingException e) {throw new RuntimeException(e);}    }}
