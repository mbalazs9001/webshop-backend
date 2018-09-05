package com.codecool.shop.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender extends Thread {

    private String clientEmail;
    private String subject;
    private String content;

    public MailSender(String clientEmail, String subject, String content) {
        this.clientEmail = clientEmail;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            sendMail();
        } catch (MessagingException e) {
            System.out.println("Email could not be sent.");
            e.printStackTrace();
        }
    }

    private void sendMail() throws MessagingException {
        Session session = getSession();
        MimeMessage mail = createMail(clientEmail, subject, content, session);
        Transport.send(mail);
    }

    private Session getSession() {
        Properties props = setupProperties();
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        System.getenv("EMAIL_USER"), System.getenv("EMAIL_PASSWORD")
                );
            }
        });
    }

    private Properties setupProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        return props;
    }

    private MimeMessage createMail(String clientEmail, String subject, String content, Session session) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(System.getenv("EMAIL_USER")));
        mail.setRecipients(Message.RecipientType.TO, clientEmail);
        mail.setSubject(subject);
        mail.setText(content, "utf-8", "html");
        return mail;
    }
}
