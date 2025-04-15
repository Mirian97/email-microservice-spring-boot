package com.quispe.email_service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quispe.email_service.adapter.EmailSenderGateway;
import com.quispe.email_service.core.EmailSenderUseCase;

@Service
public class EmailSenderService implements EmailSenderUseCase {

	@Autowired
	EmailSenderGateway emailSenderGateway;

	@Override
	public void sendEmail(String to, String subject, String body) {
		this.emailSenderGateway.sendEmail(to, subject, body);
	}
}
