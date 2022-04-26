package com.example.demo.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.ConsoleHandler;

public class MailSender {

    private final String recipient;
    private final String sender;

    public MailSender(String recipient, String sender) {
        this.recipient = recipient;
        this.sender = sender;
    }

    public void send(String htmlMsg, JavaMailSender mailSender)  {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            message.setContent(htmlMsg, "text/html");
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject("New Order");
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Error");
        }
    }


}
