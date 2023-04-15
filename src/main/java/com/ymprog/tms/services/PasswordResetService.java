package com.ymprog.tms.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ymprog.tms.entities.PasswordResetToken;
import com.ymprog.tms.entities.User;

@Service
public  class PasswordResetService {

    @Autowired
    private PasswordResetTokenServiceImpl passwordResetTokenService;

    private PasswordResetToken token = new PasswordResetToken();

    @Autowired
    private SendGrid sendGrid;

    public void sendPasswordResetEmail(String email, String resetUrl) throws IOException {
        Email from = new Email("yaman.refa.sup@gmail.com");
        String subject = "Password reset request";
        Email to = new Email(email);
        Content content = new Content("text/plain", "To reset your password, click the link below:\n" + resetUrl);
        Mail mail = new Mail(from, subject, to, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public String getPasswordResetToken(User user) {
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        passwordResetTokenService.save(token);
        return token.getToken();
    }
    
}
