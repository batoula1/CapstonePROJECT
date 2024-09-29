# OpenInk

OpenInk is a full-stack blog platform built with Java Spring Boot and React. It allows users to register, log in, write blogs, comment on posts, and explore content through a smooth, infinite-scroll newsfeed.

## Features

- User Authentication (JWT & Spring Security)
- User Dashboard to manage blogs and comments
- Blog view page with comment feature
- Infinite scrollable newsfeed
- Search functionality for blogs and users

## Technology Stack

### Backend
- Java Spring Boot
- MySQL Database
- Spring JPA
- Spring Security with JWT authentication
- Lombok for simplified coding
- Maven for dependency management

### Frontend
- React JS
- Bootstrap for responsive design
- Axios for API communication
- Formik & YUP validation
## Prerequisites

Before you begin, ensure you have the following installed:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/installer/) and MySQL Workbench or [XAMPP](https://www.apachefriends.org/index.html) for local database management
- [Node.js](https://nodejs.org/) and npm (included with Node.js)
- An IDE such as [Spring Tool Suite (STS)](https://spring.io/tools) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Running the Project

### Backend (Spring Boot)

1. Install MySQL and set up a local MySQL server using MySQL Workbench or XAMPP.

2. Create a new database named `openink` by running the following SQL command in MySQL:
   ```sql
   CREATE DATABASE openink;
# OpenInk

OpenInk is a full-stack blog platform built with Java Spring Boot and React. It allows users to register, log in, write blogs, comment on posts, and explore content through a smooth, infinite-scroll newsfeed.

## Features

- User Authentication (JWT & Spring Security)
- User Dashboard to manage blogs and comments
- Blog view page with comment feature
- Infinite scrollable newsfeed
- Search functionality for blogs and users

## Technology Stack

### Backend
- Java Spring Boot
- MySQL Database
- Spring JPA
- Spring Security with JWT authentication
- Lombok for simplified coding
- Maven for dependency management

### Frontend
- React JS
- Bootstrap for responsive design
- Axios for API communication

## Prerequisites

Before you begin, ensure you have the following installed:

- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/installer/) and MySQL Workbench or [XAMPP](https://www.apachefriends.org/index.html) for local database management
- [Node.js](https://nodejs.org/) and npm (included with Node.js)
- An IDE such as [Spring Tool Suite (STS)](https://spring.io/tools) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Running the Project

### Backend (Spring Boot)

1. Install MySQL and set up a local MySQL server using MySQL Workbench or XAMPP.

2. Create a new database named `openink` by running the following SQL command in MySQL:
   ```sql
   CREATE DATABASE openink;
3. Open the project in your IDE (STS or IntelliJ) and locate the application.properties file in the src/main/resources directory.

Configure the database connection by specifying your MySQL username and password:

spring.datasource.url=jdbc:mysql://localhost:3306/openink
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

4. Ensure your IDE has downloaded all the Maven dependencies. This can be done by right-clicking the project and selecting Maven > Update Project (for STS) or by running mvn clean install from the terminal.

5. Run the Spring Boot application from your IDE by running the main class (usually OpenInkApplication),

### Frontend (React)

1. Navigate to the `open-ink-react` folder inside the project directory:
   ```bash
   cd open-ink-react
2. Ensure you have Node.js installed. You can check this by running:
   node -v
   npm -v
3. If Node.js is not installed, download and install it from here.

Install the required npm packages:
npm install

4. After the installation is complete, start the React development server:
   npm start

5. The React app should now be running on http://localhost:3000.

Make sure the Spring Boot backend is also running so the frontend can interact with the API.


## Figma
https://www.figma.com/design/9nWb7ieC6NCLYh3cJeranz/Blog-Website?node-id=0-1&m=dev&t=hIpIn4S7VXWKvSjD-1