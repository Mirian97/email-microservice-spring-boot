package com.quispe.email_service.infra.ses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.quispe.email_service.adapter.EmailSenderGateway;
import com.quispe.email_service.core.exception.EmailServiceException;

@Service
public class SesEmailSender implements EmailSenderGateway {

	@Autowired
	private Environment env;

	@Autowired
	private AmazonSimpleEmailService amazonSimpleEmailService;

	@Override
	public void sendEmail(String to, String subject, String body) throws EmailServiceException {
		SendEmailRequest emailRequest = new SendEmailRequest()
				.withSource(env.getProperty("emailSource"))
				.withDestination(new Destination().withToAddresses(to))
				.withMessage(new Message().withSubject(new Content(subject))
						.withBody(new Body().withText(new Content(body))));

		try {
			amazonSimpleEmailService.sendEmail(emailRequest);
		} catch (AmazonServiceException exception) {
			throw new EmailServiceException("Error sending email", exception);
		}
	}
}
