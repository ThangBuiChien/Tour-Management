# Tour Management Website

## Overview

Welcome to the Tour Management Website, a comprehensive platform designed to manage tours effectively. Our website provides functionalities tailored for different user roles including admin, users, and tour guides. Built with a robust technology stack, this application ensures a seamless experience for managing tours, bookings, and user interactions.

## Features

### Admin
- **Manage Tours**: Create, update, and delete tour information.
- **User Management**: Manage user accounts and permissions.
- **Notification Management**: Send and manage notifications for users when submited invoice is acceptable
- **Invoice Management**: Oversee and manage invoices for bookings.

### Users
- **Tour Booking**: Browse available tours and make bookings.
- **Feedback**: Provide feedback on tours and services.
- **User Profile**: Manage personal information 

### Tour Guides
- **Tour Assignment**: View assigned tours and schedules.

## Technology Stack

- **Spring Boot**: Provides the foundation for the application with powerful dependency injection and configuration management.
- **Spring Security**: Ensures secure authentication and authorization mechanisms.
- **Spring MVC**: Facilitates the creation of a robust and scalable web application.
- **Thymeleaf**: Enables server-side rendering with dynamic and well-structured HTML templates.
- **MySQL**: Utilized for storing and managing application data.

## Running the Project

To get the project up and running, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ThangBuiChien/tour-management-website.git
   cd tour-management-website
   ```

2. **Create a MySQL database**:
   - Open your MySQL workbench.
   - Create a database for the project:
     ```sql
     CREATE DATABASE tour_management;
     ```

3. **Configure the database connection**:
   - Open `src/main/resources/application.properties`.
   - Adjust the database connection settings:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/tour_management
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=create
     ```
First time running you should use "spring.jpa.hibernate.ddl-auto=create" to create the initial database table and their relationship based on model class. After that you should change it to "spring.jpa.hibernate.ddl-auto=update". otherwise it automatically reset all your data when you rerun the application

4. **Run the application**:
   - Use the following command to start the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```
   - The application will start running on `http://localhost:8080`.

## Contributing

We welcome contributions from the community. If you would like to contribute, please fork the repository and create a pull request with your changes.


---

Feel free to reach out if you have any questions or need further assistance. Happy touring!
# DEVELOPING WEBSITE’S DIVISION

## Team Members and Their Tasks

| Team Members           | Tasks                                                                                              | Assign Report                        |
|------------------------|---------------------------------------------------------------------------------------------------|--------------------------------------|
| **Nguyễn Văn Anh Đồng** | 1. Booking function<br>2. Manage Invoice<br>3. Manage Notification<br>4. Write doc Part 3, 7     | Demo Booking Function, Invoice       |
| **Bùi Chiến Thắng**    | 1. Authentication and Authorization<br>2. Create CRUD Tour Function<br>3. Searching and Paging Function<br>4. Write Doc Part 4, 6 | Demo the CRUD Tour Function          |
| **Võ Đăng Trình**      | 1. Design frontend<br>2. Assign Page for each type of user<br>3. CRUD User Function<br>4. Write Doc Part 5<br>5. Manage the group report | Demo the CRUD User Function          |
| **Trần Gia Huy**     | 1. Tour guide function<br>2. Assign Tour guide<br>3. Frontend<br>4. Write Doc Part 8               | Demo Tour guide function and feedback|
| **Đỗ Xuân Trường**     | 1. User crud function<br>2. Frontend<br>3. Testing<br>             | Demo Tour guide function and feedback|

# APPLYING DESIGN PATTERN WEBSITE’S DIVISION

## Team Members and Their Tasks

| Team Members           | Tasks                                                                                  | Assign Report                                 |
|------------------------|---------------------------------------------------------------------------------------|-----------------------------------------------|
| **Nguyễn Văn Anh Đồng** | 1. Apply Observer in notification function                                             | Demo Observer in notification function        |
| **Bùi Chiến Thắng**    | 1. Apply Factory pattern in paying services<br>2. Apply Abstract factory pattern in paying services | Demo Factory pattern in paying services<br>Demo Abstract factory pattern in paying services |
| **Võ Đăng Trình**      | 1. Apply Prototype pattern in duplicate tour                                            | Demo Prototype pattern in duplicate tour      |
| **Đỗ Xuân Trường**     | 1. Apply builder pattern in creating user                                                | Demo builder pattern in creating user         |

