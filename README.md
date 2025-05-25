# Price Comparator Backend

Project built with Spring Boot to demonstrate Accesa internship challenge.


The app compares product discounts from multiple stores using data stored in CSV files. It’s built using Java, Spring Boot, and Maven.

I implemented a series of REST API endpoints to filter and sort discounts based on different criteria:

- `GET /discounts/best` – returns the top 10 biggest discounts
- `GET /discounts/new` – shows discounts added today or yesterday
- `GET /discounts/store/{store}` – all discounts from a specific store (`LIDL`, `KAUFLAND`, `PROFI`)
- `GET /discounts/product/{productName}` – search discounts by product name
- `GET /discounts/category/{category}` – filter by product category
- `GET /discounts/min/{percentage}` – filter by minimum discount percentage
- `GET /discounts/date?from=yyyy-MM-dd&to=yyyy-MM-dd` – filter by date range
- `GET /discounts/sorted` – shows all discounts sorted by discount percentage (descending)

These are the technologies I used:
- Java 17
- Spring Boot 3.x
- Maven
- OpenCSV (for reading CSV files)
- Git & GitHub

CSV File Format

All data is stored in files inside:

Each row in the CSV looks like this:

```csv
productId,productName,brand,quantity,unit,category,fromDate,toDate,discountPercent

1,Milk,Pilos,1,l,Dairy,2025-05-25,2025-05-28,20
```

How to run:

1. Make sure you have Java 17 and Maven installed
2. Clone the repository: git clone https://github.com/<andreipuscas0>/price-comparator.git
3. Open the app in an IDE
4. Run the app -> mvn spring-boot:run
5. API available at -> http://localhost:8080

Thank you for the opportunity!