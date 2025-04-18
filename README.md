# 📧 Email Microservice with Spring Boot and AWS SES

![Build Status](https://img.shields.io/badge/build-passing-brightgreen) ![Java](https://img.shields.io/badge/Java-11%2B-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![AWS SES](https://img.shields.io/badge/AWS-SES-blue)

<div align="center">
	<img src="docs/mail.jpg" alt="Email Banner"/>
</div>

<br/>

This project is a Spring Boot-based microservice designed to send emails using **Amazon Simple Email Service (SES)** 🚀. It provides a RESTful API to trigger email sending through HTTP requests, following a clean architecture pattern with clear separation of concerns. The microservice is lightweight, scalable, and perfect for integration in a microservices ecosystem 🌐.

## ✨ Features

- Send emails using Amazon SES with customizable recipient, subject, and body 📩.
- RESTful API endpoint to trigger email sending via POST requests 🌐.
- Clean architecture with separation of concerns (use cases, application, adapters, infrastructure) 🧩.
- Error handling for email sending failures 🚨.
- Configurable email source address via environment properties ⚙️.
- Integration with AWS SES for reliable email delivery ☁️.

## 🛠️ Technologies Used

- **Java** ☕: Programming language (version 11 or later).
- **Spring Boot** 🌱: Framework for building the microservice.
- **Amazon SES** ☁️: Email sending service via AWS SDK.
- **Maven** 📦: Dependency management and build tool.
- **Spring Web** 🕸️: For creating RESTful endpoints.
- **Spring Core** 🔩: For dependency injection and configuration.
- **AWS SDK for Java** 🛠️: To interact with Amazon SES.

## 📋 Prerequisites

Before running the project, ensure you have:

- Java Development Kit (JDK) 11 or higher ☕.
- Maven 3.6 or higher 📦.
- An AWS account with access to Amazon SES ☁️.
- AWS credentials (Access Key ID and Secret Access Key) with SES permissions 🔑.
- Git (optional, for cloning the repository) 🐙.

## ⚙️ Setup and Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Mirian97/email-microservice-spring-boot.git
   cd email-microservice-spring-boot
   ```

2. **Install Dependencies**:

   ```bash
   ./mvnw clean install
   ```

3. **Configure AWS SES**:

   - Set up your AWS SES account and move out of sandbox mode for production (or use sandbox for testing) ☁️.
   - Obtain your AWS Access Key ID and Secret Access Key from the AWS IAM console 🔑.
   - Verify an email address in SES to use as the `emailSource` 📧.

4. **Update Configuration**:
   Configure the `application.properties` file (see [🔧 Configuration](#-configuration) section).

5. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```
   The app starts on `http://localhost:8080` by default.

## 🔧 Configuration

Update `src/main/resources/application.properties` or `application.yml` with your AWS credentials and email source. Example:

```properties
emailSource=verified-email@example.com

aws.accessKeyId=your-aws-access-key-id
aws.secretKey=your-aws-secret-key
aws.region=us-your-aws-region

springdoc.api-docs.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.path=/api-docs
```

For security, use environment variables or AWS Secrets Manager in production 🔐.

If your your project is already running, you could check an online documentation with Swagger OpenApi, in this link <a>http://localhost:8080/swagger-ui/index.html</a>

## 🌍 API Endpoints

### POST /api/email

Sends an email with the provided details 📩.

#### Request Body

JSON format:

```json
{
	"to": "string",
	"subject": "string",
	"body": "string"
}
```

- `to`: Recipient's email address 📨.
- `subject`: Email subject line ✉️.
- `body`: Email body content (plain text) 📝.

#### Response

- **Success** ✅: `200 OK`
  ```json
  "Email sent successfully"
  ```
- **Error** ❌: `500 INTERNAL SERVER ERROR`
  ```json
  "Error when sending email: <error-message>"
  ```

#### Example Request

```bash
curl -X POST http://localhost:8080/api/email \
-H "Content-Type: application/json" \
-d '{
  "to": "recipient@example.com",
  "subject": "Test Email",
  "body": "This is a test email sent via AWS SES."
}'
```

## 🚀 Usage

1. Start the application as described in [⚙️ Setup and Installation](#-setup-and-installation).
2. Send a POST request to `/api/email` using `curl`, Postman, or any HTTP client 📬.
3. Verify the response to confirm email sending ✅.
4. Check the recipient's inbox (if verified in SES) or SES console for delivery status 📊.

## 🚨 Error Handling

- **EmailServiceException**: Handles email sending failures (e.g., invalid addresses, SES errors) ⚠️.
- **AmazonServiceException**: Wrapped in `EmailServiceException` for AWS-specific issues (e.g., authentication, limits) ☁️.
- Returns `500 INTERNAL SERVER ERROR` with a descriptive message on failure ❌.

## 🏛️ Architecture

The project follows a **clean architecture** pattern:

- **Core** 🧠:
  - `EmailSenderUseCase`: Defines email sending contract.
  - `EmailRequest`: Record for email data (to, subject, body).
  - `EmailServiceException`: Custom exception for errors.
- **Application** ⚙️:
  - `EmailSenderService`: Implements `EmailSenderUseCase`, delegates to gateway.
- **Adapter** 🔌:
  - `EmailSenderGateway`: Interface for email sending implementation.
- **Infrastructure** 🏗️:
  - `SesEmailSender`: Uses AWS SES to send emails.
  - `AwsSesConfig`: Configures SES client with credentials and region.
- **Controller** 🎮:
  - `EmailSenderController`: Handles HTTP requests and delegates to service.

This ensures modularity, testability, and flexibility to swap email providers.
