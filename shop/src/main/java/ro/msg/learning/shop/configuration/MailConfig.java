package ro.msg.learning.shop.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.mail")
public class MailConfig {

    private String host;

    private String port;

    private String username;

    private String password;

    private MailConfigProperties properties;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", properties.starttlsEnable);
        javaMailProperties.put("mail.smtp.auth", properties.smtpAuth);
        javaMailProperties.put("mail.transport.protocol", properties.transportProtocol);

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Data
    @Configuration
    @EnableConfigurationProperties
    @ConfigurationProperties("spring.mail.properties")
    private static class MailConfigProperties {

        private Boolean smtpAuth;

        private Boolean starttlsEnable;

        private String transportProtocol;
    }
}
