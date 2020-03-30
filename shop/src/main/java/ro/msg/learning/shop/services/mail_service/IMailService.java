package ro.msg.learning.shop.services.mail_service;

public interface IMailService {
    void sendOrderSuccessfullyCreatedEmail(String clientMail, Integer orderNumber);
}
