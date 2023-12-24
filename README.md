# Flight Search API

This project is a robust Flight Search API that enables users to query, list, and perform searches for flight information based on specific criteria. Developed with modern Java technologies, this application features a scheduled task that automatically fetches and updates flight information from a mock external API source daily.

## Key Features

- Comprehensive flight information management: querying, adding, updating, and deleting.
- Daily automated updates of flight data via a scheduled task, simulating third-party API integration.
- RESTful API design for easy integration and usage.
- Detailed API documentation with Swagger UI.
- Secured endpoints with HTTP Basic Authentication using Spring Security.

## Technologies

- **Spring Boot:** For rapid and simplified application development.
- **Spring Data JPA:** For efficient and streamlined database operations.
- **H2 Database:** An in-memory database used for development purposes.
- **Spring Security:** To secure API endpoints and manage user authentication.
- **Swagger/OpenAPI:** For interactive API documentation and testing.
- **Scheduled Tasks:** For automated daily updates of flight data.
- **JUnit and Mockito:** Employed for thorough testing of application components.

## Getting Started

### Installation

1. **Clone the Project:**
   ```bash
   git clone https://github.com/your-username/flight-search-api.git

2. **Navigate to the Project Directory:**
   ```bash
   cd flight-search-api
   
### Running the Application

1. **Start the Application:**
   on Mac and Linux
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```bash
   mvnw spring-boot:run
   ```
   
3. **Access the Swagger UI:**
   - Open your web browser and go to http://localhost:8080/swagger-ui.html.
   - You will be prompted for a username and password.

### Access Credentials
   - Username: user
   - Password: [Generated in the console, after the running application]

For example:
![image](https://github.com/furkankeremselimoglu/flight-search-api/assets/83104753/4fa27547-e27d-4ae8-a492-55a8055c4a21)

Use these credentials for HTTP Basic Authentication to access the Swagger UI and interact with the API endpoints.

## API Endpoints

The application offers a variety of endpoints, which are accessible through the Swagger UI. Here are the details for the flight search functionalities:

### Flight Management
- `GET /flights`: List all flights.
- `GET /flights/{id}`: Retrieve a flight by its ID.
- `POST /flights`: Add a new flight.
- `PUT /flights/{id}`: Update an existing flight.
- `DELETE /flights/{id}`: Delete a flight.

### Flight Search
The flight search feature includes different methods for querying flight data:

1. **Search Flights by Airports and Time:**
   - `GET /flights/search`
   - This endpoint allows you to search for flights based on departure and arrival airports and a specific departure time.
   - Parameters: `departureAirport`, `arrivalAirport`, `departureTime`.

2. **Flexible One-Way and Round-Trip Flight Search:**
   - `GET /flights/searchFlights`
   - This endpoint caters to both one-way and round-trip flight searches. Depending on whether a return time is specified, it can return either one-way or round-trip flight options.
   - For one-way flights, provide the `departureAirport`, `arrivalAirport`, and `departureTime`. It will return flights matching these criteria.
   - For round-trip flights, additionally provide the `returnTime`. The endpoint will return both outbound and return flights that fit the specified criteria.
   - Parameters: `departureAirport`, `arrivalAirport`, `departureTime`, `returnTime` (optional for round-trip).

3. **Search Flights by Departure and Arrival Times:**
   - `GET /flights/flightsByDepartureAndArrivalTimes`
   - Use this endpoint to find flights within a specific time range, from the departure time to the arrival time.
   - Parameters: `departureTime`, `arrivalTime`.

Each of these search functionalities provides a flexible way to query flight data based on different criteria, making it easy for users to find the information they need.
