package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final SimpleMailMessage actualMail;
    private final JavaMailSender mailSender;

    public MailService(@Value("${spring.mail.username}") String senderEmail, JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.actualMail = new SimpleMailMessage();
        this.actualMail.setFrom(senderEmail);
    }

    public void sendOrderSuccessfullyCreatedEmail(String clientMail, Integer orderNumber) {
        actualMail.setSubject("Order Successfully Created");
        actualMail.setTo(clientMail);
        actualMail.setText("Thank you for your order! All you have to do now, is just waiting for your products! /n Order Number: " + orderNumber);

        mailSender.send(actualMail);
    }
}
