package com.quispe.email_service.infra.ses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class AwsSesConfig {

	@Value("${aws.accessKeyId}")
	private String accessKey;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonSimpleEmailServiceClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.US_EAST_2)
				.build();
	}
}
