package ro.msg.learning.shop.services.mail_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService{

    @Autowired
    JavaMailSender mailSender;

    private SimpleMailMessage actualMailMessage;

    public MailService(@Value("${spring.mail.username}") String senderEmail) {
        this.actualMailMessage = new SimpleMailMessage();
        this.actualMailMessage.setFrom(senderEmail);
    }

    @Override
    public void sendOrderSuccessfullyCreatedEmail(String clientMail, Integer orderNumber) {
        actualMailMessage.setSubject("Order Successfully Created");
        actualMailMessage.setTo(clientMail);
        actualMailMessage.setText("Thank you for your order! All you have to do now, is just waiting for your products! /n Order Number: " + orderNumber);
    }
}
