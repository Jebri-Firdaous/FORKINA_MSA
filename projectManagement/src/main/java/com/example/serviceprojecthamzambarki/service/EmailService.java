package com.example.serviceprojecthamzambarki.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAssignmentEmail(String to, String userName, String projectTitle, Integer projectId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("You’ve Been Assigned to a Project: " + projectTitle);
        helper.setText(
                "<h1>Hello, " + userName + "!</h1>" +
                        "<p>You have been assigned to the project <b>" + projectTitle + "</b>.</p>" +
                        "<p>Click <a href='http://localhost:8082/projects/retrieve-project/" + projectId + "'>here</a> to view the project details.</p>",
                true
        );

        mailSender.send(message);
    }
}