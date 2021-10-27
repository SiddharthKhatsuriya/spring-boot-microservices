# Sweet-Home
#### An online hotel room boking API using Spring-boot

## 1) Booking Micro-service
### This service provides user with 2 endpoints
- ### /hotel/booking
    - POST call
        - Expectes a request-body with booking related details.
        - Creates booking record in Hotel database
        - Sets the transaction id as default value i.e 0[zero] in this case.
        - Creats a new id for every record.
- ### /hotel/booking/{id}
    - GET Call
        - Expectes a booking record id.
        - Returns all the details for the provided ID.
    
    
- ### /hotel/booking/{id}/transaction
    - POST call
        - Expecects a valid available id in the URL.
        - Also needs a request-body with transaction related details
        - This endpoint call /payment/transaction endpoint of payment Micro-service and creates a transaction record for specified booking-id
        - Updates the booking record with the transaction-id returned from payment Micro-service
        - There are two unwanted scenarios handled in this endpoint. First, booking-id provided is invalid. and second, if the feign client throws an exception.
       - In both the above cases, a graceful exception message will be returned.

## 2) Payment Micro-service
- ### /payment/transaction
    - POST call
        - This endpoint will create a new record in the transactions table of payments database.
        - This accepts a request-body with transaction related details.
        - On Successful creation of a payment record the details are printed on the console.
        - There are two unwanted scenarios handled in this endpoint. First, PaymentMode provided is not in valid list of payment modes accepted and second, if the feign client throws an exception.( feign client used to get booking details to print on console)
- ### /payment/transaction/{id}
    - GET call
        - This endpoint will fetch in all the details stored in the transactions table for Payment DB with the provided id.
  
## HOW TO USE
 - Lets walk through the infrastructure summary before other details. 
    -  The booking microservices are coded to run on 8081 port of the host executing it.
    -  The payment microservice are coded to run on 8083. 
    -  Both this microservices are configured as Eureka Client, hence we would need an Eureka Server running before starting these. The Eureka server provided here is running on 8761 port and the microservices are configured to check for it on the same.
    -  There is also an API-Gateway which would run on 9191 port and is configured to point to both the listed microservices.

---
**NOTE**

As the micro-services are using MySQL for RDBMS, make sure MySQL server is running on 3306 with root user and no password ( Default configs) and make sure two databases HOTEL and PAYMENT exists before starting.

---

- Example API call for creating a new Booking
    ```
    POST http://localhost:9191/hotel/booking
    ```
    Request Body
    ```json
    {
        "fromDate": "2021-10-20",
        "toDate": "2021-10-24",
        "aadharNumber": "sample-aadhar-number",
        "noOfRooms": "2"
    }
    ```
    Response Received
    ```
    Status: 200
    ```
    ```json
    {
        "bookingId": 1,
        "fromDate": "2021-10-20",
        "toDate": "2021-10-24",
        "bookedOn": "2021-10-26",
        "aadharNumber": "sample-aadhar-number",
        "noOfRooms": 2,
        "roomNumbers": "43, 50",
        "roomPrice": 8000,
        "transactionId": 0
    }
    ```
- Example API call for creating Transaction for an existing booking
    ```
    POST http://localhost:9191/hotel/booking/1/transaction
    ```
    Request Body
    ```json
    {
        "paymentMode": "UPI",
        "upiId": "123466",
        "cardNumber": "",
        "bookingId": "1"
    }
    ```
    Respinse Received
    ```
    Status 200
    ```
    ```json
    {
        "bookingId": 1,
        "fromDate": "2021-10-20",
        "toDate": "2021-10-24",
        "bookedOn": "2021-10-26",
        "aadharNumber": "sample-aadhar-number",
        "noOfRooms": 2,
        "roomNumbers": "43, 50",
        "roomPrice": 8000,
        "transactionId": 1
    }
    ```
    Execption Responses
    ```json
    {
        "statusCode": 400,
        "message": "Invalid Booking Id"
    }
    ```
     ```json
    {
        "statusCode": 400,
        "message": "Invalid mode of payment"
    }
    ```
- Example API call for getting transaction details.
    ```
    GET http://localhost:9191/payment/transaction/1
    ```
    Response Received
    ```json
    {
        "transactionId": 1,
        "paymentMode": "upi",
        "bookingId": 1,
        "upiId": "123466",
        "cardNumber": ""
    }
    ```