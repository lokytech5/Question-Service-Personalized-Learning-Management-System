# Question Service - Personalized Learning Management System

## Overview
The Question Service is a key component of the Personalized Learning Management System, designed to manage and respond to user-generated questions.This Spring Boot application designed to handle user-generated questions and provide answers. It integrates advanced features like Spring Boot Actuator for application insights, Spring Data JPA for database interactions, and OpenAI's GPT-3 Java SDK for AI-driven answers. The service also includes client-side discovery with Netflix Eureka and declarative REST client with OpenFeign, making it highly scalable and responsive. Its primary function is to manage, store, and respond to user inquiries with either AI-generated or expert human responses, facilitating an interactive and informative user experience.

## Maven Project Configuration
The project is built using Maven, providing a standardized build process. Key configurations in the `pom.xml` file include:

- **Parent Artifact**: `spring-boot-starter-parent` (Version: 3.1.4)
- **Group ID**: `com.lokytech`
- **Artifact ID**: `question-service`
- **Version**: `0.0.1-SNAPSHOT`
- **Java Version**: `17`
- **Spring Cloud Version**: `2022.0.4`

### Dependencies
The service utilizes several dependencies, including:
- Spring Boot's core starter packages for web, data JPA, validation, and testing.
- MySQL Connector for database connectivity.
- ModelMapper for object mapping.
- Spring Cloud for Netflix Eureka client and OpenFeign.
- OpenAI GPT-3 Java SDK for AI answer generation.

## API Endpoints

### POST `/question/{userId}`
Creates a new question associated with a user.

**Request Body**:
```json
{
  "content": "Which year did Abraham Lincoln become US president?",
  "timeStamp": "2023-12-30T03:15:55.000",
  "topic": "Politics"
}
```
**Response**:
```json
{
  "questionId": 3,
  "userId": 1,
  "content": "Which year did Abraham Lincoln become US president?",
  "timeStamp": "2023-12-30T03:15:55.000",
  "topic": "Politics",
  "status": "UNANSWERED"
}
```

### GET `/question/{userId}`
Fetches questions submitted by a specific user.
**Response**:
```json
{
  "content": "which year did Abraham Lincoln become US president?",
  "timeStamp": "2023-12-30T03:15:55",
  "status": "ANSWERED",
  "topic": "Politics"
}
```

### POST `/generate-answer/{questionId}`
Generates an AI-based answer for a specified question.
**Response:**
```json
{
  "answerId": 3,
  "question": {
    "questionId": 3,
    "userId": 1,
    "content": "Which year did Abraham Lincoln become US president?",
    "timeStamp": "2023-12-30T03:15:55.000",
    "topic": "Politics",
    "status": "ANSWERED"
  },
  "content": "Abraham Lincoln became the 16th President of the United States in the year 1861.",
  "timeStamp": "2023-12-01T07:48:06.386419",
  "humanGenerated": false,
  "answeredBy": "OpenAI Assistant"
}

```

### GET `/answers/{questionId}`
Retrieves the answer for a given question.

## Running the Application with Docker
The application, along with its dependencies like the service-registry and user-service, can be run using Docker and Docker Compose.

### Prerequisites
* Docker installed on your system. If not already installed, download from Docker's official site.
* Docker Compose installed with Docker.

### Steps to Run
* Clone the Repository: Clone this repository to your local machine.
* Navigate to the Project Directory: Change to the directory where the project is located.
* Build and Run with Docker Compose: Execute the following command:
  `docker-compose up`
* This command will start all the services defined in the docker-compose.yml file, including the service-registry and user-service.

## Example Requests
To facilitate the ease of use and testing, we have pre-populated the database with sample data. Below are examples of how to use the API with this data:

## Contributing
We welcome contributions to enhance the Question Service. Please adhere to our contribution guidelines when submitting pull requests.

## License
This project is Licensed under the MIT License.