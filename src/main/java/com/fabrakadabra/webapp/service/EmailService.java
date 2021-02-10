package com.fabrakadabra.webapp.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;


@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;
    private Configuration configuration;

    public void sendEmail(String to, String subject, HashMap<String, Object> model){
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,"utf-8");


        try {
            Template t = configuration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html,true);
        } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
        }


        javaMailSender.send(mailMessage);
    }
}
