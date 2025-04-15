package com.quispe.email_service.adapter;

public interface EmailSenderGateway {

	void sendEmail(String to, String subject, String body);
}
