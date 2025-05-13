# Product API

This project is a simple RESTful API developed using Spring Boot, designed to manage a collection of products. It provides endpoints for filtering and sorting products based on price.

## Features

- **Price Filter**: Retrieve products within a specified price range.
- **Price Sorting**: Get a list of product names sorted by price in ascending order.

## Technical Stack

- Java 11
- Spring Boot 2.7.x
- Maven
- JUnit 5
- Spring Test

## Project Structure

```
product-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── productapi
│   │   │               ├── ProductApiApplication.java
│   │   │               ├── controller
│   │   │               │   └── ProductController.java
│   │   │               ├── model
│   │   │               │   └── Product.java
│   │   │               ├── repository
│   │   │               │   └── ProductRepository.java
│   │   │               └── service
│   │   │                   └── ProductService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── data
│   │           └── products.json
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── productapi
│                       └── controller
│                           └── ProductControllerTest.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+

### Configuration

The application runs on port 8080 by default. You can modify this in `application.properties`:

```properties
server.port=8080
```

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd product-api
   ```

3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

### API Endpoints

#### 1. Price Filter
- **URL**: `/price/{initial_range}/{final_range}`
- **Method**: GET
- **URL Params**: 
  - initial_range: Integer
  - final_range: Integer
- **Success Response**:
  - Code: 200
  - Content Example:
    ```json
    [
      {
        "barcode": "74001755",
        "item": "Ball Gown",
        "category": "Full Body Outfits",
        "price": 3548,
        "discount": 7,
        "available": 1
      }
    ]
    ```
- **Error Responses**:
  - Invalid Range (400):
    ```json
    {
      "timestamp": "2025-05-13T15:00:00",
      "status": 400,
      "error": "Invalid Price Range",
      "message": "Initial price cannot be greater than final price"
    }
    ```
  - Negative Values (400):
    ```json
    {
      "timestamp": "2025-05-13T15:00:00",
      "status": 400,
      "error": "Invalid Price Range",
      "message": "Price range cannot contain negative values"
    }
    ```
  - No Results (400):
    ```json
    {
      "timestamp": "2025-05-13T15:00:00",
      "status": 400,
      "error": "Invalid Price Range",
      "message": "No products found in the price range 10000 to 20000"
    }
    ```
- **Sample Call**:
  ```bash
  curl http://localhost:8080/price/500/4000
  ```

#### 2. Price Sorting
- **URL**: `/price`
- **Method**: GET
- **Success Response**:
  - Code: 200
  - Content Example:
    ```json
    [
      "Shawl",
      "Ball Gown"
    ]
    ```
- **Sample Call**:
  ```bash
  curl http://localhost:8080/price
  ```

### Running Tests

Run the test suite using:
```bash
mvn test
```

### Building for Production

Create a production-ready JAR file:
```bash
mvn clean package
```
The JAR will be available in the `target` directory.

## Error Handling

The API includes comprehensive error handling for:
- Invalid price ranges
- Missing parameters
- Server errors

## Technical Decisions

1. **Spring Boot**: Chosen for its auto-configuration capabilities and embedded server
2. **JSON Data Storage**: Simple file-based storage using products.json for this demo
3. **Maven**: Industry-standard build tool with excellent dependency management
4. **JUnit 5**: Modern testing framework with good Spring Boot integration

## Performance Considerations

- In-memory data storage for quick access
- Efficient sorting algorithms using Java Streams
- Proper exception handling for robustness

## Security

Basic security considerations implemented:
- Input validation
- Error message sanitization
- HTTP status code standards

## Future Improvements

- Add authentication/authorization
- Implement database persistence
- Add more filtering options
- Add pagination support
- Add Swagger/OpenAPI documentation

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.