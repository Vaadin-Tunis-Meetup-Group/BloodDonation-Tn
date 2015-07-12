package org.vaadin.tunis.blooddonation.service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.vaadin.tunis.blooddonation.persistence.nodes.User;

/**
 * Service for sending e-mails.
 * 
 * We use the @Async annotation to send e-mails asynchronously.
 * 
 */
@Service
public class MailService {
	
	 private final Logger log = LoggerFactory.getLogger(MailService.class);

	    @Autowired
	    private Environment env;

	    @Autowired
	    private JavaMailSenderImpl javaMailSender;
	    
	    @Autowired
	    private SpringTemplateEngine templateEngine;

	    /**
	     * System default email address that sends the e-mails.
	     */
	    private String from;

	    @PostConstruct
	    public void init() {
	        this.from = env.getProperty("spring.mail.from");
	    }

	    @Async
	    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
	        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
	                isMultipart, isHtml, to, subject, content);

	        // Prepare message using a Spring helper
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        try {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
	            message.setTo(to);
	            message.setFrom(from);
	            message.setSubject(subject);
	            message.setText(content, isHtml);
	            javaMailSender.send(mimeMessage);
	            log.debug("Sent e-mail to User '{}'", to);
	        } catch (Exception e) {
	            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
	        }
	    }

	    @Async
	    public void sendActivationEmail(User user, String baseUrl) {
	        log.debug("Sending activation e-mail to '{}'", user.getEmail());
	        Context context = new Context();
	        context.setVariable("user", user);
	        context.setVariable("baseUrl", baseUrl);
	        String content = templateEngine.process("activationEmail", context);
	        String subject = "Activation Email";
	        sendEmail(user.getEmail(), subject, content, false, true);
	    }

	    @Async
	    public void sendPasswordResetMail(User user, String baseUrl) {
	        log.debug("Sending password reset e-mail to '{}'", user.getEmail());
	        Context context = new Context();
	        context.setVariable("user", user);
	        context.setVariable("baseUrl", baseUrl);
	        String content = templateEngine.process("passwordResetEmail", context);
	        String subject = "BloodDonation - Reset Password";
	        sendEmail(user.getEmail(), subject, content, false, true);
	    }
}