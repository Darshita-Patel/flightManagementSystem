# ✈️ Flight Management System

The **Flight Management System (FMS)** is a Java-based web application designed to streamline flight booking, scheduling, and management. The system consolidates flight data from various airline carriers and enables customers and administrators to manage bookings and operations efficiently.

---

## 🌟 Features

- **Passenger Management:** Create and manage passenger profiles and bookings.
- **Flight Scheduling and Management:** Add, update, and view flight schedules.
- **Date-wise Booking:** View flights and availability by date.
- **Flexible Ticket Booking:** Book, cancel, and view tickets easily.
- **User Feedback:** Collect and manage user feedback for continuous improvement.

---

## 🧩 Modules

The application has two main modules:

### ✨ Admin Module
Admins can:
- Log in securely.
- Add, update, and view:
  - Airports
  - Routes
  - Flights
- View:
  - User feedback
  - Ticket details
  - Passenger details
- Log out.

### ✨ Customer Module
Customers can:
- Register a user account.
- Log in to the system.
- Search for available flights.
- Book tickets and view booking details.
- Cancel bookings.
- Provide feedback.
- Log out.

---

## ⚙️ Technologies Used

### Frontend
- **JSP** (JavaServer Pages)
- **CSS**
- **JavaScript**
- **Bootstrap**

### Backend
- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**

### Database
- **MySQL**

---

## 🗂️ Core Entities and Tables

The system uses multiple tables to manage data:

- **airport**
- **flight**
- **route**
- **ticket**
- **passenger**
- **feedback**
- **flight_user**
- **flight_datewise**

Each table has appropriate keys and fields to track relevant information, such as flight schedules, passenger details, and bookings.

---

## 🏗️ System Design Overview

### DTO Classes
These classes encapsulate the data for:
- Airport
- Feedback
- Flight
- Route
- Ticket
- Passenger
- Flight Users
- Flight-Date Associations

Each DTO has:
- Attributes matching table fields.
- Getters and setters.
- Hashing and equality methods where needed.

### Service Layer
The service layer manages core operations:
- **FlightService:** Flight creation, retrieval, and seat management.
- **FlightUserService:** User registration, authentication, and type retrieval.
- **RouteService:** Route creation and management.
- **TicketService:** Discount calculation, ticket booking, cancellation, and seat validation.

### DAO Layer
Repositories implement data persistence operations, such as:
- Saving records to the database.
- Retrieving records by ID.
- Fetching lists of entities.
- Deleting records.

---

## 🧮 Special Features

- **Discount Calculation:**
  - Children ≤ 14 years: 50% discount.
  - Seniors ≥ 60 years: 30% discount.
- **Capacity Check:**
  - Ensures flights have sufficient seats before confirming bookings.
- **Return Flight & Route Creation:**
  - Automatically generate reverse journeys to simplify scheduling.

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven
- MySQL Server

### Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd flight-management-system
   ```

2. **Set Up Database**
   - Create a MySQL database:
     ```sql
     CREATE DATABASE fms_db;
     ```
   - Configure database credentials in \`application.properties\`:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/fms_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
   - Open [http://localhost:8080](http://localhost:8080) in your browser.

---

## 🙌 Contributing

Contributions are welcome! Please fork the repository and open a pull request.

---
