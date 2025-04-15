package com.quispe.email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quispe.email_service.application.EmailSenderService;
import com.quispe.email_service.core.EmailRequest;
import com.quispe.email_service.core.exception.EmailServiceException;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

	@Autowired
	EmailSenderService emailSenderService;

	@PostMapping()
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		try {
			this.emailSenderService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());
			return ResponseEntity.ok("Email sent successfully");
		} catch (EmailServiceException exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error when sending email: " + exception.getMessage());
		}
	}
}
